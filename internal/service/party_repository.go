package service

import (
	"context"
	"fmt"

	"github.com/jackc/pgx/v5"
	"github.com/rs/zerolog/log"

	"github.com/erpmicroservices/people-and-organizations-endpoint-graphql/internal/db"
	"github.com/erpmicroservices/people-and-organizations-endpoint-graphql/internal/model"
)

// PartyRepository handles database operations for parties
type PartyRepository struct {
	db *db.Connection
}

// NewPartyRepository creates a new party repository
func NewPartyRepository(db *db.Connection) *PartyRepository {
	return &PartyRepository{db: db}
}

// GetPartyByID retrieves a party by ID
func (r *PartyRepository) GetPartyByID(ctx context.Context, id string) (*model.Party, error) {
	query := `
		SELECT p.party_id, p.party_type_id, p.comment, pt.description as party_type_description
		FROM party p
		JOIN party_type pt ON p.party_type_id = pt.party_type_id
		WHERE p.party_id = $1
	`

	row := r.db.Pool.QueryRow(ctx, query, id)

	var party model.Party
	var partyType model.PartyType

	err := row.Scan(&party.ID, &partyType.ID, &party.Comment, &partyType.Description)
	if err != nil {
		if err == pgx.ErrNoRows {
			return nil, nil
		}
		log.Error().Err(err).Str("party_id", id).Msg("Failed to get party by ID")
		return nil, err
	}

	party.PartyType = &partyType
	return &party, nil
}

// GetAllParties retrieves parties with pagination
func (r *PartyRepository) GetAllParties(ctx context.Context, pageInfo model.PageInfoInput) (*model.PartyConnection, error) {
	// Handle default values and nil pointers
	pageNumber := 0
	if pageInfo.PageNumber != nil {
		pageNumber = *pageInfo.PageNumber
	}

	pageSize := 100
	if pageInfo.PageSize != nil {
		pageSize = *pageInfo.PageSize
	}

	offset := pageNumber * pageSize
	limit := pageSize

	// Build query with sorting
	query := `
		SELECT p.party_id, p.party_type_id, p.comment, pt.description as party_type_description
		FROM party p
		JOIN party_type pt ON p.party_type_id = pt.party_type_id
		ORDER BY p.party_id
		LIMIT $1 OFFSET $2
	`

	rows, err := r.db.Pool.Query(ctx, query, limit, offset)
	if err != nil {
		log.Error().Err(err).Msg("Failed to query parties")
		return nil, err
	}
	defer rows.Close()

	var parties []*model.Party
	for rows.Next() {
		var party model.Party
		var partyType model.PartyType

		err := rows.Scan(&party.ID, &partyType.ID, &party.Comment, &partyType.Description)
		if err != nil {
			log.Error().Err(err).Msg("Failed to scan party row")
			return nil, err
		}

		party.PartyType = &partyType
		parties = append(parties, &party)
	}

	if err = rows.Err(); err != nil {
		log.Error().Err(err).Msg("Error iterating party rows")
		return nil, err
	}

	// Create edges
	edges := make([]*model.PartyEdge, len(parties))
	for i, party := range parties {
		cursor := fmt.Sprintf("party_%s", party.ID)
		edges[i] = &model.PartyEdge{
			Node:   party,
			Cursor: cursor,
		}
	}

	// Create page info
	hasNextPage := len(parties) == pageSize
	hasPreviousPage := pageNumber > 0

	pageInfoResult := &model.PageInfo{
		HasNextPage:     hasNextPage,
		HasPreviousPage: hasPreviousPage,
	}

	if len(edges) > 0 {
		pageInfoResult.StartCursor = &edges[0].Cursor
		pageInfoResult.EndCursor = &edges[len(edges)-1].Cursor
	}

	return &model.PartyConnection{
		Edges:    edges,
		PageInfo: pageInfoResult,
	}, nil
}

// CreateParty creates a new party
func (r *PartyRepository) CreateParty(ctx context.Context, newParty model.NewParty) (*model.Party, error) {
	// First, get the party type
	partyTypeQuery := `SELECT party_type_id, description FROM party_type WHERE party_type_id = $1`
	var partyType model.PartyType
	err := r.db.Pool.QueryRow(ctx, partyTypeQuery, newParty.PartyTypeID).Scan(&partyType.ID, &partyType.Description)
	if err != nil {
		if err == pgx.ErrNoRows {
			return nil, fmt.Errorf("party type with ID %s not found", newParty.PartyTypeID)
		}
		log.Error().Err(err).Str("party_type_id", newParty.PartyTypeID).Msg("Failed to get party type")
		return nil, err
	}

	// Insert the new party
	insertQuery := `
		INSERT INTO party (party_type_id, comment) 
		VALUES ($1, $2) 
		RETURNING party_id
	`

	var partyID string
	err = r.db.Pool.QueryRow(ctx, insertQuery, newParty.PartyTypeID, newParty.Comment).Scan(&partyID)
	if err != nil {
		log.Error().Err(err).Interface("new_party", newParty).Msg("Failed to create party")
		return nil, err
	}

	party := &model.Party{
		ID:        partyID,
		PartyType: &partyType,
		Comment:   newParty.Comment,
	}

	log.Info().Str("party_id", partyID).Msg("Party created successfully")
	return party, nil
}
