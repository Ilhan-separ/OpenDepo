/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo.shared.application;

import com.opendepo.shared.domain.TenantId;
import com.opendepo.shared.domain.UserId;

public interface TenantContext {

    TenantId tenantId();

    UserId userId();
}
