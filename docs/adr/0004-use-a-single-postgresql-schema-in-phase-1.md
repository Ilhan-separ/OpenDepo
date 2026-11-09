# ADR 0004: Use a single PostgreSQL schema in Phase 1

- Status: Accepted
- Date: 2026-11-09

## Context

Phase 1 is a modular monolith with several business modules sharing one PostgreSQL database. Module ownership must be clear, but introducing a schema per module adds migration, connection and tooling complexity before the boundaries have stabilized.

## Decision

Use a single PostgreSQL schema (`public`) in Phase 1.

- Each table has exactly one owning module.
- Table names are module-specific and descriptive.
- Cross-module SQL joins are forbidden.
- Cross-module references use identifiers and public APIs.
- Module boundaries are enforced by Spring Modulith, ArchUnit, code review and integration tests.
- Revisit schema-per-module or database-per-service when modules are extracted.

## Consequences

### Positive

- Simple Flyway configuration.
- Simple local development and debugging.
- Easy cross-module test data setup.
- Lower migration overhead while the model evolves.

### Negative

- The database cannot enforce module ownership by itself.
- Accidental cross-module joins are possible without discipline.
- Stronger isolation requires an explicit migration later.

## Alternatives considered

- **Schema per module:** stronger ownership, but more complex Flyway and connection configuration for Phase 1.
- **Database per module:** rejected for a modular monolith; it adds operational overhead without the benefits of independently deployed services.

## References

- [Phase 1 blueprint](../architecture/blueprint.md)
- [ADR 0001](0001-use-a-modular-monolith-first.md)
