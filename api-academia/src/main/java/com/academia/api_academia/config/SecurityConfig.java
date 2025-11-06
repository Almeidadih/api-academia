package com.academia.api_academia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${app.security.admin-username:admin}")
    private String adminUsername;

    @Value("${app.security.admin-password:}")
    private String adminPassword;

    private final Environment env;

    public SecurityConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // Em ambientes de desenvolvimento (profile 'dev') permitimos a senha definida em application-dev.properties
        boolean isDev = Arrays.asList(env.getActiveProfiles()).contains("dev");

        String password = adminPassword;
        if (!isDev && (password == null || password.isBlank())) {
            // Em produção, obrigamos que a senha seja fornecida via variável de ambiente 'APP_ADMIN_PASSWORD' mapeada em 'app.security.admin-password'
            throw new IllegalStateException("Admin password not configured. Set APP_ADMIN_PASSWORD environment variable or configure app.security.admin-password in properties for dev.");
        }

        // Se estiver vazio (profile dev), mantemos a senha padrão 'password'
        if (password == null || password.isBlank()) {
            password = "password";
        }

        String encoded = passwordEncoder.encode(password);

        InMemoryUserDetailsManager mgr = new InMemoryUserDetailsManager();
        mgr.createUser(User.withUsername(adminUsername).password(encoded).roles("ADMIN").build());
        return mgr;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF é mantido desabilitado para esta API (se for usada por browser com sessões, reavalie e habilite)
        boolean isDev = Arrays.asList(env.getActiveProfiles()).contains("dev");
        http.csrf(csrf -> csrf.disable());

        if (isDev) {
            http.authorizeHttpRequests(authorize -> authorize
                            .requestMatchers("/actuator/**").permitAll()
                            .requestMatchers("/h2-console/**").permitAll()
                            .anyRequest().authenticated()
                    )
                    .httpBasic(Customizer.withDefaults());

            // Para permitir o console H2 em iframes (apenas útil em dev)
            http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
        } else {
            http.authorizeHttpRequests(authorize -> authorize
                            .anyRequest().authenticated()
                    )
                    .httpBasic(Customizer.withDefaults());
        }

        return http.build();
    }
}
