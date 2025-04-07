package com.DesafioItau.DesafioBackEndItau.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info().title("Desafio Itau API").version("1.0")
                        .description("Projeto desenvolvido como parte de um desafio técnico proposto pelo Itaú. Esse projeto, tem como foco a criacao de uma " +
                                        "API utilizando Java e Spring Boot, com o intuito de realizar transações financeiras e gerar estatísticas com base " +
                                        "nas últimas transações no intervalo de 60 segundos (1 minuto)."
                                )

        );

    }
}
