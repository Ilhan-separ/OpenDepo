/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo.shared.domain;

import java.util.Objects;
import java.util.UUID;

public record UserId(UUID value) {

    public UserId {
        Objects.requireNonNull(value, "value must not be null");
    }
}
