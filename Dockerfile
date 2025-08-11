# Multi-stage Docker build for People and Organizations GraphQL API
# Stage 1: Build the application
FROM golang:1.21-alpine AS builder

# Install build dependencies
RUN apk add --no-cache git ca-certificates tzdata make

# Set working directory
WORKDIR /app

# Copy go mod files first for better layer caching
COPY go.mod go.sum ./
RUN go mod download

# Copy source code
COPY . .

# Build arguments for metadata
ARG BUILD_TIME
ARG VERSION=v0.0.1-SNAPSHOT
ARG COMMIT=unknown

# Build the application with optimizations
RUN CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build \
    -a -installsuffix cgo -trimpath \
    -ldflags="-w -s -X main.version=${VERSION} -X main.commit=${COMMIT} -X main.buildTime=${BUILD_TIME}" \
    -o people-organizations-api ./cmd/server

# Stage 2: Create minimal runtime image
FROM scratch

# Copy CA certificates for HTTPS connections
COPY --from=builder /etc/ssl/certs/ca-certificates.crt /etc/ssl/certs/

# Copy timezone data
COPY --from=builder /usr/share/zoneinfo /usr/share/zoneinfo

# Copy the built binary
COPY --from=builder /app/people-organizations-api /people-organizations-api

# Set non-root user (optional, scratch doesn't have users)
USER 65534:65534

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD ["/people-organizations-api", "health"] || exit 1

# Set entrypoint
ENTRYPOINT ["/people-organizations-api"]

# Labels for metadata
LABEL maintainer="ERP MicroServices Team" \
      description="People and Organizations GraphQL API" \
      version="${VERSION}" \
      org.opencontainers.image.title="people-organizations-api" \
      org.opencontainers.image.description="GraphQL API for managing people and organizations" \
      org.opencontainers.image.vendor="ERP MicroServices" \
      org.opencontainers.image.version="${VERSION}" \
      org.opencontainers.image.created="${BUILD_TIME}" \
      org.opencontainers.image.source="https://github.com/ErpMicroServices/people-organizations-endpoint-graphql" \
      org.opencontainers.image.documentation="https://github.com/ErpMicroServices/people-organizations-endpoint-graphql/README.md"