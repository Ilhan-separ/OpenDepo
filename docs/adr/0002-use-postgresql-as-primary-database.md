# ADR 0002: Use PostgreSQL as the primary database

- Status: Accepted
- Date: 2026-11-09

## Context

OpenDepo needs a transactional source of truth for organizations, suppliers, products, purchase orders, approvals, inventory and shipments. These workflows require strong consistency, relational constraints, transactions, concurrency control and reliable reporting.

The platform also plans to use Couchbase, Elasticsearch and Redis for specific concerns, but those must not become the authoritative store for core business data.

## Decision

Use PostgreSQL as the primary transactional database.

- Use Flyway for all schema migrations.
- Use JPA/Hibernate for persistence adapters.
- Enforce tenant ownership in every tenant-scoped table.
- Use optimistic locking and database constraints where appropriate.
- Treat search indexes and caches as derived data.
- Never use Hibernate `ddl-auto` to create or modify application tables.

## Consequences

### Positive

- ACID transactions and mature concurrency controls.
- Rich constraints, indexes and query capabilities.
- Reliable migrations with Flyway.
- Strong fit for procurement and inventory consistency requirements.
- Mature operational tooling and ecosystem.

### Negative

- Less flexible than document stores for rapidly changing product attributes.
- Requires careful schema design and query optimization.
- Cross-module reporting must respect ownership boundaries.

## Alternatives considered

- **Couchbase as the primary store:** rejected because transactional business workflows need stronger consistency and relational constraints.
- **Elasticsearch as the primary store:** rejected because it is a derived read model, not a transactional source of truth.
- **Multiple databases from the start:** rejected because each store must solve a concrete problem and premature proliferation increases complexity.

## References

- [Phase 1 blueprint](../architecture/blueprint.md)
- [Project architecture](../architecture/project-architecture.md)
