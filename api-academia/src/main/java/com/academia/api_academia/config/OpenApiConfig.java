package com.academia.api_academia.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Local Development Server");

        SecurityScheme basicAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic")
                .description("Autenticação HTTP Basic - Credenciais de administrador necessárias.");

        return new OpenAPI()
                .info(new Info()
                        .title("API Academia")
                        .version("0.0.1")
                        .description("API REST para gerenciamento de alunos e instrutores.\n\n" +
                                "Funcionalidades principais:\n" +
                                "* Cadastro e gerenciamento de alunos\n" +
                                "* Cadastro e gerenciamento de instrutores\n" +
                                "* Paginação e filtros nas consultas")
                        .contact(new Contact().name("Equipe Academia"))
                )
                .servers(List.of(localServer))
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                .components(new Components().addSecuritySchemes("basicAuth", basicAuth)
                );
    }
}
