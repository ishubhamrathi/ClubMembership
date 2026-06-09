package com.club.membership.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI membershipOpenApi() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Club Membership Program APIs")
                                .description("REST APIs for managing plans, subscription and tier")
                                .version("v1.0")
                                .contact(
                                        new Contact()
                                                .name("Shubham Rathi")
                                )
                );
    }
}
