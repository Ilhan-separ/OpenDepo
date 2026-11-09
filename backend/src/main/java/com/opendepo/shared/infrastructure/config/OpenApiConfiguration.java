/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo.shared.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class OpenApiConfiguration {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OpenDepo API")
                        .description("B2B procurement, inventory, warehouse and supply chain platform")
                        .version("v1"));
    }
}
