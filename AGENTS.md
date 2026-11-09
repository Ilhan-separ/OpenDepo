# AGENTS.md

Guidance for AI coding agents and contributors working on OpenDepo.

## Read first

Before changing code, read:

1. [`docs/architecture/blueprint.md`](docs/architecture/blueprint.md) — the concrete Phase 1 module, data, API and security plan.
2. [`docs/architecture/project-architecture.md`](docs/architecture/project-architecture.md) — the authoritative product and architecture vision.
3. [`docs/adr/README.md`](docs/adr/README.md) — accepted architecture decisions.
4. [`README.md`](README.md) — quick start and project status.

If a request conflicts with the blueprint, the architecture document or an accepted ADR, raise the conflict before implementing.

## Project summary

OpenDepo is an open-source, multi-tenant B2B procurement, inventory, warehouse and supply chain management platform.

The current phase is a **Spring Boot modular monolith**. The application must remain modular internally so that selected modules can be extracted into services later only when there is a clear operational need.

Do not introduce microservices, Kafka, RabbitMQ, Couchbase, Elasticsearch or Redis until the relevant roadmap phase and business use case justify them.

## Repository layout

```text
backend/                            Spring Boot application
  src/main/java/com/opendepo/       Application and business modules
  src/main/resources/               Configuration and Flyway migrations
  src/test/java/com/opendepo/       Tests
docs/architecture/                  Architecture documentation
docs/adr/                           Architecture decision records
docker-compose.yml                  Local development infrastructure
```

Business modules live under `com.opendepo.<module>`:

- `identity`
- `organization`
- `supplier`
- `catalog`
- `procurement`
- `approval`
- `inventory`
- `warehouse`
- `fulfillment`
- `notification`
- `shared`

Only create a module when it has an actual responsibility. Do not create empty modules to mirror the roadmap.

## Technology stack

- Java 25 (LTS)
- Spring Boot 4.1
- Spring Modulith
- Spring Web MVC, Validation, Data JPA, Actuator
- PostgreSQL and Flyway
- Maven with the Maven Wrapper
- JUnit 5, AssertJ, Mockito, Testcontainers
- Planned: Kafka, RabbitMQ, Couchbase, Elasticsearch, Redis, Kubernetes, Helm, Terraform

Use versions managed by the Spring Boot and Spring Modulith BOMs. Do not add dependencies without a concrete use case.

## Engineering principles

These principles are mandatory for all production and test code. When delivery pressure conflicts with them, fix the design before adding more behavior.

### SOLID

- **Single Responsibility:** A class must have one reason to change. Keep domain rules, use-case orchestration, persistence and HTTP concerns in separate classes.
- **Open/Closed:** Extend behavior through new strategies, policies or adapters instead of editing stable core logic with type checks and flags.
- **Liskov Substitution:** Every implementation of a port must honor the full contract, including nullability, exception behavior and side effects. Do not surprise callers.
- **Interface Segregation:** Prefer small, role-specific ports. Do not create interfaces without a real boundary or test seam.
- **Dependency Inversion:** High-level policy must not depend on low-level details. Domain and application code depend on abstractions they own; infrastructure implements them.

### Dependency inversion and ports

- Define ports in the domain or application layer and implement them in infrastructure.
- Use constructor injection only. Do not use field injection, service locators or hidden dependencies.
- Use cases depend on ports, not on JPA repositories, HTTP clients, messaging templates or Spring beans directly.
- Do not instantiate infrastructure inside use cases with `new`.
- Keep framework annotations out of domain classes. Transaction and security annotations belong at the application boundary.
- Dependencies point inward: presentation → application → domain; infrastructure → application/domain. Never the reverse.

### Composition over inheritance

- Prefer delegation, strategy, decorator and composition.
- Use inheritance only for a genuine is-a relationship with stable behavior; avoid deep hierarchies.
- Do not use inheritance merely to reuse utility code.
- Mark classes `final` unless they are explicitly designed for extension.

### Encapsulation

- Protect aggregate invariants inside the aggregate. Do not expose mutable fields or setters that allow invalid states.
- Model behavior as methods on entities and value objects; avoid anemic domain models.
- Return defensive copies or immutable views for collections.
- Value objects validate their own invariants at construction.
- Keep persistence concerns out of the domain model.

### Immutability

- Use records for DTOs, commands, queries, events and value objects where appropriate.
- Prefer final fields and unmodifiable collections.
- Return new instances instead of mutating shared state.
- Domain events and public API DTOs must be immutable.
- Avoid mutable static state and shared caches without explicit ownership.

### Domain-Driven Design

- Use the ubiquitous language of the business domain in class, method and event names.
- Model bounded contexts explicitly; do not share domain entities across modules.
- Use aggregates to protect transactional invariants; keep one transaction per aggregate.
- Use entities for identity, value objects for concepts without identity, and domain events for meaningful state changes.
- Use repositories as domain or application ports; do not expose query builders or persistence types.
- Use domain services only for rules that do not naturally belong to one entity.
- Do not create generic CRUD services as a substitute for domain behavior.
- Cross-aggregate and cross-module consistency is eventual; do not force one transaction across them.

### Hexagonal architecture

- Keep the domain core independent of Spring, JPA, HTTP, messaging and external APIs.
- Inbound adapters such as REST controllers and message listeners call application use cases.
- Outbound adapters such as JPA repositories, external clients and event publishers implement ports.
- Map transport DTOs to application commands or queries and domain types at the boundary.
- Do not leak persistence entities or HTTP types into the core.
- Domain and application tests must run without Spring or infrastructure.

### Code smells

Watch for and remove:

- Long methods or classes, deep nesting, long parameter lists and flag arguments.
- God objects, feature envy, inappropriate intimacy and shotgun surgery.
- Divergent change, duplicated code and primitive obsession.
- Anemic domain models, leaky abstractions and temporal coupling.
- Speculative generality, dead code, commented-out code and magic numbers or strings.
- Hidden dependencies, mutable static state and inconsistent error handling.

When a smell is detected, refactor before adding more behavior to the affected area.

### Refactoring

- Refactor continuously in small, behavior-preserving steps.
- Keep tests green; add characterization tests before refactoring untested behavior.
- Prefer renames, extract method/class, move behavior, introduce value objects, replace conditionals with polymorphism or strategy, and remove duplication.
- Do not perform large speculative rewrites.
- Keep refactoring commits separate from behavior changes when practical.
- Update documentation and ADRs when a refactor changes a public contract or architectural decision.

### Separation of concerns

- **Presentation:** HTTP handling, validation, DTO mapping, status codes and error translation.
- **Application:** use-case orchestration, transaction boundaries, authorization checks and port calls.
- **Domain:** business rules, invariants, state transitions and domain events.
- **Infrastructure:** persistence, external integrations, messaging and technical configuration.
- Do not put business logic in controllers, repositories, entity setters or configuration classes.
- Keep side effects at the edges; do not mix I/O with pure domain decisions.
- Cross-cutting concerns such as logging, metrics, transactions and security must be explicit and applied at the correct layer.

## Architecture rules

### Module boundaries

- Organize code by business capability, not by technical layer at the top level.
- A module owns its domain model, use cases, persistence and API.
- Other modules may interact only through the module's public application API or domain events.
- Never access another module's database tables or repositories directly.
- Avoid circular module dependencies.
- Use Spring Modulith and ArchUnit to enforce boundaries as modules are added.
- Follow accepted ADRs. Record significant new decisions as a new ADR instead of silently changing the architecture.

### Layers inside a module

Each module should separate these responsibilities:

```text
<module>/
  domain/           Entities, value objects, aggregates, domain events, policies
  application/      Use cases, commands, queries, ports, transaction boundaries
  infrastructure/   JPA repositories, external adapters, messaging adapters
  presentation/     REST controllers, request/response DTOs, exception handling
```

- The **domain** layer must be plain Java. It must not depend on Spring, JPA, messaging or external services.
- The **application** layer coordinates use cases and defines input/output ports.
- The **infrastructure** layer implements ports with PostgreSQL, messaging, search, cache or external APIs.
- The **presentation** layer exposes REST endpoints and maps DTOs explicitly. Never expose persistence entities directly.

### Use cases

- Model business operations as use cases, not generic CRUD wrappers.
- Keep transaction boundaries in the application layer.
- Keep business rules in the domain layer.
- Prefer small, focused classes and explicit dependencies.
- Use constructor injection. Do not use field injection.

### Multi-tenancy

- Every organization-owned record must be scoped to a tenant.
- Resolve tenant context from the authenticated principal, not from a client-supplied header by default.
- Never allow an organization to read or mutate another organization's data by changing an ID.
- Apply tenant filters consistently in application and persistence code.

## Coding standards

- Use Java records for immutable DTOs and value holders where appropriate.
- Use `BigDecimal` for monetary values. Never use `double` or `float` for money.
- Persist timestamps in UTC.
- Prefer composition over inheritance.
- Keep methods and classes focused; avoid god objects.
- Use meaningful names and avoid magic numbers and strings.
- Do not swallow exceptions or leave empty catch blocks.
- Keep domain exceptions separate from infrastructure exceptions.
- Return consistent API error responses with appropriate HTTP status codes.
- Do not expose stack traces, secrets or internal implementation details to API clients.
- Document non-obvious decisions in code comments or ADRs; do not repeat the code in comments.

## API conventions

- Follow REST conventions and use appropriate HTTP methods.
- Use explicit request and response DTOs.
- Validate inputs with Jakarta Bean Validation.
- Implement pagination and filtering for collection endpoints.
- Document public endpoints with OpenAPI when the API layer is introduced.
- Consider API versioning and backward compatibility.
- Do not return JPA entities from controllers.

## Data and persistence

- PostgreSQL is the primary transactional source of truth.
- Use Flyway for every schema change. Never use Hibernate `ddl-auto` to create or modify tables.
- Migrations are immutable after merge. Add a new migration instead of editing an applied one.
- Use database constraints, indexes and appropriate transaction isolation.
- Prevent lost updates and duplicate operations with optimistic/pessimistic locking or unique constraints as appropriate.
- Avoid N+1 queries; verify important queries with `EXPLAIN ANALYZE`.
- Do not store authoritative business data only in caches or search indexes.
- Plan for eventual consistency only where the architecture explicitly requires it.

Future data stores and their intended responsibilities:

- **Couchbase**: dynamic product documents and category-specific attributes.
- **Elasticsearch**: derived full-text search and filtering projections.
- **Redis**: caching, rate limiting and short-lived data.

Do not add these integrations before the corresponding feature needs them.

## Testing strategy

Testing is part of implementation, not a final cleanup step.

- **Unit tests**: domain rules, aggregates, value objects, pricing, approvals, state transitions and inventory rules. No Spring context.
- **Integration tests**: repositories, transactions, Flyway migrations, messaging and serialization with Testcontainers.
- **Architecture tests**: Spring Modulith and ArchUnit for boundaries, layering and dependency direction.
- **Contract tests**: REST and event contracts when independently evolving components appear.
- **End-to-end tests**: complete business flows when the API layer exists.
- **Performance and resilience tests**: add in later phases with justified scenarios.

A feature is not complete until relevant tests exist and pass.

## Build and run

Requirements: JDK 25 and Docker (for PostgreSQL and integration tests). Ensure `JAVA_HOME` points to JDK 25 before running Maven; the Maven Wrapper uses the JDK from `JAVA_HOME` or `PATH`.

```bash
# Start local PostgreSQL and Keycloak, then wait until they are healthy
docker compose up -d --wait

# Run the application
cd backend
./mvnw spring-boot:run

# Run tests
cd backend
./mvnw test

# Format Java sources
cd backend
./mvnw spotless:apply

# Run the full quality gate (format check, tests, architecture checks)
cd backend
./mvnw verify

# Package the application
cd backend
./mvnw package
```

Testcontainers tests require a running Docker daemon. When Docker is unavailable, the initial context test is skipped; do not treat that as proof that integration behavior works.

Local Compose starts PostgreSQL and Keycloak. The development realm is imported from `infrastructure/keycloak/realm/`; its credentials are for local development only.

Use the Maven Wrapper (`./mvnw`) rather than a system Maven installation.

Java sources are formatted with Spotless and Palantir Java Format. Run `./mvnw spotless:apply` before committing; `./mvnw verify` fails on unformatted code, test failures and architecture violations. CI runs the same verification on every pull request.

## Security

- Never commit credentials, tokens, private keys or production secrets.
- Use environment variables or a secrets manager for configuration.
- Follow OWASP guidance.
- Validate and authorize every request at both organization and role level.
- Use OAuth2/OpenID Connect and an identity provider when identity features are introduced; do not build an identity provider from scratch.
- Keep audit records for critical business operations immutable and traceable.

Current security foundation:

- The backend is an OAuth2 resource server.
- The local issuer is `http://localhost:8180/realms/opendepo`.
- `/api/**` requires a valid JWT. Health, OpenAPI and Swagger endpoints are public for local development.
- The user identifier comes from the `sub` claim.
- The active tenant comes from the `org_id` claim.
- Realm roles from `realm_access.roles` are mapped to `ROLE_*` authorities.
- Never trust an organization identifier supplied in a path, header, query parameter or request body without authorization validation.
- Error responses use RFC 9457 Problem Details and include a request identifier.
- Database-backed membership validation is added with the organization module. Until it exists, do not add tenant-owned business endpoints that assume membership has been checked.

## Git workflow

- Use small, focused commits.
- Use Conventional Commits:
  - `feat:` new behavior
  - `fix:` bug fix
  - `refactor:` internal change without behavior change
  - `test:` tests only
  - `docs:` documentation only
  - `chore:` build, tooling or maintenance
- Do not commit generated build output, IDE files or `.DS_Store`.
- Do not rewrite published history without explicit approval.
- Update documentation when architecture, setup or public behavior changes.

## Before implementing a feature

1. Understand the business requirement.
2. Identify the responsible module.
3. Identify entities, aggregates, value objects and business rules.
4. Define the use case and its inputs/outputs.
5. Determine transaction boundaries.
6. Decide whether communication is synchronous or asynchronous.
7. Identify failure scenarios and recovery behavior.
8. Define tests before or alongside implementation.
9. Implement the smallest maintainable solution.
10. Run tests and static checks.
11. Refactor where necessary.
12. Update documentation and architecture decisions.

## Definition of Done

A change is done only when:

- The business requirement is satisfied.
- Architectural boundaries are respected.
- Relevant unit tests exist.
- Relevant integration tests pass.
- Architecture tests pass.
- Validation and error handling are implemented.
- Security and tenant isolation implications are considered.
- Logging and observability are appropriate.
- A Flyway migration is included when the schema changes.
- API or event documentation is updated.
- No quality gate is bypassed.
- License and copyright notices are preserved.

## Code review checklist

Check every change against these questions:

- [ ] Does each class have a single, clear reason to change (SOLID/SRP)?
- [ ] Is extension handled without modifying stable core logic with flags or type checks (SOLID/OCP)?
- [ ] Do ports and implementations honor their contracts (SOLID/LSP)?
- [ ] Are interfaces small and role-specific (SOLID/ISP)?
- [ ] Do dependencies point inward, with infrastructure behind ports (Dependency Inversion)?
- [ ] Is composition preferred over unnecessary inheritance?
- [ ] Are aggregate invariants protected, and is the domain model free of anemic setters and public mutable state?
- [ ] Are DTOs, commands, events and value objects immutable where possible?
- [ ] Is the ubiquitous language used, and are bounded contexts and aggregates respected (DDD)?
- [ ] Do inbound and outbound adapters stay outside the domain core (Hexagonal Architecture)?
- [ ] Have obvious code smells been removed before adding more behavior?
- [ ] Was the change refactored in small, behavior-preserving steps with tests?
- [ ] Are presentation, application, domain and infrastructure concerns separated?

## Licensing

OpenDepo is licensed under the MIT License. See [`LICENSE`](LICENSE).

- Preserve the MIT copyright and permission notices.
- Do not add licensing restrictions beyond MIT.
- Do not copy code from projects with incompatible licenses.
- Third-party dependencies remain under their own licenses.
- Add SPDX headers to new source files where appropriate.

## Current bootstrap state

The repository currently contains only the project foundation. Do not assume that identity, supplier, catalog, procurement or other business features already exist. Add them incrementally according to the architecture roadmap, one independently testable task at a time.
