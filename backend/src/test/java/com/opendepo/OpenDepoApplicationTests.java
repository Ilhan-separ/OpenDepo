/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@Testcontainers(disabledWithoutDocker = true)
class OpenDepoApplicationTests {

    @Test
    void contextLoads() {}
}
