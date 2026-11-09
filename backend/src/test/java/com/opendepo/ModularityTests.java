/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModularityTests {

    private static final ApplicationModules MODULES = ApplicationModules.of(OpenDepoApplication.class);

    @Test
    void verifiesModuleStructure() {
        MODULES.verify();
    }
}
