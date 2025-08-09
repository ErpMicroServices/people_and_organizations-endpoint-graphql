# People and Organizations GraphQL API Makefile
# Go microservice with standard targets

# Go parameters
GOCMD=go
GOBUILD=$(GOCMD) build
GOCLEAN=$(GOCMD) clean
GOTEST=$(GOCMD) test
GOGET=$(GOCMD) get
GOMOD=$(GOCMD) mod
GOFMT=$(GOCMD) fmt
BINARY_NAME=people-organizations-api
BINARY_UNIX=$(BINARY_NAME)_unix
BUILD_DIR=build
CMD_DIR=cmd/server

# Docker parameters
DOCKER_IMAGE=erpmicroservices/people-organizations-endpoint-graphql
DOCKER_TAG=latest
DOCKER_BUILD_ARGS=--build-arg BUILD_TIME=$(shell date -u +'%Y-%m-%dT%H:%M:%SZ')

# Version info
VERSION ?= $(shell git describe --tags --always --dirty 2>/dev/null || echo "v0.0.1-SNAPSHOT")
COMMIT ?= $(shell git rev-parse --short HEAD 2>/dev/null || echo "unknown")
BUILD_TIME = $(shell date -u +'%Y-%m-%dT%H:%M:%SZ')

# Build flags
LDFLAGS = -ldflags "-X main.version=$(VERSION) -X main.commit=$(COMMIT) -X main.buildTime=$(BUILD_TIME)"

.PHONY: help build clean test coverage lint fmt vet deps tidy generate run docker-build docker-run security-scan install-tools

## Default target
all: clean deps fmt vet lint build test

## Help target - displays available targets
help:
	@echo "Available targets:"
	@echo "  build         - Build the binary"
	@echo "  clean         - Remove build artifacts and clean cache"
	@echo "  test          - Run tests"
	@echo "  coverage      - Run tests with coverage report"
	@echo "  lint          - Run golangci-lint"
	@echo "  fmt           - Format code"
	@echo "  vet           - Run go vet"
	@echo "  deps          - Download dependencies"
	@echo "  tidy          - Tidy module dependencies"
	@echo "  generate      - Generate GraphQL code"
	@echo "  run           - Run the application locally"
	@echo "  docker-build  - Build Docker image"
	@echo "  docker-run    - Run Docker container"
	@echo "  security-scan - Run security scans"
	@echo "  install-tools - Install required tools"
	@echo "  all           - Run complete build pipeline"

## Build the binary
build:
	@echo "Building $(BINARY_NAME) version $(VERSION)..."
	@mkdir -p $(BUILD_DIR)
	$(GOBUILD) $(LDFLAGS) -o $(BUILD_DIR)/$(BINARY_NAME) -v ./$(CMD_DIR)

## Build for linux (useful for containers)
build-linux:
	@echo "Building $(BINARY_NAME) for Linux..."
	@mkdir -p $(BUILD_DIR)
	CGO_ENABLED=0 GOOS=linux GOARCH=amd64 $(GOBUILD) $(LDFLAGS) -a -installsuffix cgo -o $(BUILD_DIR)/$(BINARY_UNIX) -v ./$(CMD_DIR)

## Clean build artifacts and module cache
clean:
	@echo "Cleaning..."
	$(GOCLEAN)
	@rm -rf $(BUILD_DIR)
	@$(GOCMD) clean -cache -testcache -modcache

## Run tests
test:
	@echo "Running tests..."
	$(GOTEST) -v -race -timeout 30s ./...

## Run tests with BDD scenarios
test-bdd:
	@echo "Running BDD tests..."
	$(GOTEST) -v -race -timeout 60s -tags=bdd ./test/...

## Generate test coverage report
coverage:
	@echo "Running tests with coverage..."
	@mkdir -p $(BUILD_DIR)/coverage
	$(GOTEST) -race -coverprofile=$(BUILD_DIR)/coverage/coverage.out -covermode=atomic ./...
	$(GOCMD) tool cover -html=$(BUILD_DIR)/coverage/coverage.out -o $(BUILD_DIR)/coverage/coverage.html
	$(GOCMD) tool cover -func=$(BUILD_DIR)/coverage/coverage.out | tail -1
	@echo "Coverage report generated: $(BUILD_DIR)/coverage/coverage.html"

## Run golangci-lint
lint:
	@echo "Running linter..."
	@which golangci-lint > /dev/null || (echo "golangci-lint not found, run 'make install-tools'"; exit 1)
	golangci-lint run --timeout=5m

## Format code
fmt:
	@echo "Formatting code..."
	$(GOFMT) ./...

## Run go vet
vet:
	@echo "Running go vet..."
	$(GOCMD) vet ./...

## Download dependencies
deps:
	@echo "Downloading dependencies..."
	$(GOMOD) download

## Tidy module dependencies
tidy:
	@echo "Tidying module dependencies..."
	$(GOMOD) tidy
	$(GOMOD) verify

## Generate GraphQL code
generate:
	@echo "Generating GraphQL code..."
	@which gqlgen > /dev/null || $(GOGET) github.com/99designs/gqlgen
	$(GOCMD) run github.com/99designs/gqlgen generate

## Run the application locally (requires database)
run:
	@echo "Running $(BINARY_NAME) locally..."
	$(GOCMD) run $(LDFLAGS) ./$(CMD_DIR)

## Run with live reload (requires air)
run-dev:
	@echo "Running $(BINARY_NAME) with live reload..."
	@which air > /dev/null || (echo "air not found, install with: go install github.com/cosmtrek/air@latest"; exit 1)
	air

## Build Docker image
docker-build:
	@echo "Building Docker image $(DOCKER_IMAGE):$(DOCKER_TAG)..."
	docker build $(DOCKER_BUILD_ARGS) -t $(DOCKER_IMAGE):$(DOCKER_TAG) .
	docker tag $(DOCKER_IMAGE):$(DOCKER_TAG) $(DOCKER_IMAGE):$(VERSION)

## Run Docker container with compose
docker-run:
	@echo "Running Docker container with compose..."
	docker-compose up -d

## Stop Docker containers
docker-stop:
	@echo "Stopping Docker containers..."
	docker-compose down

## Security scan with gosec
security-scan:
	@echo "Running security scan..."
	@which gosec > /dev/null || (echo "gosec not found, run 'make install-tools'"; exit 1)
	gosec -fmt json -out $(BUILD_DIR)/gosec-report.json -stdout -severity medium ./...

## Install required tools
install-tools:
	@echo "Installing required tools..."
	$(GOGET) github.com/golangci/golangci-lint/cmd/golangci-lint@latest
	$(GOGET) github.com/securecodewarrior/gosec/v2/cmd/gosec@latest
	$(GOGET) github.com/99designs/gqlgen@latest
	$(GOGET) github.com/cosmtrek/air@latest

## Database operations
db-up:
	@echo "Starting database..."
	docker-compose -f docker-compose.yml up -d database

db-down:
	@echo "Stopping database..."
	docker-compose -f docker-compose.yml stop database

## Initialize database with migrations (requires separate database module)
db-migrate:
	@echo "Running database migrations..."
	@echo "Note: Run migrations from people_and_organizations-database module"

## Run pre-commit checks (used by Git hooks)
pre-commit: fmt vet lint test

## CI pipeline
ci: clean deps generate fmt vet lint test coverage security-scan build

## Show project info
info:
	@echo "Project: $(BINARY_NAME)"
	@echo "Version: $(VERSION)"
	@echo "Commit:  $(COMMIT)"
	@echo "Build:   $(BUILD_TIME)"
	@echo "Go:      $(shell $(GOCMD) version)"

## Check if tools are installed
check-tools:
	@echo "Checking required tools..."
	@which go > /dev/null || (echo "Go not found"; exit 1)
	@which docker > /dev/null || (echo "Docker not found"; exit 1)
	@which golangci-lint > /dev/null || echo "golangci-lint not found (run 'make install-tools')"
	@which gosec > /dev/null || echo "gosec not found (run 'make install-tools')"
	@echo "Tools check complete"