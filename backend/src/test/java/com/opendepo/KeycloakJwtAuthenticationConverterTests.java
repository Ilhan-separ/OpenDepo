/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo;

import static org.assertj.core.api.Assertions.assertThat;

import com.opendepo.shared.infrastructure.security.KeycloakJwtAuthenticationConverter;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

class KeycloakJwtAuthenticationConverterTests {

    private final KeycloakJwtAuthenticationConverter converter = new KeycloakJwtAuthenticationConverter();

    @Test
    void mapsRealmRolesToSpringAuthorities() {
        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "none")
                .subject("11111111-1111-1111-1111-111111111111")
                .claim("realm_access", Map.of("roles", List.of("OWNER", "BUYER")))
                .build();

        JwtAuthenticationToken authentication = (JwtAuthenticationToken) converter.convert(jwt);

        assertThat(authentication.getAuthorities())
                .extracting(GrantedAuthority::getAuthority)
                .contains("ROLE_OWNER", "ROLE_BUYER");
    }

    @Test
    void returnsNoAuthoritiesWhenRealmRolesAreMissing() {
        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "none")
                .subject("11111111-1111-1111-1111-111111111111")
                .build();

        JwtAuthenticationToken authentication = (JwtAuthenticationToken) converter.convert(jwt);

        assertThat(authentication.getAuthorities())
                .extracting(GrantedAuthority::getAuthority)
                .noneMatch(authority -> authority.startsWith("ROLE_"));
    }
}
