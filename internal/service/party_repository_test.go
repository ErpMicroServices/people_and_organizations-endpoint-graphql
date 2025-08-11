package service

import (
	"context"
	"testing"

	"github.com/erpmicroservices/people-and-organizations-endpoint-graphql/internal/model"
)

func TestNewPartyRepository(t *testing.T) {
	// Test that NewPartyRepository creates a repository properly
	repo := NewPartyRepository(nil)
	if repo == nil {
		t.Fatal("NewPartyRepository returned nil")
	}
	
	if repo.db != nil {
		t.Error("Expected db to be nil when passed nil")
	}
}

func TestPartyRepository_GetPartyByID_NilDB(t *testing.T) {
	// Test that GetPartyByID handles nil database gracefully
	repo := NewPartyRepository(nil)
	
	defer func() {
		if r := recover(); r == nil {
			t.Error("Expected panic when calling GetPartyByID with nil db")
		}
	}()
	
	// This should panic since db is nil
	_, err := repo.GetPartyByID(context.Background(), "test-id")
	if err == nil {
		t.Error("Expected error when calling GetPartyByID with nil db")
	}
}

func TestPartyRepository_GetAllParties_NilDB(t *testing.T) {
	// Test that GetAllParties handles nil database gracefully
	repo := NewPartyRepository(nil)
	
	defer func() {
		if r := recover(); r == nil {
			t.Error("Expected panic when calling GetAllParties with nil db")
		}
	}()
	
	pageInfo := model.PageInfoInput{
		PageNumber: &[]int{0}[0],
		PageSize:   &[]int{10}[0],
	}
	
	// This should panic since db is nil
	_, err := repo.GetAllParties(context.Background(), pageInfo)
	if err == nil {
		t.Error("Expected error when calling GetAllParties with nil db")
	}
}

func TestPartyRepository_CreateParty_NilDB(t *testing.T) {
	// Test that CreateParty handles nil database gracefully
	repo := NewPartyRepository(nil)
	
	defer func() {
		if r := recover(); r == nil {
			t.Error("Expected panic when calling CreateParty with nil db")
		}
	}()
	
	newParty := model.NewParty{
		PartyTypeID: "PERSON",
		Comment:     &[]string{"Test comment"}[0],
	}
	
	// This should panic since db is nil
	_, err := repo.CreateParty(context.Background(), newParty)
	if err == nil {
		t.Error("Expected error when calling CreateParty with nil db")
	}
}