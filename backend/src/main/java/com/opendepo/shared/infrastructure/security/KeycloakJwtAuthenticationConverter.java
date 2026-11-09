/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo.shared.infrastructure.security;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

public class KeycloakJwtAuthenticationConverter extends JwtAuthenticationConverter {

    public KeycloakJwtAuthenticationConverter() {
        setJwtGrantedAuthoritiesConverter(KeycloakJwtAuthenticationConverter::extractRealmRoles);
    }

    private static Collection<GrantedAuthority> extractRealmRoles(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
        if (realmAccess == null) {
            return List.of();
        }
        Object roles = realmAccess.get("roles");
        if (!(roles instanceof Collection<?> roleValues)) {
            return List.of();
        }
        return roleValues.stream()
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .<GrantedAuthority>map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .toList();
    }
}
