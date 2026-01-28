package com.example.demo.config;

//import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpeApiConfig {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
            .info(new Info()
                .title("REST APIs E-COMMERCE")
                .version("v1.0.0")
                .description("REST API's RESTful from 0 with Java, Spring boot and Docker")
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://swagger-ui/index.html")));
}
}
