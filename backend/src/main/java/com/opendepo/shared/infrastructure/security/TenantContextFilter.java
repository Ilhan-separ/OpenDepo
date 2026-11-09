/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo.shared.infrastructure.security;

import com.opendepo.shared.application.TenantContext;
import com.opendepo.shared.application.TenantContextException;
import com.opendepo.shared.domain.TenantId;
import com.opendepo.shared.domain.UserId;
import com.opendepo.shared.infrastructure.web.ProblemDetailsWriter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

public class TenantContextFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(TenantContextFilter.class);
    private static final String TENANT_MDC_KEY = "tenantId";
    private static final String USER_MDC_KEY = "userId";

    private final TenantContext tenantContext;
    private final ProblemDetailsWriter problemDetailsWriter;

    public TenantContextFilter(TenantContext tenantContext, ProblemDetailsWriter problemDetailsWriter) {
        this.tenantContext = tenantContext;
        this.problemDetailsWriter = problemDetailsWriter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (!request.getRequestURI().startsWith("/api/")) {
            filterChain.doFilter(request, response);
            return;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof JwtAuthenticationToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            TenantId tenantId = tenantContext.tenantId();
            UserId userId = tenantContext.userId();
            MDC.put(TENANT_MDC_KEY, tenantId.value().toString());
            MDC.put(USER_MDC_KEY, userId.value().toString());
            filterChain.doFilter(request, response);
        } catch (TenantContextException ex) {
            log.warn("Rejected request with invalid tenant context: {}", ex.getMessage());
            problemDetailsWriter.write(
                    request,
                    response,
                    HttpStatus.FORBIDDEN,
                    "Tenant context is invalid",
                    "TENANT_CONTEXT_INVALID",
                    ex.getMessage());
        } finally {
            MDC.remove(TENANT_MDC_KEY);
            MDC.remove(USER_MDC_KEY);
        }
    }
}
