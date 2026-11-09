/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo;

import org.springframework.boot.SpringApplication;

public class TestOpenDepoApplication {

    public static void main(String[] args) {
        SpringApplication.from(OpenDepoApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
