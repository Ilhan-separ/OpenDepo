# ADR 0005: Use Testcontainers for integration tests

- Status: Accepted
- Date: 2026-11-09

## Context

OpenDepo depends on PostgreSQL behavior: constraints, transactions, migrations, locking and indexes. Integration tests that replace PostgreSQL with an in-memory database can hide real production issues.

## Decision

Use Testcontainers with real PostgreSQL instances for integration tests.

- Integration tests use a PostgreSQL container matching the supported major version.
- Spring Boot `@ServiceConnection` wires the container to the application.
- Tests that require Docker are skipped when Docker is unavailable during initial development.
- CI must provide a Docker-capable runner.
- Do not use H2 as a substitute for PostgreSQL integration tests.

## Consequences

### Positive

- Tests exercise real database behavior.
- Flyway migrations and SQL constraints are verified.
- Fewer production surprises.
- Testcontainers is widely supported in CI.

### Negative

- Docker is required for integration tests.
- Container startup adds test time.
- CI runners need sufficient resources and Docker access.

## Alternatives considered

- **H2 in PostgreSQL mode:** rejected because it does not faithfully reproduce PostgreSQL behavior.
- **Shared developer database:** rejected because tests become environment-dependent and hard to isolate.
- **Mocks for repositories:** rejected for integration-level verification because they would not test SQL, constraints or transactions.

## References

- [Phase 1 blueprint](../architecture/blueprint.md)
- [Testcontainers documentation](https://testcontainers.com/)
