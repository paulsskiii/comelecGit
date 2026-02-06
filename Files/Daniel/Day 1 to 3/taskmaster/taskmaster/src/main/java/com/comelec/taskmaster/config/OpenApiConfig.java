package com.comelec.taskmaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI taskMasterOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TaskMaster API")
                        .description("A comprehensive API for managing daily tasks and workflows.")
                        .version("1.0.0"));
    }
}
