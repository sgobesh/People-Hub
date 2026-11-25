package com.aihrms.backend.security.config;

import com.aihrms.backend.security.jwt.JwtAuthenticationFilter;
import com.aihrms.backend.security.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            CustomUserDetailsService customUserDetailsService) {

        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.customUserDetailsService = customUserDetailsService;
    }

    // =====================================================
    // Password Encoder
    // =====================================================

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // =====================================================
    // Authentication Manager
    // =====================================================

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }

    // =====================================================
    // CORS
    // =====================================================

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of("http://localhost:5173"));

        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                ));

        configuration.setAllowedHeaders(
                List.of("*"));

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    // =====================================================
    // Security Filter Chain
    // =====================================================

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

                .cors(cors ->
                        cors.configurationSource(corsConfigurationSource()))

                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                
.authorizeHttpRequests(auth -> auth

        // Public React frontend files
        .requestMatchers(
                "/",
                "/index.html",
                "/assets/**",
                "/vite.svg",
                "/favicon.ico",
                "/error"
        ).permitAll()

        // Public APIs
        .requestMatchers(
                "/api/auth/**",
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/h2-console/**"
        ).permitAll()

        // Protected APIs
        .requestMatchers("/api/profile/**").authenticated()
        .requestMatchers("/api/dashboard/**").authenticated()
        .requestMatchers("/api/employees/**").authenticated()
        .requestMatchers("/api/departments/**").authenticated()
        .requestMatchers("/api/attendance/**").authenticated()
        .requestMatchers("/api/leaves/**").authenticated()
        .requestMatchers("/api/payroll/**").authenticated()
        .requestMatchers("/api/jobs/**").authenticated()
        .requestMatchers("/api/candidates/**").authenticated()
        .requestMatchers("/api/notifications/**").authenticated()
        .requestMatchers("/api/settings/**").authenticated()

        .anyRequest().authenticated()
)


                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .headers(headers ->
                        headers.frameOptions(frame ->
                                frame.disable()));

        return http.build();
    }

}