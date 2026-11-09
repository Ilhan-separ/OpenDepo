/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo.shared.application;

public class TenantContextException extends RuntimeException {

    public TenantContextException(String message) {
        super(message);
    }

    public TenantContextException(String message, Throwable cause) {
        super(message, cause);
    }
}
