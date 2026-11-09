# ADR 0007: Use Keycloak for identity and access management

- Status: Accepted
- Date: 2026-11-09

## Context

OpenDepo needs authentication, OAuth2/OpenID Connect token issuance, role management and a path to organization-level identity features. Building and operating a custom identity provider would add security risk and distract from the procurement domain.

The backend must validate tokens and enforce tenant and role rules. Local development must be reproducible without manually configuring an identity provider.

## Decision

Use Keycloak as the identity and access management provider.

- The backend is an OAuth2 resource server and validates JWTs issued by Keycloak.
- Local development starts Keycloak through Docker Compose.
- A development realm is imported from `infrastructure/keycloak/realm/`.
- The realm defines the `opendepo-web` public client and realm roles `OWNER`, `ADMIN`, `PROCUREMENT_MANAGER`, `BUYER`, `APPROVER` and `VIEWER`.
- The active tenant is carried in the `org_id` token claim.
- The user identifier is the standard `sub` claim.
- Realm roles are mapped to Spring Security `ROLE_*` authorities.
- Database-backed organization membership validation is added with the organization module.

The development client, test user and credentials are local-only and must never be used in production.

## Consequences

### Positive

- Standards-based OAuth2/OIDC authentication.
- No custom password or token implementation.
- Central role and user administration.
- Reproducible local identity setup.
- Clear path to organization switching, MFA and identity federation.

### Negative

- Keycloak becomes a local and production dependency.
- The backend startup depends on OIDC metadata availability.
- Realm and client configuration must be maintained.
- Organization membership still requires application-level validation.

## Alternatives considered

- **Custom authentication:** rejected because it would create avoidable security and maintenance risk.
- **Spring Authorization Server:** viable, but requires building and operating the identity layer; Keycloak provides a more complete out-of-the-box platform.
- **Hosted identity provider:** possible later, but would add external dependency for local development and self-hosting.

## References

- [Phase 1 blueprint](../architecture/blueprint.md)
- [Project architecture](../architecture/project-architecture.md)
- [Keycloak documentation](https://www.keycloak.org/documentation)
