package com.openwebinars.rest.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("api-rest")
                .packagesToScan("com.openwebinars.rest.controller")
                .build();
    }

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST")
                        .description("API REST con Spring Boot de artistas")
                        .version("1.0")
                        .contact(new Contact()
                            .name("Jesús Rodríguez")
                            .email("jesusrodrimerc99@gmail.com")
                            .url("https://github.com/jesusrdguez")));
    }
}
