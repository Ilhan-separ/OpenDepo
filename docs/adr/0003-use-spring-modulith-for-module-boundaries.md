# ADR 0003: Use Spring Modulith for module boundaries

- Status: Accepted
- Date: 2026-11-09

## Context

A modular monolith only works when module boundaries are explicit and enforceable. Without tooling, packages tend to become a flat structure, cross-module dependencies appear, and the application turns into an unstructured monolith.

Spring Modulith provides module detection, dependency verification, event publication and documentation support in a Spring Boot application.

## Decision

Use Spring Modulith to define and verify module boundaries.

- Package-by-business-capability under `com.opendepo`.
- Module root packages expose the public module API.
- Internal layers live in `domain`, `application`, `infrastructure` and `presentation` subpackages.
- Cross-module access is limited to public APIs and events.
- Use Spring Modulith application events for in-process decoupling.
- Persist event publications through the Modulith JPA registry.
- Add architecture tests that fail on boundary violations.

## Consequences

### Positive

- Boundaries are documented and testable.
- In-process events support loose coupling without a broker.
- The application remains refactorable while the domain evolves.
- Easier path to selective service extraction.

### Negative

- Developers must understand Spring Modulith conventions.
- Event publication adds infrastructure tables and operational considerations.
- Module APIs require maintenance.
- Framework-specific testing is required.

## Alternatives considered

- **Plain package structure without enforcement:** rejected because boundaries would rely only on code review.
- **ArchUnit only:** useful, but Spring Modulith adds module semantics and event support.
- **Microservices:** rejected for the reasons in ADR 0001.

## References

- [Phase 1 blueprint](../architecture/blueprint.md)
- [Spring Modulith documentation](https://docs.spring.io/spring-modulith/reference/)
