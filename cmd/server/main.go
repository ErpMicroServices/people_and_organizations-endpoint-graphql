package main

import (
	"context"
	"fmt"
	"net/http"
	"os"
	"os/signal"
	"syscall"
	"time"

	"github.com/99designs/gqlgen/graphql/handler"
	"github.com/99designs/gqlgen/graphql/playground"
	"github.com/rs/zerolog"
	"github.com/rs/zerolog/log"

	"github.com/erpmicroservices/people-and-organizations-endpoint-graphql/graph"
	"github.com/erpmicroservices/people-and-organizations-endpoint-graphql/internal/config"
	"github.com/erpmicroservices/people-and-organizations-endpoint-graphql/internal/db"
	"github.com/erpmicroservices/people-and-organizations-endpoint-graphql/internal/resolver"
)

func main() {
	// Configure structured logging
	zerolog.TimeFieldFormat = zerolog.TimeFormatUnix
	zerolog.SetGlobalLevel(zerolog.InfoLevel)
	log.Logger = log.Output(zerolog.ConsoleWriter{Out: os.Stderr})

	log.Info().Msg("Starting People and Organizations GraphQL API")

	// Load configuration
	cfg := config.NewConfig()

	// Connect to database
	ctx := context.Background()
	dbConn, err := db.NewConnection(ctx, cfg.GetDatabaseURL())
	if err != nil {
		log.Fatal().Err(err).Msg("Failed to connect to database")
	}
	defer dbConn.Close()

	// Initialize resolver with database connection
	resolverInstance := &resolver.Resolver{
		DB: dbConn,
	}

	// Create GraphQL server
	srv := handler.NewDefaultServer(graph.NewExecutableSchema(graph.Config{
		Resolvers: resolverInstance,
	}))

	// Setup HTTP routes
	http.Handle("/", playground.Handler("GraphQL playground", "/query"))
	http.Handle("/query", srv)
	http.HandleFunc("/health", healthHandler(dbConn))

	// Start server
	server := &http.Server{
		Addr:         cfg.GetServerAddr(),
		ReadTimeout:  time.Duration(cfg.Server.Timeout) * time.Second,
		WriteTimeout: time.Duration(cfg.Server.Timeout) * time.Second,
		IdleTimeout:  2 * time.Duration(cfg.Server.Timeout) * time.Second,
	}

	// Start server in goroutine
	go func() {
		log.Info().
			Str("addr", server.Addr).
			Str("playground", fmt.Sprintf("http://%s/", server.Addr)).
			Str("graphql", fmt.Sprintf("http://%s/query", server.Addr)).
			Msg("Server starting")

		if err := server.ListenAndServe(); err != nil && err != http.ErrServerClosed {
			log.Fatal().Err(err).Msg("Server failed to start")
		}
	}()

	// Wait for interrupt signal to gracefully shutdown the server
	quit := make(chan os.Signal, 1)
	signal.Notify(quit, syscall.SIGINT, syscall.SIGTERM)
	<-quit

	log.Info().Msg("Server shutting down...")

	// Gracefully shutdown the server
	ctx, cancel := context.WithTimeout(context.Background(), 30*time.Second)
	defer cancel()

	if err := server.Shutdown(ctx); err != nil {
		log.Fatal().Err(err).Msg("Server forced to shutdown")
	}

	log.Info().Msg("Server exited")
}

// healthHandler provides a health check endpoint
func healthHandler(dbConn *db.Connection) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		ctx, cancel := context.WithTimeout(r.Context(), 5*time.Second)
		defer cancel()

		if err := dbConn.Health(ctx); err != nil {
			w.WriteHeader(http.StatusServiceUnavailable)
			w.Write([]byte("Database health check failed"))
			return
		}

		w.WriteHeader(http.StatusOK)
		w.Write([]byte("OK"))
	}
}
