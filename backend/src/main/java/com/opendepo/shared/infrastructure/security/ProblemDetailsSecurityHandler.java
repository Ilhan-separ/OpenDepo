/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo.shared.infrastructure.security;

import com.opendepo.shared.infrastructure.web.ProblemDetailsWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class ProblemDetailsSecurityHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    private final ProblemDetailsWriter problemDetailsWriter;

    public ProblemDetailsSecurityHandler(ProblemDetailsWriter problemDetailsWriter) {
        this.problemDetailsWriter = problemDetailsWriter;
    }

    @Override
    public void commence(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        problemDetailsWriter.write(
                request,
                response,
                HttpStatus.UNAUTHORIZED,
                "Unauthorized",
                "UNAUTHORIZED",
                "Authentication is required.");
    }

    @Override
    public void handle(
            HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException {
        problemDetailsWriter.write(
                request, response, HttpStatus.FORBIDDEN, "Forbidden", "FORBIDDEN", "Access is denied.");
    }
}
