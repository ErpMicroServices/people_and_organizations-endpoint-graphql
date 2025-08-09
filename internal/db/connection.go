package db

import (
	"context"
	"fmt"

	"github.com/jackc/pgx/v5/pgxpool"
	"github.com/rs/zerolog/log"
)

// Connection wraps the pgxpool.Pool and provides database operations
type Connection struct {
	Pool *pgxpool.Pool
}

// NewConnection creates a new database connection
func NewConnection(ctx context.Context, databaseURL string) (*Connection, error) {
	// Configure connection pool
	config, err := pgxpool.ParseConfig(databaseURL)
	if err != nil {
		return nil, fmt.Errorf("failed to parse database URL: %w", err)
	}

	// Set pool configuration
	config.MaxConns = 25
	config.MinConns = 5

	// Connect to database
	pool, err := pgxpool.NewWithConfig(ctx, config)
	if err != nil {
		return nil, fmt.Errorf("failed to connect to database: %w", err)
	}

	// Test the connection
	if err := pool.Ping(ctx); err != nil {
		return nil, fmt.Errorf("failed to ping database: %w", err)
	}

	log.Info().Str("database", config.ConnConfig.Database).Msg("Database connected successfully")

	return &Connection{Pool: pool}, nil
}

// Close closes the database connection
func (c *Connection) Close() {
	if c.Pool != nil {
		c.Pool.Close()
		log.Info().Msg("Database connection closed")
	}
}

// Health checks if the database connection is healthy
func (c *Connection) Health(ctx context.Context) error {
	if c.Pool == nil {
		return fmt.Errorf("database connection is nil")
	}
	return c.Pool.Ping(ctx)
}
