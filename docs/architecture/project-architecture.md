# OpenDepo — Open Source B2B Procurement & Supply Chain Management Platform

## 1. Project Overview

You are working on **OpenDepo**, an open-source, enterprise-grade B2B Procurement, Inventory, Warehouse and Supply Chain Management Platform.

The goal is to build a real-world application that can be used by companies to manage their procurement processes, suppliers, product catalogs, warehouses, inventory, purchase orders, approvals, shipments and business operations.

This is not a tutorial project or a simple CRUD application.

OpenDepo must be designed as a production-oriented software platform with an emphasis on:

- Object-Oriented Programming (OOP).
- SOLID principles.
- Clean Code.
- Clean Architecture.
- Hexagonal Architecture (Ports and Adapters).
- Domain-Driven Design (DDD).
- Design Patterns.
- Software Craftsmanship.
- Refactoring and maintainability.
- Unit, Integration, Contract and End-to-End Testing.
- Event-Driven Architecture.
- Distributed Systems.
- Fault Tolerance.
- Scalability.
- Loose Coupling.
- Observability.
- Infrastructure as Code.
- CI/CD and DevOps practices.

The project must demonstrate professional backend engineering practices and follow industry-standard software development principles.

The architecture should be extensible, maintainable, testable and suitable for real business requirements.

## 2. Project License and Open-Source Policy

OpenDepo must be distributed under the **MIT License (SPDX identifier: MIT)**.

The project should remain permissive and friendly to individual developers, companies, commercial products and other open-source projects.

### License Permissions

Users must be allowed to:

- Use OpenDepo for personal and commercial purposes.
- Modify the source code.
- Fork the repository.
- Redistribute original or modified versions.
- Integrate OpenDepo into proprietary or commercial applications.
- Use OpenDepo as part of SaaS products.
- Distribute binaries without publishing their entire application source code.
- Sublicense the software under different terms, subject to the MIT License conditions.

### Attribution Requirement

The original copyright notice and the complete MIT License permission notice must be preserved in all copies or substantial portions of the software.

Users and organizations distributing OpenDepo or substantial portions of its code must retain the original copyright and license notices.

There must be no additional requirement to:

- Publish modifications.
- Open-source derivative applications.
- Use the same license for derivative works.
- Provide public access to their modified source code.
- Display OpenDepo branding in their application's UI.
- Display a visible OpenDepo credit in marketing material.

Do not introduce additional licensing restrictions beyond the standard MIT License.

### License Implementation

Create a root-level `LICENSE` file containing the complete, unmodified standard MIT License text.

Use:

- SPDX Identifier: `MIT`
- License name: `MIT License`
- Copyright year: 2026
- Copyright holder: `OpenDepo contributors`

If the actual copyright holder is established differently, use the correct owner information rather than inventing a legal entity or individual.

The license file must contain the standard MIT terms, including:

1. Permission for use, copying, modification, merging, publication, distribution, sublicensing and sale.
2. Requirement to preserve copyright and permission notices.
3. Standard warranty disclaimer.
4. Standard limitation of liability.

Do not create a custom MIT-like license. Use the official standard MIT License text.

### Repository License Requirements

- Include `LICENSE` in the root directory.
- Include a license section in `README.md`.
- Add an MIT license badge to the README.
- Include `SPDX-License-Identifier: MIT` in appropriate project metadata where supported.
- Ensure the license is discoverable on GitHub.
- Include the license and copyright notices in applicable source and binary distributions.
- Preserve the copyright and license notices of third-party dependencies.
- Do not claim ownership of third-party libraries, source code or assets.
- Document any separately licensed components or assets.
- Avoid accidentally copying code from projects with incompatible licensing terms.

A suitable README statement:

"OpenDepo is open-source software licensed under the MIT License. You are free to use, modify, distribute and commercially integrate it, provided that the required copyright and license notices are preserved."

Keep the licensing process simple. Do not add a Contributor License Agreement (CLA), custom commercial license or additional attribution restrictions unless explicitly requested by project maintainers.

## 3. Technology Stack

Use the following technologies as the primary technology stack.

### Backend

- Java (prefer a supported LTS version).
- Spring Boot.
- Spring Security.
- Spring Data.
- Spring Validation.
- Spring Modulith.
- Spring Cloud components when justified.
- Hibernate / JPA.
- Flyway for database migrations.
- Maven or Gradle.
- OpenAPI / Swagger.

### Databases and Storage

- PostgreSQL: Primary transactional relational database.
- Couchbase: Flexible document-oriented data storage.
- Elasticsearch: Full-text search and search projections.
- Redis: Caching, rate limiting and temporary data.

Each database must have a clearly defined responsibility.

Do not use multiple databases simply to demonstrate their existence. Every technology must solve an actual business or architectural problem.

### Messaging and Event Streaming

- Apache Kafka.
- RabbitMQ.
- Debezium.
- Schema Registry when applicable.
- Avro, Protobuf or JSON Schema for event contracts.

Kafka and RabbitMQ must serve different architectural purposes.

### Containerization and Infrastructure

- Docker.
- Docker Compose.
- Kubernetes.
- Helm.
- Terraform.
- Kind or Minikube for local Kubernetes development.

### CI/CD and GitOps

- GitHub Actions.
- Argo CD.
- GitHub Container Registry.
- SonarQube or SonarCloud.
- Trivy.

### Testing

- JUnit 5.
- Mockito.
- AssertJ.
- Testcontainers.
- REST Assured.
- ArchUnit.
- Pact.
- Spring Modulith testing.
- Gatling or k6.
- Toxiproxy.
- Chaos Mesh when appropriate.

### Observability

- OpenTelemetry.
- Prometheus.
- Grafana.
- Loki.
- Tempo or Jaeger.
- Alertmanager.

Verify technology compatibility and official documentation before selecting exact dependency versions. Keep the stack based on compatible, stable releases.

## 4. Product Scope and Business Modules

OpenDepo is a multi-tenant B2B platform.

Multiple organizations must be able to use the same application while their business data remains isolated.

The initial product should include the following business modules.

### 4.1 Identity and Organization Management

Responsibilities:

- User registration and management.
- Organization creation.
- Organization membership.
- Multiple users per organization.
- Role-based access control (RBAC).
- Permission management.
- Organization-level authorization.
- Authentication and token validation.
- User invitation.
- Account activation and deactivation.
- Audit logging.

Consider integrating Keycloak for identity and access management.

Do not unnecessarily implement an entire identity provider from scratch.

### 4.2 Supplier Management

Responsibilities:

- Supplier registration.
- Supplier profiles.
- Supplier contact information.
- Supplier addresses.
- Supplier documents.
- Supplier contracts.
- Supplier categories.
- Supplier performance records.
- Supplier status management.
- Supplier evaluation and scoring.

### 4.3 Product Catalog

Responsibilities:

- Product management.
- Product categories.
- Product attributes.
- Product variants.
- Product images and attachments.
- Supplier-specific products.
- Product pricing.
- Product specifications.
- Product search.
- Product filtering.

Different product categories may contain completely different attributes.

For example, electronics products may have processor, RAM and storage attributes, while furniture may have material, dimensions and maximum weight.

Use Couchbase for flexible product documents and dynamic category-specific attributes where appropriate.

### 4.4 Procurement Management

Responsibilities:

- Purchase requisitions.
- Purchase orders.
- Purchase order items.
- Supplier quotation requests.
- Supplier quotations.
- Quote comparison.
- Purchase order approval.
- Order status management.
- Order cancellation.
- Purchase history.
- Purchase order revisions.
- Purchase order attachments.

### 4.5 Approval Workflow

Responsibilities:

- Configurable approval workflows.
- Multi-level approval.
- Department-based approval.
- Role-based approval.
- Approval and rejection.
- Approval comments.
- Approval history.
- Delegated approvals.
- Approval notifications.

Example:

A department employee creates a purchase requisition. The system sends it to the department manager. After approval, it may require another approval from the procurement manager.

The approval rules should be configurable instead of hardcoded into individual controllers.

### 4.6 Inventory Management

Responsibilities:

- Inventory tracking.
- Stock movements.
- Stock reservation.
- Stock release.
- Stock adjustments.
- Stock transfers.
- Low-stock alerts.
- Inventory history.
- Inventory valuation.
- Stock availability.
- Inventory reconciliation.

Inventory consistency is a critical business requirement.

Prevent concurrent operations from causing incorrect stock quantities.

### 4.7 Warehouse Management

Responsibilities:

- Multiple warehouse management.
- Warehouse locations.
- Storage areas.
- Receiving operations.
- Dispatch operations.
- Internal transfers.
- Warehouse employees.
- Goods receiving.
- Picking and packing.
- Inventory counting.

### 4.8 Fulfillment and Shipping

Responsibilities:

- Shipment creation.
- Shipment tracking.
- Shipping status management.
- Delivery confirmation.
- Carrier integrations.
- Shipment history.
- Partial shipments.
- Failed delivery handling.

Use Adapter and Strategy patterns for external shipping provider integrations.

### 4.9 Notification Management

Responsibilities:

- Email notifications.
- SMS notifications.
- Webhook notifications.
- In-app notifications.
- Notification preferences.
- Notification templates.
- Retry handling.
- Notification history.

### 4.10 Audit and Reporting

Responsibilities:

- Organization audit logs.
- User activity history.
- Order history.
- Inventory reports.
- Supplier performance reports.
- Procurement analytics.
- Operational dashboards.

Audit records for critical business operations should be immutable and traceable.

## 5. Architectural Strategy

Do not start with a large number of microservices.

Follow an evolutionary architecture:

**Phase 1: Modular Monolith**

Start with a well-structured Spring Boot Modular Monolith.

Each business module must have explicit responsibilities and boundaries.

Use Spring Modulith to validate module boundaries, dependencies and interactions.

The application should initially be deployable as a single application while maintaining strong internal modularity.

**Phase 2: Event-Driven Architecture**

Introduce Kafka and RabbitMQ when the relevant business workflows require asynchronous processing.

Introduce transactional outbox, idempotent consumers and event-driven integrations.

**Phase 3: Selective Microservices Extraction**

Extract independently scalable or operationally independent modules only when there is a clear architectural justification.

Potential candidates:

- Identity Service.
- Supplier Catalog Service.
- Procurement Service.
- Inventory Service.
- Fulfillment Service.
- Search Projection Service.
- Notification Worker.

Each extracted service must have a clearly defined ownership boundary.

**Phase 4: Distributed Production Architecture**

Introduce Kubernetes, service deployment strategies, infrastructure automation, centralized observability, resilience mechanisms and GitOps.

Do not prematurely introduce distributed complexity.

## 6. Clean Architecture and Code Organization

Follow Clean Architecture and Hexagonal Architecture principles.

Use clear separation between:

1. Presentation Layer.
2. Application Layer.
3. Domain Layer.
4. Infrastructure Layer.

### Domain Layer

Contains:

- Entities.
- Value Objects.
- Aggregates.
- Domain Services.
- Domain Events.
- Business Rules.
- Domain Exceptions.

The domain layer must not depend on Spring Framework, database implementations, messaging clients or external services.

Keep core business logic in plain Java.

### Application Layer

Contains:

- Use Cases.
- Application Services.
- Commands.
- Queries.
- Input Ports.
- Output Ports.
- Transaction boundaries.
- Application-level orchestration.

Example use cases:

- CreatePurchaseOrderUseCase.
- ApprovePurchaseOrderUseCase.
- ReserveInventoryUseCase.
- ReleaseInventoryUseCase.
- CreateSupplierUseCase.

Use cases must represent business operations instead of generic CRUD wrappers.

### Infrastructure Layer

Contains implementations of:

- PostgreSQL repositories.
- Couchbase repositories.
- Elasticsearch adapters.
- Kafka producers and consumers.
- RabbitMQ publishers and consumers.
- External API clients.
- Redis adapters.
- File storage adapters.

Use Ports and Adapters to prevent infrastructure dependencies from leaking into business logic.

### Presentation Layer

Contains:

- REST controllers.
- Request DTOs.
- Response DTOs.
- Validation.
- Exception handling.
- API configuration.
- Authentication and authorization integration.

Never expose persistence entities directly through REST endpoints.

Use explicit DTO mapping.

### General Code Organization Rules

- Organize code primarily by business domain or feature.
- Avoid giant service classes.
- Avoid god objects.
- Avoid excessive inheritance.
- Prefer composition over inheritance.
- Use constructor-based dependency injection.
- Prefer immutable objects where appropriate.
- Use Java records for suitable DTOs and immutable data structures.
- Keep classes and methods focused.
- Make dependencies explicit.
- Keep interfaces meaningful.
- Avoid unnecessary abstractions.
- Do not create interfaces for every class without a real design reason.
- Avoid circular module dependencies.
- Keep application and domain logic independent of framework details.

## 7. OOP, SOLID and Design Patterns

Apply OOP and SOLID principles consistently.

### SOLID

- Single Responsibility Principle.
- Open/Closed Principle.
- Liskov Substitution Principle.
- Interface Segregation Principle.
- Dependency Inversion Principle.

### Patterns to Apply When Appropriate

**Strategy Pattern**

Use for:

- Pricing strategies.
- Discount calculations.
- Shipping cost calculations.
- Supplier evaluation policies.

**Factory Pattern**

Use for:

- Payment provider construction.
- Shipping provider selection.
- Provider-specific integration creation.

**Adapter Pattern**

Use for:

- External shipping APIs.
- Payment gateways.
- Third-party ERP integrations.
- External notification providers.

**State Pattern**

Use for:

- Purchase order lifecycle.
- Shipment lifecycle.
- Approval status transitions.

**Specification Pattern**

Use for:

- Complex product filtering.
- Supplier eligibility rules.
- Procurement approval conditions.

**Builder Pattern**

Use for complex object creation when it improves readability.

**Observer / Domain Events**

Use for publishing and reacting to business events.

**Repository Pattern**

Use for domain persistence abstractions.

**Saga Pattern**

Use for distributed business transactions.

**CQRS**

Use when read and write requirements justify separating their models.

Do not apply patterns artificially.

Every design pattern must solve a real design problem and have a clear justification.

## 8. Database Architecture

Use a database-per-service ownership model when services become independently deployed.

In the initial Modular Monolith, maintain clear persistence ownership and module boundaries. Avoid direct cross-module database access.

### PostgreSQL

Use PostgreSQL for transactional business data:

- Organizations.
- Users and organization memberships.
- Suppliers.
- Purchase orders.
- Approvals.
- Inventory records.
- Stock movements.
- Warehouse records.
- Shipments.

Pay particular attention to:

- Transactions.
- Isolation levels.
- Optimistic locking.
- Pessimistic locking.
- Unique constraints.
- Foreign keys.
- Database indexes.
- Query optimization.
- Pagination.
- Connection pooling.
- Database migrations.
- EXPLAIN ANALYZE.
- N+1 query prevention.

Use Flyway for schema migrations.

Use appropriate concurrency controls to prevent incorrect inventory updates and duplicate business operations.

### Couchbase

Use Couchbase for flexible JSON documents, particularly:

- Dynamic product attributes.
- Supplier-specific catalog documents.
- Flexible category schemas.
- Product specifications that vary across categories.

Avoid storing transactional records in Couchbase when PostgreSQL provides a better consistency model for the operation.

### Elasticsearch

Use Elasticsearch for:

- Full-text product search.
- Supplier search.
- Product filtering.
- Search suggestions.
- Search-oriented order lookup.
- Advanced search and faceted filtering.

Treat Elasticsearch as a derived read model, not the primary transactional source of truth.

Search indexes must be rebuildable from authoritative data.

### Redis

Use Redis for:

- Frequently accessed cache data.
- API rate limiting.
- Short-lived temporary data.
- Distributed coordination only when justified.
- Cache invalidation and expiration strategies.

Avoid storing critical business data exclusively in cache.

## 9. Event-Driven Architecture

Use Apache Kafka for domain event streaming and RabbitMQ for background job processing where appropriate.

### Kafka Responsibilities

Kafka should handle:

- OrderCreated.
- PurchaseOrderApproved.
- PurchaseOrderCancelled.
- InventoryReserved.
- InventoryReleased.
- StockLevelChanged.
- ShipmentCreated.
- ShipmentDelivered.
- SupplierUpdated.

Independent consumers should be able to process relevant events without direct synchronous dependencies.

Potential consumers:

- Inventory projections.
- Search projections.
- Analytics.
- Notification orchestration.
- Audit processing.

Define meaningful topic names, partition strategies, retention policies and consumer groups.

Use aggregate identifiers as partition keys where per-aggregate ordering is required.

### RabbitMQ Responsibilities

Use RabbitMQ for asynchronous task delivery:

- Email sending.
- SMS sending.
- PDF generation.
- Webhook delivery.
- CSV imports and exports.
- Background report generation.
- Other retryable background operations.

Use appropriate exchanges, queues, routing keys, acknowledgements and dead-letter queues.

Define retry limits and backoff strategies.

### Event Contract Standards

Every business event should include:

- Event ID.
- Event type.
- Event version.
- Aggregate ID.
- Organization ID when applicable.
- Timestamp in UTC.
- Correlation ID.
- Causation ID.
- Payload.

Maintain versioned event schemas.

Do not expose internal persistence entities as public event contracts.

Introduce compatibility checks for schema changes.

### Transactional Outbox

Implement the Transactional Outbox Pattern for reliable event publishing.

Business changes and outbox records must be written in the same database transaction.

Use Debezium CDC and its Outbox Event Router when appropriate to publish committed outbox records to Kafka.

Do not assume that a successful database transaction guarantees a successful independent broker publish.

### Idempotent Consumers and Inbox

Assume that messages can be delivered more than once.

Implement deduplication and idempotent processing.

Use an Inbox or processed-message mechanism where appropriate.

Ensure repeated messages cannot cause:

- Duplicate stock reservations.
- Duplicate payment operations.
- Duplicate orders.
- Duplicate notifications where duplication is unacceptable.

### Saga Pattern

Use Saga for multi-service business operations.

Example order workflow:

1. Create purchase order.
2. Reserve inventory.
3. Process payment or credit validation.
4. Create shipment.
5. Complete order.

If payment fails, release inventory and cancel the order.

Implement both Choreography and Orchestration approaches in suitable scenarios and document the trade-offs.

Do not use distributed database transactions or assume that Kafka and PostgreSQL form one atomic transaction.

### CQRS and Eventual Consistency

Introduce CQRS for search, reporting and read-heavy workflows when beneficial.

Keep write models authoritative.

Update read projections asynchronously.

Document consistency expectations and acceptable delays.

Provide a mechanism to rebuild projections from source data and replay events safely.

## 10. Distributed Systems and Fault Tolerance

Design for failure.

Assume every external dependency, network connection and downstream service can become unavailable.

Implement and test:

- Circuit Breaker.
- Retry with exponential backoff.
- Randomized jitter.
- Timeout.
- Bulkhead.
- Rate limiting.
- Idempotency.
- Dead Letter Queue.
- Graceful shutdown.
- Health checks.
- Event replay.
- Backpressure handling.
- Consumer recovery.

Use Resilience4j where appropriate.

Design failure-handling policies according to business impact.

For example:

If Elasticsearch is unavailable:

- Purchase order creation must continue.
- PostgreSQL must remain the transactional source of truth.
- Kafka events must remain recoverable.
- Search projection processing must recover after Elasticsearch becomes available.
- Indexes must be rebuildable.

If RabbitMQ is unavailable, core procurement transactions should not become dependent on immediate notification delivery.

Do not block critical business transactions on non-critical downstream operations.

Define clear recovery and retry behavior for every asynchronous integration.

## 11. Security

Security must be considered from the beginning.

Implement:

- OAuth2 and OpenID Connect.
- JWT validation.
- Role-Based Access Control.
- Organization-level authorization.
- Multi-tenant data isolation.
- Input validation.
- Output encoding where appropriate.
- Rate limiting.
- Secure password handling through an identity provider.
- Secrets management.
- CORS configuration.
- Secure HTTP headers.
- Audit logging.
- Dependency vulnerability scanning.

Ensure that users cannot access another organization's data by manipulating IDs or request parameters.

Use tenant context consistently throughout application and persistence operations.

Evaluate PostgreSQL Row-Level Security and other tenant isolation approaches where appropriate.

Never commit credentials, private keys, tokens or production secrets to Git.

Follow OWASP guidance and maintain a security policy for vulnerability reporting.

## 12. Testing Strategy

Testing is a mandatory part of development, not an optional final phase.

### Unit Testing

Use JUnit, Mockito and AssertJ.

Test:

- Domain business rules.
- Aggregate invariants.
- Value Objects.
- Pricing calculations.
- Approval conditions.
- State transitions.
- Inventory operations.
- Error handling.

Domain tests should not require Spring context or infrastructure.

### Integration Testing

Use Testcontainers.

Test against real instances of:

- PostgreSQL.
- Kafka.
- RabbitMQ.
- Redis.
- Couchbase.
- Elasticsearch.

Test:

- Repository implementations.
- Database constraints.
- Transactions.
- Messaging.
- Event processing.
- Outbox publishing.
- Search projections.
- Serialization and deserialization.

Avoid replacing all infrastructure with mocks in integration tests.

### Architecture Testing

Use ArchUnit and Spring Modulith.

Validate:

- Layering rules.
- Module boundaries.
- Allowed dependencies.
- Absence of circular dependencies.
- Domain independence.
- Dependency direction.

### Contract Testing

Use Pact or appropriate schema compatibility tools.

Verify REST APIs and event contracts between independently evolving components.

### API and End-to-End Testing

Use REST Assured.

Test complete business scenarios:

- Organization creation.
- Supplier registration.
- Product registration.
- Purchase requisition.
- Approval.
- Purchase order creation.
- Inventory reservation.
- Shipment creation.
- Delivery completion.

### Performance Testing

Use Gatling or k6.

Measure:

- Throughput.
- Latency.
- p50, p95 and p99.
- Error rates.
- Concurrent requests.
- Database performance.
- Kafka consumer lag.
- Queue depth.

### Resilience Testing

Introduce controlled failures with Toxiproxy and, where appropriate, Chaos Mesh.

Test:

- Database unavailability.
- Kafka broker interruption.
- RabbitMQ interruption.
- Network latency.
- Packet loss.
- Slow external APIs.
- Consumer crashes.
- Repeated message delivery.

A feature should not be considered complete until relevant tests exist and pass.

## 13. Docker and Local Development

Create Dockerfiles using multi-stage builds.

Use:

- Lightweight runtime images.
- Non-root containers.
- Environment-based configuration.
- Health checks.
- Minimal production dependencies.

Use a comprehensive Docker Compose configuration for local development.

It should support the necessary infrastructure services:

- PostgreSQL.
- Couchbase.
- Elasticsearch.
- Redis.
- Kafka.
- RabbitMQ.
- Keycloak.
- OpenTelemetry Collector.
- Prometheus.
- Grafana.

Ensure local development can be started with a documented command.

Separate application configuration from infrastructure configuration.

Provide example environment variables without exposing real credentials.

## 14. Kubernetes and Terraform

Prepare Kubernetes deployments for the application and independently deployed services.

Use:

- Deployments.
- StatefulSets when appropriate.
- Services.
- Ingress.
- ConfigMaps.
- Secrets.
- Persistent Volumes.
- Namespaces.
- Horizontal Pod Autoscaler.
- Resource requests and limits.
- Readiness probes.
- Liveness probes.
- Startup probes.
- Pod Disruption Budgets.
- Rolling updates.
- Network Policies.

Use Helm for deployment packaging.

Keep liveness checks independent from transient downstream dependency failures.

### Terraform

Use Terraform to manage infrastructure as code.

Potential infrastructure:

- Network and VPC.
- Subnets.
- Kubernetes cluster.
- Container registry.
- Managed PostgreSQL.
- Storage.
- IAM.
- Firewall and network policies.

Organize Terraform into reusable modules.

Separate development, staging and production environments.

Use remote state and proper state locking when supported.

Never store credentials in Terraform files or repository history.

Infrastructure should be reproducible and version controlled.

## 15. CI/CD and GitOps

Use GitHub Actions for continuous integration.

A pull request should trigger:

1. Formatting checks.
2. Static analysis.
3. Compilation.
4. Unit tests.
5. Integration tests.
6. Architecture tests.
7. Dependency vulnerability checks.
8. Docker image build.
9. Container security scanning.
10. License and dependency license checks where appropriate.

After successful validation:

- Build and tag Docker images.
- Push images to GitHub Container Registry.
- Publish build artifacts.
- Update deployment configuration through a controlled release process.

Use Argo CD for GitOps-based Kubernetes deployments.

Deployment must be traceable and reproducible.

Separate development, staging and production configurations.

Do not automatically deploy unreviewed changes to production.

## 16. Observability

Use OpenTelemetry as the primary instrumentation standard.

Implement:

- Distributed tracing.
- Metrics.
- Structured logging.
- Correlation IDs.
- Trace propagation across asynchronous events.
- Health monitoring.

Use Prometheus and Grafana for metrics and dashboards.

Use Loki for centralized logs.

Use Tempo or Jaeger for distributed tracing.

Monitor:

- API latency.
- API error rate.
- Request throughput.
- JVM memory.
- Garbage collection.
- Database connection pools.
- Database query performance.
- Kafka consumer lag.
- RabbitMQ queue depth.
- Dead-letter messages.
- Worker processing times.
- Pod resource consumption.
- Failed business workflows.

Define meaningful service-level indicators and objectives.

Make it possible to trace a purchase order from the first API request through database persistence, Kafka processing, inventory reservation and notification delivery.

## 17. Software Craftsmanship and Code Quality Standards

Code quality must remain a priority throughout the project.

### Clean Code

- Choose meaningful names.
- Keep methods focused.
- Avoid unnecessary complexity.
- Avoid duplicated logic.
- Avoid magic numbers and strings.
- Keep business rules explicit.
- Prefer readable implementations over clever code.
- Avoid unnecessary comments that merely repeat the code.
- Document important decisions and non-obvious behavior.

### Refactoring

Refactor continuously.

- Remove code smells.
- Eliminate duplication.
- Simplify complicated conditionals.
- Improve abstractions when necessary.
- Extract domain concepts.
- Reduce coupling.
- Improve cohesion.
- Preserve behavior through tests.

Do not perform large speculative abstractions.

### Error Handling

- Use meaningful exception types.
- Separate domain exceptions from infrastructure exceptions.
- Avoid swallowing errors.
- Avoid empty catch blocks.
- Return consistent API error responses.
- Use appropriate HTTP status codes.
- Include trace/correlation identifiers in diagnostic context.
- Never expose internal stack traces or secrets to API clients.

### API Design

- Follow REST conventions.
- Use appropriate HTTP methods.
- Implement pagination and filtering.
- Use explicit request and response DTOs.
- Validate inputs.
- Maintain consistent naming.
- Document endpoints with OpenAPI.
- Consider API versioning and backward compatibility.

### Data and Time

- Use BigDecimal for financial calculations.
- Avoid floating-point types for monetary values.
- Use UTC for persisted timestamps.
- Model business states explicitly.
- Define clear transaction boundaries.
- Avoid exposing internal database identifiers as the only external contract identifiers.

### Static Analysis and Formatting

Use Spotless and a suitable static analysis solution.

Maintain strict compilation and test checks.

Treat relevant warnings and security findings seriously.

## 18. Documentation and Open-Source Standards

The repository must be understandable and accessible to external contributors.

Maintain:

- README.md.
- CONTRIBUTING.md.
- LICENSE.
- SECURITY.md.
- CODE_OF_CONDUCT.md.
- Architecture documentation.
- C4 diagrams.
- Sequence diagrams.
- Architecture Decision Records (ADR).
- API documentation.
- Event contract documentation.
- Database diagrams.
- Local development instructions.
- Deployment instructions.
- Operational runbooks.
- Third-party license notices where applicable.

Document important architectural decisions.

Examples:

- Why PostgreSQL is used for transactional data.
- Why Couchbase is used for flexible product documents.
- Why Elasticsearch is a derived search model.
- Why Kafka and RabbitMQ have separate responsibilities.
- Why a module should or should not become a microservice.
- How event replay and recovery work.
- Why MIT License was selected.
- How contributors and downstream users must preserve license notices.

A new contributor should be able to understand the architecture and run the project without relying on undocumented knowledge.

## 19. Repository Structure

Use a structure that supports the evolutionary architecture.

A suggested initial structure:

```text
opendepo/
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/opendepo/
│   │   │   │       ├── identity/
│   │   │   │       ├── organization/
│   │   │   │       ├── supplier/
│   │   │   │       ├── catalog/
│   │   │   │       ├── procurement/
│   │   │   │       ├── approval/
│   │   │   │       ├── inventory/
│   │   │   │       ├── warehouse/
│   │   │   │       ├── fulfillment/
│   │   │   │       ├── notification/
│   │   │   │       └── shared/
│   │   │   └── resources/
│   │   └── test/
│   │
│   └── pom.xml
│
├── services/
│   ├── search-projector/
│   └── notification-worker/
│
├── contracts/
│   ├── events/
│   ├── schemas/
│   └── api/
│
├── infrastructure/
│   ├── docker/
│   ├── kubernetes/
│   ├── helm/
│   ├── terraform/
│   └── monitoring/
│
├── docs/
│   ├── architecture/
│   ├── adr/
│   ├── diagrams/
│   └── runbooks/
│
├── .github/
│   ├── workflows/
│   ├── ISSUE_TEMPLATE/
│   └── PULL_REQUEST_TEMPLATE/
│
├── docker-compose.yml
├── README.md
├── CONTRIBUTING.md
├── SECURITY.md
├── CODE_OF_CONDUCT.md
├── LICENSE
└── .gitignore
```

This structure is a starting proposal, not a reason to force unnecessary modules into the initial release.

Within each business module, maintain clear separation between domain, application, infrastructure and presentation responsibilities.

## 20. Development Roadmap

Implement OpenDepo incrementally.

### Phase 1 — Foundation and Modular Monolith

- Set up Java and Spring Boot.
- Configure project structure.
- Establish coding standards.
- Implement initial domain modules.
- Introduce DDD fundamentals.
- Implement Identity and Organization Management.
- Configure PostgreSQL and Flyway.
- Implement Supplier Management.
- Implement Product Catalog.
- Implement initial Procurement workflows.
- Add Unit and Integration tests.
- Introduce Spring Modulith and ArchUnit.
- Prepare Docker Compose.
- Add MIT License and open-source repository documentation.

Deliverable: A working, testable Modular Monolith with clear business boundaries and a properly licensed repository.

### Phase 2 — Database Engineering

- Advanced PostgreSQL modeling.
- Transaction management.
- Concurrency control.
- Couchbase integration.
- Elasticsearch integration.
- Redis caching.
- Search indexing.
- Data consistency.
- Database performance tests.

Deliverable: A system using each database for a justified purpose.

### Phase 3 — Messaging and Event-Driven Architecture

- Kafka integration.
- RabbitMQ integration.
- Domain event contracts.
- Transactional Outbox.
- Debezium CDC.
- Inbox and idempotency.
- Retry policies.
- Dead Letter Queues.
- CQRS where appropriate.
- Saga workflows.

Deliverable: Reliable asynchronous business workflows.

### Phase 4 — Distributed Architecture

- Extract selected modules into independent services.
- Establish service boundaries.
- Introduce database-per-service ownership.
- Implement service communication.
- Introduce distributed tracing.
- Add resilience mechanisms.
- Implement contract tests.
- Test service failures.

Deliverable: Independently deployable services with documented failure and recovery behavior.

### Phase 5 — Infrastructure and Deployment

- Production Docker images.
- Kubernetes deployment.
- Helm charts.
- Terraform modules.
- GitHub Actions.
- Container registry.
- Argo CD.
- Staging environment.
- Controlled deployment process.

Deliverable: Reproducible deployment to Kubernetes.

### Phase 6 — Production Readiness

- Complete observability.
- Performance testing.
- Load testing.
- Fault injection.
- Security hardening.
- Backup and restore procedures.
- SLOs and alerts.
- Documentation and operational runbooks.
- Open-source contribution and release process.

Deliverable: A documented, observable, tested and resilient open-source platform.

## 21. Critical AI Agent Development Rules

You must follow these development rules throughout the project.

1. Do not generate the entire application in one giant implementation.
2. Do not skip architectural planning.
3. Do not introduce microservices prematurely.
4. Do not add technologies without a justified use case.
5. Do not sacrifice code quality for implementation speed.
6. Do not put business logic inside controllers.
7. Do not make domain logic dependent on Spring or persistence frameworks.
8. Do not expose database entities directly through APIs.
9. Do not allow uncontrolled cross-module dependencies.
10. Do not use distributed messaging as a substitute for understanding transaction boundaries.
11. Do not ignore idempotency, consistency or failure scenarios.
12. Do not mark work as completed without relevant tests.
13. Do not silently remove existing tests or weaken quality gates.
14. Do not introduce breaking API or event contract changes without documenting them.
15. Do not commit secrets or real environment credentials.
16. Do not create unnecessary abstractions or design patterns.
17. Do not ignore security, observability or operational requirements.
18. Do not modify the MIT License text or introduce additional licensing restrictions.
19. Do not remove original copyright or license notices from distributed copies.
20. Do not assume that third-party dependencies are covered by OpenDepo's MIT License.
21. Do not copy third-party code without checking its license compatibility.
22. Prefer official documentation and established engineering practices when making architectural decisions.

### Before Implementing Any Feature

Follow this process:

1. Understand the business requirement.
2. Identify the responsible domain module.
3. Identify relevant domain entities and business rules.
4. Define the use case.
5. Determine transaction boundaries.
6. Identify persistence requirements.
7. Determine whether synchronous or asynchronous communication is appropriate.
8. Identify relevant failure scenarios.
9. Define tests before or alongside implementation.
10. Implement the smallest maintainable solution.
11. Run relevant tests and static analysis.
12. Refactor where necessary.
13. Update documentation and architecture decisions.
14. Check whether the change affects licensing or third-party attribution requirements.

### Definition of Done

A feature is not complete until:

- Business requirements are satisfied.
- Code follows established architectural boundaries.
- Relevant unit tests exist.
- Relevant integration tests pass.
- Architecture tests pass.
- Validation and error handling are implemented.
- Security implications have been considered.
- Logging and observability are appropriate.
- Database migrations are included where needed.
- API or event documentation is updated.
- No relevant quality gate has been bypassed.
- Relevant license and copyright notices are preserved.

## 22. Initial Task: Architecture and Project Blueprint

Before writing application code, analyze the complete OpenDepo requirements and produce a detailed technical blueprint.

The initial response must contain:

1. Product scope and functional boundaries.
2. Domain-Driven Design analysis.
3. Bounded Context definitions.
4. Context Map.
5. Modular Monolith architecture.
6. Proposed package and module structure.
7. Initial database ownership and data model.
8. Main entities, aggregates and relationships.
9. PostgreSQL, Couchbase, Elasticsearch and Redis responsibilities.
10. Kafka topic and event design.
11. RabbitMQ exchange, routing key and queue design.
12. Transactional Outbox and Inbox architecture.
13. Saga workflow design.
14. Security and multi-tenancy architecture.
15. Testing strategy.
16. Docker Compose architecture.
17. Kubernetes deployment strategy.
18. Terraform infrastructure plan.
19. CI/CD and GitOps pipeline.
20. Observability architecture.
21. MIT licensing and repository compliance plan.
22. Key architecture decisions and trade-offs.
23. Incremental implementation roadmap.
24. Initial GitHub issues grouped by development phase.

For significant architectural decisions, create Architecture Decision Records.

Do not start implementing every module before the blueprint has been reviewed.

After presenting the blueprint, organize implementation into small, independently testable tasks.

For every development task, specify:

- Objective.
- Business context.
- Relevant module.
- Technical implementation requirements.
- Dependencies.
- Expected files or components.
- Unit tests.
- Integration tests.
- Acceptance criteria.
- Documentation changes.

Work incrementally and preserve all established architectural and code quality standards throughout the project.

**Final Objective:**

Build OpenDepo as a high-quality, open-source enterprise platform that demonstrates professional Java and Spring Boot backend engineering, clean architecture, distributed systems, reliable event-driven communication, database engineering, infrastructure automation, testing culture and production readiness.

The project should be useful as a real business application, remain permissively licensed under the MIT License, respect original copyright and license notices, welcome external contributors and serve as a practical demonstration of mature software engineering skills.