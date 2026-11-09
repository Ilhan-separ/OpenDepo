/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;

@TestConfiguration(proxyBeanMethods = false)
public class TestJwtConfiguration {

    @Bean
    JwtDecoder jwtDecoder() {
        return token -> {
            throw new JwtException("The test JWT decoder is not configured.");
        };
    }
}
