package com.example.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Отключаем CSRF защиту:cite[1]:cite[9]
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/users/**").permitAll() // Разрешаем доступ без аутентификации к вашим API:cite[2]:cite[5]
                .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
            );
        return http.build();
    }
}