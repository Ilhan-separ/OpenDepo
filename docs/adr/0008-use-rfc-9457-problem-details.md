# ADR 0008: Use RFC 9457 Problem Details for API errors

- Status: Accepted
- Date: 2026-11-09

## Context

OpenDepo needs consistent, machine-readable API errors across validation failures, authentication and authorization errors, tenant context failures and unexpected server errors. Ad-hoc error payloads make client handling and observability harder.

## Decision

Use RFC 9457 Problem Details (`application/problem+json`) for API error responses.

Every error response includes:

- `type`, `title`, `status`, `detail` and `instance`.
- A stable application `code`.
- A `requestId` that is also returned in the `X-Request-Id` response header.
- A field-level `errors` collection for validation failures.

Security filters and application exception handlers use the same error contract. Internal exception details are logged but never exposed to clients.

## Consequences

### Positive

- Standard, predictable error handling for clients.
- Consistent behavior across controllers and security filters.
- Request identifiers support debugging and observability.
- Validation errors are machine-readable.

### Negative

- Clients must understand the Problem Details shape.
- Error codes must be maintained as a stable contract.
- Security handlers need explicit integration with the contract.

## Alternatives considered

- **Custom error envelope:** rejected because it duplicates an established standard and requires more documentation.
- **Spring Boot default error response:** rejected because it is not stable or detailed enough for API clients.

## References

- [RFC 9457](https://www.rfc-editor.org/rfc/rfc9457)
- [Phase 1 blueprint](../architecture/blueprint.md)
