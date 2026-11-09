/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo.shared.presentation;

import com.opendepo.shared.application.TenantContext;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/me")
public class CurrentUserController {

    private static final String ROLE_PREFIX = "ROLE_";

    private final TenantContext tenantContext;

    public CurrentUserController(TenantContext tenantContext) {
        this.tenantContext = tenantContext;
    }

    @GetMapping
    CurrentUserResponse currentUser(Authentication authentication) {
        Set<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority -> authority.startsWith(ROLE_PREFIX))
                .map(authority -> authority.substring(ROLE_PREFIX.length()))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        return new CurrentUserResponse(
                tenantContext.userId().value(), tenantContext.tenantId().value(), roles);
    }

    record CurrentUserResponse(UUID userId, UUID tenantId, Set<String> roles) {}
}
