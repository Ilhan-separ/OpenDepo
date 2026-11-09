/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureMockMvc
@Import({TestcontainersConfiguration.class, TestJwtConfiguration.class, SecurityTestEndpoints.class})
@Testcontainers(disabledWithoutDocker = true)
class SecurityIntegrationTests {

    private static final UUID USER_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");
    private static final UUID TENANT_ID = UUID.fromString("22222222-2222-2222-2222-222222222222");

    @Autowired
    private MockMvc mockMvc;

    @Test
    void rejectsUnauthenticatedRequestsWithProblemDetails() throws Exception {
        mockMvc.perform(get("/api/v1/me"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.code").value("UNAUTHORIZED"))
                .andExpect(jsonPath("$.requestId").isNotEmpty());
    }

    @Test
    void returnsCurrentContextForAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/api/v1/me")
                        .with(jwt().jwt(jwt -> jwt.subject(USER_ID.toString())
                                        .claim("org_id", TENANT_ID.toString())
                                        .claim("realm_access", Map.of("roles", List.of("OWNER"))))
                                .authorities(new SimpleGrantedAuthority("ROLE_OWNER"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(USER_ID.toString()))
                .andExpect(jsonPath("$.tenantId").value(TENANT_ID.toString()))
                .andExpect(jsonPath("$.roles[0]").value("OWNER"));
    }

    @Test
    void rejectsTokenWithoutTenantClaim() throws Exception {
        mockMvc.perform(get("/api/v1/me")
                        .with(jwt().jwt(jwt -> jwt.subject(USER_ID.toString())
                                        .claim("realm_access", Map.of("roles", List.of("OWNER"))))
                                .authorities(new SimpleGrantedAuthority("ROLE_OWNER"))))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.code").value("TENANT_CONTEXT_INVALID"));
    }

    @Test
    void enforcesMethodSecurity() throws Exception {
        mockMvc.perform(get("/api/v1/test/owner").with(authenticatedJwt("BUYER")))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.code").value("FORBIDDEN"));

        mockMvc.perform(get("/api/v1/test/owner").with(authenticatedJwt("OWNER")))
                .andExpect(status().isOk());
    }

    private org.springframework.test.web.servlet.request.RequestPostProcessor authenticatedJwt(String role) {
        return jwt().jwt(jwt -> jwt.subject(USER_ID.toString())
                        .claim("org_id", TENANT_ID.toString())
                        .claim("realm_access", Map.of("roles", List.of(role))))
                .authorities(new SimpleGrantedAuthority("ROLE_" + role));
    }
}
