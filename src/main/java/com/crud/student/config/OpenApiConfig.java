package com.crud.student.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        try {
            return new OpenAPI()
                .info(new Info()
                    .title("UEA Student Services API")
                    .version("1.0.0")
                    .description("API REST pour le système de gestion d'inscription aux cours")
                );
        } catch (Exception e) {
            // Fallback si problème de configuration
            return new OpenAPI()
                .info(new Info()
                    .title("UEA Student Services API")
                    .version("1.0.0")
                );
        }
    }
}

