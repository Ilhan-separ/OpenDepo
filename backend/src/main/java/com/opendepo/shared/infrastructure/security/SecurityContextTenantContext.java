/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo.shared.infrastructure.security;

import com.opendepo.shared.application.TenantContext;
import com.opendepo.shared.application.TenantContextException;
import com.opendepo.shared.domain.TenantId;
import com.opendepo.shared.domain.UserId;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextTenantContext implements TenantContext {

    private static final String TENANT_CLAIM = "org_id";

    @Override
    public TenantId tenantId() {
        return new TenantId(parseUuid(jwt().getClaimAsString(TENANT_CLAIM), TENANT_CLAIM));
    }

    @Override
    public UserId userId() {
        return new UserId(parseUuid(jwt().getSubject(), "sub"));
    }

    private Jwt jwt() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken jwtAuthentication) {
            return jwtAuthentication.getToken();
        }
        throw new TenantContextException("No JWT authentication is available.");
    }

    private UUID parseUuid(String value, String claimName) {
        if (value == null || value.isBlank()) {
            throw new TenantContextException("Token is missing the required " + claimName + " claim.");
        }
        try {
            return UUID.fromString(value);
        } catch (IllegalArgumentException ex) {
            throw new TenantContextException("Token contains an invalid " + claimName + " claim.", ex);
        }
    }
}
