package com.devsu.account.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Account Microservice API",
                version = "1.0",
                description = "API para la gestión de cuentas"
        ),
        servers = {
                @Server(url = "http://localhost:8082", description = "Servidor local")
        }
)
public class OpenApiConfig {

}
