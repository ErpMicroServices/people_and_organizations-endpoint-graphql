# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this module.

## Module Overview

The `people_and_organizations-endpoint-graphql` module is a Spring Boot GraphQL API endpoint that provides comprehensive access to people and organizational data. It serves as the primary API gateway for party management, contact mechanisms, relationships, facilities, and communication events through a modern GraphQL interface.

## Technology Stack

- **Framework**: Spring Boot (Gradle-based)
- **Java Version**: Modern Java
- **Build Tool**: Gradle
- **API**: GraphQL with Spring GraphQL integration
- **Database**: PostgreSQL via Spring Data JPA
- **Container**: Docker Compose support
- **Testing**: Cucumber for BDD integration testing

## Project Structure

```
people_and_organizations-endpoint-graphql/
├── build.gradle                # Gradle build configuration
├── settings.gradle             # Gradle settings
├── docker-compose.yml          # Local development setup
├── graphql.config.yml          # GraphQL configuration
├── gradle/wrapper/             # Gradle wrapper files
└── src/
    ├── main/
    │   ├── java/org/erpmicroservices/peopleandorganizations/
    │   │   ├── backend/         # Business logic layer
    │   │   └── endpoint/        # GraphQL endpoint implementation
    │   └── resources/
    │       ├── application*.yml # Environment configurations
    │       └── graphql/         # GraphQL schema definitions
    ├── test/                   # Unit tests
    └── behaviorTest/           # BDD integration tests
        ├── java/               # Cucumber test runners
        └── resources/          # Feature files and test configurations
```

## Build and Development Commands

### Gradle Commands
```bash
# Build the project
./gradlew build

# Run the application
./gradlew bootRun

# Run unit tests
./gradlew test

# Run BDD tests
./gradlew behaviorTest

# Generate GraphQL schema
./gradlew generateSchema

# Clean build artifacts
./gradlew clean
```

### Docker Operations
```bash
# Start local development environment
docker-compose up

# Run in background
docker-compose up -d

# Stop services
docker-compose down
```

## GraphQL Implementation

### Schema Organization
The GraphQL schema is organized around core domain entities:
- **Party**: People and organizations
- **ContactMechanism**: Email, phone, postal addresses
- **PartyRelationship**: Relationships between parties
- **Facility**: Physical locations and facilities
- **Case**: Case management and tracking
- **CommunicationEvent**: Communications and interactions

### Key GraphQL Operations
- **Queries**: Comprehensive data retrieval with filtering and pagination
- **Mutations**: Party creation, updates, relationship management
- **Subscriptions**: Real-time updates for party and case changes
- **Field Resolvers**: Custom resolvers for computed fields and relationships

## Domain Model Coverage

### Core Entities
- **Party Management**: People, organizations, and party groups
- **Contact Information**: Email addresses, phone numbers, postal addresses
- **Relationships**: Employment, partnerships, family relationships
- **Facilities**: Offices, warehouses, retail locations
- **Cases**: Customer service cases, support tickets
- **Communication**: Emails, calls, meetings, notes

### Business Capabilities
- **Party Registration**: Create and manage people and organizations
- **Contact Management**: Maintain current contact information
- **Relationship Tracking**: Model complex organizational relationships
- **Facility Management**: Track physical locations and their purposes
- **Case Management**: Handle customer service and support cases
- **Communication History**: Track all interactions and communications

## Development Workflow

### Adding New GraphQL Operations
1. **Schema Design**: Define new types, queries, or mutations in GraphQL schema
2. **Backend Implementation**: Create or update business logic in backend layer
3. **Resolver Implementation**: Implement GraphQL resolvers in endpoint layer
4. **Testing**: Write unit tests and BDD integration tests
5. **Documentation**: Update GraphQL documentation and examples

### BDD Testing Integration
- **Feature Files**: Gherkin scenarios in `behaviorTest/resources/`
- **Step Definitions**: Java step implementations
- **Test Data**: Database test data setup and cleanup
- **GraphQL Testing**: Direct GraphQL query and mutation testing

## Testing Standards

### BDD Testing with Cucumber
- **Comprehensive Scenarios**: Cover all major business workflows
- **GraphQL Integration**: Test GraphQL operations end-to-end
- **Database Integration**: Verify data persistence and retrieval
- **Error Handling**: Test validation and error scenarios
- **Performance Testing**: Test query performance and optimization

### Test Organization
- **Domain-specific Features**: Party, case, communication event features
- **Integration Testing**: Database and GraphQL integration
- **Mock External Services**: Mock dependencies for isolated testing
- **Test Profiles**: Different configurations for various test environments

## Configuration Management

### Environment Profiles
- **Local Development**: `application-local.yml`
- **Testing**: `application-test.yml`
- **CI/CD**: `application-cicd.yml`
- **Production**: `application-prod.yml`

### GraphQL Configuration
- **Schema Location**: Centralized schema definitions
- **Resolver Configuration**: Custom resolver registration
- **Security Configuration**: Authentication and authorization
- **Performance Configuration**: Query complexity limits, timeout settings

## API Usage Examples

### Query Examples
```graphql
# Get party information with relationships
query {
  party(id: "123") {
    id
    name
    partyType
    contactMechanisms {
      type
      value
      purposeType
    }
    relationships {
      relatedParty {
        id
        name
      }
      relationshipType
      fromDate
      thruDate
    }
  }
}

# Search facilities by criteria
query {
  facilities(filter: {
    facilityType: WAREHOUSE
    location: { city: "Seattle" }
  }) {
    id
    name
    facilityType
    address {
      street
      city
      state
      postalCode
    }
  }
}
```

### Mutation Examples
```graphql
# Create a new person
mutation {
  createPerson(input: {
    firstName: "John"
    lastName: "Doe"
    contactMechanisms: [{
      type: EMAIL
      value: "john.doe@example.com"
      purposeType: PRIMARY
    }]
  }) {
    id
    fullName
    contactMechanisms {
      type
      value
    }
  }
}

# Add party relationship
mutation {
  addPartyRelationship(input: {
    fromPartyId: "123"
    toPartyId: "456"
    relationshipType: EMPLOYMENT
    fromDate: "2024-01-01"
  }) {
    id
    relationshipType
    fromDate
  }
}
```

## Performance Considerations

### GraphQL Optimization
- **DataLoader Pattern**: Efficient batch loading to prevent N+1 queries
- **Query Complexity Analysis**: Prevent overly complex queries
- **Field-level Caching**: Cache expensive computed fields
- **Pagination**: Implement cursor-based pagination for large datasets

### Database Optimization
- **JPA Optimization**: Efficient entity mapping and query generation
- **Connection Pooling**: Optimized database connection management
- **Query Indexing**: Database indexes for common GraphQL queries
- **Lazy Loading**: Strategic lazy loading configuration

## Security Implementation

### Authentication and Authorization
- **JWT Integration**: Token-based authentication
- **Role-based Access**: Field and operation-level authorization
- **Data Privacy**: Sensitive data access controls
- **Audit Logging**: Complete audit trail for data access and modifications

### Input Validation
- **Schema Validation**: GraphQL schema-level validation
- **Business Rule Validation**: Domain-specific validation rules
- **Sanitization**: Input sanitization and XSS prevention
- **Rate Limiting**: API rate limiting and abuse prevention

## Integration Points

### Database Integration
- **People and Organizations Database**: Primary data source
- **Complex Queries**: Advanced querying capabilities for organizational data
- **Data Consistency**: Transactional data management
- **Migration Support**: Database schema evolution support

### External Service Integration
- **Address Validation**: Integration with address validation services
- **Identity Verification**: Third-party identity verification services
- **Communication Services**: Email and SMS service integration
- **Document Management**: Integration with document storage systems

## Important Notes

- **Domain Expertise**: Requires deep understanding of party and organizational modeling
- **Data Privacy**: Handles personally identifiable information (PII) requiring careful security
- **Complex Relationships**: Supports complex organizational hierarchies and relationships
- **High Performance**: Must handle large volumes of party data efficiently
- **Integration Critical**: Central service for customer and organizational data across ERP
- **Compliance Important**: May need to support data privacy regulations (GDPR, CCPA)