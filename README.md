# OpenDepo

**Open-source B2B procurement, inventory, warehouse and supply chain platform.**

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-25-orange.svg)](https://openjdk.org/projects/jdk/25/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1-6DB33F.svg?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Status](https://img.shields.io/badge/status-early_development-blue.svg)](#project-status)

OpenDepo helps organizations manage suppliers, product catalogs, purchase requisitions, approvals, inventory, warehouses and shipments from a single multi-tenant platform.

> OpenDepo is under active development. APIs, data models and modules may change before the first stable release.

## Modules

- Identity and organization management
- Supplier management
- Product catalog
- Procurement and purchase orders
- Configurable approval workflows
- Inventory and warehouse management
- Fulfillment and shipping
- Notifications, audit and reporting

## Tech stack

- Java 25 and Spring Boot 4
- Spring Modulith, Spring Data JPA and Flyway
- PostgreSQL
- Maven
- JUnit 5, AssertJ and Testcontainers
- Planned: Kafka, RabbitMQ, Couchbase, Elasticsearch, Redis and Kubernetes

## Quick start

Requirements: JDK 25 and Docker.

> Make sure `JAVA_HOME` points to JDK 25 if it is not your default JDK. With Homebrew on macOS:
> `export JAVA_HOME=/opt/homebrew/opt/openjdk@25`

```bash
# Start local PostgreSQL and wait until it is healthy
docker compose up -d --wait

# Run the backend
cd backend
./mvnw spring-boot:run
```

The local compose database listens on port **5433** to avoid conflicts with an existing PostgreSQL installation. The health endpoint is available at <http://localhost:8080/actuator/health>.

Local database settings can be overridden with the variables documented in [`.env.example`](.env.example). The backend reads `OPEN_DEPO_DB_URL`, `OPEN_DEPO_DB_USERNAME` and `OPEN_DEPO_DB_PASSWORD`.

## Project structure

```text
backend/              Spring Boot application
docs/architecture/    Architecture documentation
docker-compose.yml    Local infrastructure
```

## Documentation

- [Project architecture](docs/architecture/project-architecture.md)
- [Agent and contributor guide](AGENTS.md)

## Project status

The repository currently contains the project foundation: a Spring Boot modular monolith with PostgreSQL, Flyway and Spring Modulith. Business modules will be implemented incrementally according to the architecture roadmap.

## License

OpenDepo is open-source software licensed under the [MIT License](LICENSE). You are free to use, modify, distribute and commercially integrate it, provided that the required copyright and license notices are preserved.
