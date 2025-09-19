package com.devsu.client.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Clients Microservice API",
                version = "1.0",
                description = "API para la gestión de clientes"
        ),
        servers = {
                @Server(url = "http://localhost:8081", description = "Servidor local")
        }
)
public class OpenApiConfig {

}
