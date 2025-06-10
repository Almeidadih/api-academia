package com.academia.api_academia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())// Desabilita CSRF para API REST
                .authorizeHttpRequests(authorize ->  authorize
                        .requestMatchers("/h2-console/**").permitAll()// Permite o acesso ao console H2
                                .anyRequest().authenticated() // Todas as outras requisições requerem autenticação
                         )
                .httpBasic(Customizer.withDefaults()); // Usa autenticação HTTP Basic

        //Para permitir o console H2 em iframes
        http.headers(headers -> headers.frameOptions(frameOptions ->  frameOptions.sameOrigin()));
        return http.build();
    }
}
