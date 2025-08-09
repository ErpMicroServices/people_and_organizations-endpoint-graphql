# People and Organizations GraphQL API

A GraphQL microservice for managing people and organizations, built with Go and Apollo Federation support.

## Overview

This service provides GraphQL APIs for:
- Party management (people and organizations)  
- Contact mechanisms and addresses
- Party relationships and roles
- Communication events
- Cases and case management
- Facilities and locations

## Features

- **GraphQL API** with Apollo Federation support
- **PostgreSQL** database with pgx driver (no ORM)
- **BDD Testing** with Cucumber scenarios
- **Docker** containerization with multi-stage builds
- **Quality Gates** with comprehensive linting and security scans
- **CI/CD** with GitHub Actions

## Architecture

### Technology Stack

- **Language**: Go 1.21+
- **GraphQL**: gqlgen with Apollo Federation
- **Database**: PostgreSQL with pgx driver
- **Logging**: Zerolog structured logging
- **Testing**: Standard Go testing + Cucumber BDD
- **Containerization**: Docker multi-stage builds

### Project Structure

```
├── cmd/server/              # Main application entry point
├── internal/
│   ├── config/             # Configuration management
│   ├── db/                 # Database connection and health checks
│   ├── model/              # Generated GraphQL models
│   ├── resolver/           # GraphQL resolvers
│   └── service/            # Business logic and repository layer
├── graph/                  # Generated GraphQL server code
├── pkg/types/              # Shared types and interfaces
├── test/features/          # BDD test scenarios
├── .github/workflows/      # CI/CD pipelines
├── schema.graphql          # GraphQL schema definition
└── gqlgen.yml             # gqlgen configuration
```

## Getting Started

### Prerequisites

- Go 1.21 or higher
- PostgreSQL 15+
- Docker and Docker Compose
- Make

### Quick Start

1. **Clone and setup**:
   ```bash
   git clone <repository-url>
   cd people_and_organizations-endpoint-graphql
   ```

2. **Install dependencies**:
   ```bash
   make deps
   ```

3. **Start the database**:
   ```bash
   make db-up
   ```

4. **Run database migrations** (from people_and_organizations-database module):
   ```bash
   cd ../people_and_organizations-database
   npm install && npm run update_database
   ```

5. **Start the service**:
   ```bash
   make run
   ```

6. **Access GraphQL Playground**: http://localhost:8080

### Development Workflow

```bash
# Format, lint, and test
make fmt vet lint test

# Generate GraphQL code
make generate

# Run with live reload
make run-dev

# Full CI pipeline
make ci
```

### Docker Development

```bash
# Build Docker image
make docker-build

# Run with Docker Compose
make docker-run

# View logs
docker-compose logs -f people-organizations-api
```

## GraphQL Schema

The service exposes a comprehensive GraphQL schema for managing:

### Core Types

- `Party` - Base type for people and organizations
- `PartyType` - Classification of parties (Person, Organization, etc.)
- `PartyName` - Names associated with parties
- `PartyId` - Identifiers and credentials
- `ContactMechanism` - Phone numbers, emails, addresses
- `PartyRelationship` - Relationships between parties

### Federation Support

The schema includes Apollo Federation directives:
- `@key` - Entity key fields for federation
- `@shareable` - Fields that can be shared across services
- `@extends` - Extended types from other services

### Sample Query

```graphql
query GetParties($pageInfo: PageInfoInput!) {
  parties(pageInfo: $pageInfo) {
    edges {
      node {
        id
        partyType {
          description
        }
        names {
          edges {
            node {
              name
              nameType {
                description
              }
            }
          }
        }
        contactMechanisms {
          edges {
            node {
              contactMechanismEntity {
                endPoint
                contactMechanismType {
                  description
                }
              }
            }
          }
        }
      }
    }
    pageInfo {
      hasNextPage
      hasPreviousPage
    }
  }
}
```

## Testing

### Unit Tests

```bash
# Run all tests
make test

# Run with coverage
make coverage

# View coverage report in browser
open build/coverage/coverage.html
```

### BDD Integration Tests

BDD scenarios are defined in the `people_and_organizations-features` directory and test full integration with the database.

```bash
# Run BDD tests (requires database)
make test-bdd
```

### Test Scenarios

The BDD tests cover scenarios from the feature files:
- Party creation and management
- Party relationship management
- Communication event tracking
- Contact mechanism management
- Case management workflows

## Configuration

### Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `DB_HOST` | Database host | `localhost` |
| `DB_PORT` | Database port | `5432` |
| `DB_NAME` | Database name | `people_and_organizations_db` |
| `DB_USER` | Database user | `people_and_organizations_user` |
| `DB_PASSWORD` | Database password | `people_and_organizations_password` |
| `DB_SSLMODE` | SSL mode | `disable` |
| `SERVER_HOST` | Server host | `localhost` |
| `SERVER_PORT` | Server port | `8080` |
| `LOG_LEVEL` | Log level | `info` |

### Database Schema

The database schema is managed in the `people_and_organizations-database` module with Flyway migrations. Key tables include:

- `party` - Core party information
- `party_type` - Party type classifications
- `party_name` - Party names and aliases
- `party_contact_mechanism` - Contact information
- `party_relationship` - Inter-party relationships
- `communication_event` - Communication tracking
- `case_entity` - Case management

## Quality Assurance

### Code Quality

- **golangci-lint** - Comprehensive linting with 30+ linters
- **gosec** - Security vulnerability scanning
- **gofmt** - Code formatting
- **go vet** - Static analysis

### Quality Gates

- 85%+ test coverage requirement
- Zero high/critical security vulnerabilities  
- All linters must pass
- 100% BDD scenario pass rate

### Security

- Input validation on all GraphQL inputs
- SQL injection prevention with parameterized queries
- Security scanning in CI/CD pipeline
- Container security scanning with Trivy

## Deployment

### Docker

```bash
# Build production image
docker build -t people-organizations-api:latest .

# Run container
docker run -p 8080:8080 \
  -e DB_HOST=postgres \
  -e DB_PASSWORD=secret \
  people-organizations-api:latest
```

### Kubernetes

Example deployment files are available in the `k8s/` directory (when added).

### Health Checks

- **Health endpoint**: `GET /health`
- **GraphQL introspection**: Available in non-production environments
- **Prometheus metrics**: Available at `/metrics` (when configured)

## API Compatibility

This Go implementation maintains 100% API compatibility with the original Java implementation, ensuring seamless replacement in existing systems.

### Migration from Java

1. Shut down Java service
2. Deploy Go service with identical configuration
3. Verify health checks and connectivity
4. Run integration tests
5. Monitor metrics and logs

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Make changes following the style guide
4. Add/update tests as needed
5. Ensure all quality checks pass: `make ci`
6. Commit changes: `git commit -m 'Add amazing feature'`
7. Push branch: `git push origin feature/amazing-feature`
8. Create a Pull Request

### Code Style

- Follow standard Go conventions
- Use `gofmt` for formatting
- Add comprehensive comments for public APIs
- Include unit tests for new functionality
- Update BDD scenarios for new features

## Monitoring and Observability

### Structured Logging

All logs use structured JSON format with consistent fields:
- `level` - Log level (error, warn, info, debug)
- `timestamp` - ISO 8601 timestamp
- `message` - Human readable message
- `service` - Service name
- `version` - Service version
- Context-specific fields (request_id, party_id, etc.)

### Metrics

Key metrics exposed:
- Request count and latency
- Database connection pool statistics
- GraphQL operation metrics
- Error rates and types

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE](LICENSE) file for details.

## Support

For questions and support:
- Create an issue in the repository
- Check the documentation in the `docs/` directory
- Review the BDD feature specifications for behavior examples