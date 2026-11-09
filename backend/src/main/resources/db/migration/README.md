# Database migrations

Flyway migrations live in this directory.

## Naming

Use the following format:

```text
V<version>__<short_description>.sql
```

Examples:

```text
V1__create_organization_tables.sql
V2__create_supplier_tables.sql
```

## Rules

- Migrations are immutable once merged.
- Never edit an applied migration; create a new one instead.
- Keep each migration focused and reviewable.
- Do not use Hibernate `ddl-auto` to create or modify application tables.
- Use UTC for persisted timestamps.
- Financial values must use PostgreSQL `numeric` and Java `BigDecimal`.
