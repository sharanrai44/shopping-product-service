package com.onlineshop.productservice.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permit all requests
                ).csrf(AbstractHttpConfigurer::disable) // Disable CSRF (optional for stateless APIs)
                .formLogin(AbstractHttpConfigurer::disable) // Disable form login (optional)
                .httpBasic(AbstractHttpConfigurer::disable); // Disable basic auth (optional)
        return http.build();
    }


}