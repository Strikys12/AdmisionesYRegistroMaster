package com.admisionesYRegistro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;


@Configuration
public class SecurityConfig {

    // 🛑 Eliminamos el PasswordEncoder ya que no se está utilizando

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. HABILITAR CORS (Resuelve el error del navegador)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 2. DESHABILITAR CSRF (Resuelve el error 403 de Postman/POST)
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        // 3. Permite acceso público a los endpoints de Registro y Login
                        // 🛑 CORRECCIÓN CLAVE para el 403: Incluimos /api/loginAdministrativo/**
                        .requestMatchers(
                                "/api/loginEstudiantes/**",
                                "/api/registroAspirantes/**",
                                "/api/loginAdministrativo/**",
                                "/api/request/**"

                        ).permitAll()

                        // 4. Cualquier otra petición a la API debe estar autenticada
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Origen de tu Frontend
        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5501"));

        // Permite GET, POST, PUT, DELETE y OPTIONS (preflight)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Permite todos los encabezados
        configuration.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Aplica esta configuración CORS a todos los paths
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}