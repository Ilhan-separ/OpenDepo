/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@TestConfiguration(proxyBeanMethods = false)
public class SecurityTestEndpoints {

    @RestController
    static class OwnerOnlyController {

        @GetMapping("/api/v1/test/owner")
        @PreAuthorize("hasRole('OWNER')")
        String ownerOnly() {
            return "ok";
        }
    }
}
