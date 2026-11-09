/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo.shared.presentation;

import com.opendepo.shared.application.TenantContextException;
import com.opendepo.shared.infrastructure.web.ProblemDetailsFactory;
import com.opendepo.shared.infrastructure.web.RequestIdFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    private final ProblemDetailsFactory problemDetailsFactory;

    public ApiExceptionHandler(ProblemDetailsFactory problemDetailsFactory) {
        this.problemDetailsFactory = problemDetailsFactory;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handleValidation(MethodArgumentNotValidException exception, HttpServletRequest request) {
        ProblemDetail problem = problemDetailsFactory.create(
                request,
                HttpStatus.BAD_REQUEST,
                "Validation failed",
                "VALIDATION_ERROR",
                "One or more fields are invalid.");
        List<Map<String, String>> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(this::toFieldError)
                .toList();
        problem.setProperty("errors", errors);
        return problem;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    ProblemDetail handleMalformedRequest(Exception exception, HttpServletRequest request) {
        return problemDetailsFactory.create(
                request,
                HttpStatus.BAD_REQUEST,
                "Malformed request",
                "MALFORMED_REQUEST",
                "The request could not be parsed.");
    }

    @ExceptionHandler(NoResourceFoundException.class)
    ProblemDetail handleNotFound(NoResourceFoundException exception, HttpServletRequest request) {
        return problemDetailsFactory.create(
                request, HttpStatus.NOT_FOUND, "Not found", "NOT_FOUND", "The requested resource was not found.");
    }

    @ExceptionHandler(TenantContextException.class)
    ProblemDetail handleTenantContext(TenantContextException exception, HttpServletRequest request) {
        return problemDetailsFactory.create(
                request,
                HttpStatus.FORBIDDEN,
                "Tenant context is invalid",
                "TENANT_CONTEXT_INVALID",
                exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    ProblemDetail handleAccessDenied(AccessDeniedException exception, HttpServletRequest request) {
        return problemDetailsFactory.create(
                request, HttpStatus.FORBIDDEN, "Forbidden", "FORBIDDEN", "Access is denied.");
    }

    @ExceptionHandler(Exception.class)
    ProblemDetail handleUnexpected(Exception exception, HttpServletRequest request) {
        String requestId = (String) request.getAttribute(RequestIdFilter.REQUEST_ID_ATTRIBUTE);
        log.error("Unhandled exception [requestId={}]", requestId, exception);
        return problemDetailsFactory.create(
                request,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal server error",
                "INTERNAL_ERROR",
                "An unexpected error occurred.");
    }

    private Map<String, String> toFieldError(FieldError fieldError) {
        return Map.of(
                "field",
                fieldError.getField(),
                "message",
                Objects.requireNonNullElse(fieldError.getDefaultMessage(), "invalid"));
    }
}
