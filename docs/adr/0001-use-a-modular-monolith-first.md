# ADR 0001: Use a modular monolith first

- Status: Accepted
- Date: 2026-11-09

## Context

OpenDepo covers several business capabilities: identity, organizations, suppliers, catalog, procurement, approval, inventory, warehouse and fulfillment. Starting with many microservices would introduce network boundaries, distributed transactions, service discovery, deployment coordination and operational overhead before the domain boundaries are proven.

The project must still demonstrate strong internal modularity and prepare for selective service extraction later.

## Decision

Start with a Spring Boot modular monolith.

- One deployable application.
- Business modules with explicit responsibilities and boundaries.
- Modules communicate through public application APIs and domain events.
- Module boundaries are verified with Spring Modulith and ArchUnit.
- A module is extracted into a service only when operational or scaling requirements justify it.

## Consequences

### Positive

- Simple local development and deployment.
- Strong consistency where business rules require it.
- Easier refactoring while the domain model is still evolving.
- Lower operational cost.
- A single test and CI pipeline.

### Negative

- Requires discipline to prevent boundary erosion.
- Modules share one process and one database in Phase 1.
- Independent scaling is not available until extraction.
- Poorly designed module APIs can become accidental coupling points.

## Alternatives considered

- **Microservices from day one:** rejected because the domain boundaries are not yet proven and distributed complexity would slow delivery.
- **Single-layer monolith:** rejected because it would not demonstrate or protect module boundaries.

## References

- [Phase 1 blueprint](../architecture/blueprint.md)
- [Project architecture](../architecture/project-architecture.md)
