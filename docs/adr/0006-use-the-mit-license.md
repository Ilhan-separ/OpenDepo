# ADR 0006: Use the MIT License

- Status: Accepted
- Date: 2026-11-09

## Context

OpenDepo is intended to be an open-source platform that individuals and companies can use, modify and integrate into commercial products. The license should be permissive and easy to understand.

## Decision

License OpenDepo under the MIT License.

- Keep the standard, unmodified MIT License text in the root `LICENSE`.
- Preserve the copyright and permission notices in copies and substantial portions.
- Do not add a Contributor License Agreement, custom commercial license or additional attribution restrictions.
- Add SPDX identifiers to project metadata and source files where appropriate.
- Do not claim ownership of third-party dependencies.
- Document separately licensed assets if any are added.

## Consequences

### Positive

- Permissive use in commercial and open-source products.
- Simple compliance requirements.
- Broad ecosystem compatibility.
- Encourages adoption and contribution.

### Negative

- Derivative works may remain closed source.
- Downstream users may not contribute improvements back.
- The project must rely on community norms and the ecosystem for sustainability.

## Alternatives considered

- **Apache License 2.0:** includes an explicit patent grant and trademark provisions, but adds more legal and notice requirements.
- **GNU AGPL v3:** ensures derivatives remain open, but is incompatible with the desired permissive commercial use.
- **Custom license:** rejected because it adds legal complexity and reduces adoption.

## References

- [MIT License](../../LICENSE)
- [Phase 1 blueprint](../architecture/blueprint.md)
