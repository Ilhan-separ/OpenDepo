/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo.shared.domain;

import java.util.Objects;
import java.util.UUID;

public record TenantId(UUID value) {

    public TenantId {
        Objects.requireNonNull(value, "value must not be null");
    }
}
