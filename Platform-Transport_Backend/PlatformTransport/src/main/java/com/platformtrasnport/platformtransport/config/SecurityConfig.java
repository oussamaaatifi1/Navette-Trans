package com.platformtrasnport.platformtransport.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/register/registerEmploye", "/auth/authenticate","/auth/register/registerAdmin").permitAll()
//                        .requestMatchers("/api/offres-transport/approved").hasAuthority("EMPLOYE")
                        .requestMatchers("/api/employeurs/**", "/auth/register/registerEmployeur").hasAuthority("ADMIN")
                        .requestMatchers("/api/reservations/**").hasAnyAuthority("EMPLOYEUR","EMPLOYE")
                        .requestMatchers("/api/utilisateurs/**","/api/offres-transport/approved").hasAnyAuthority("EMPLOYEUR", "EMPLOYE", "ADMIN")
                // Use hasAuthority here
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(Customizer.withDefaults());

        return http.build();
    }
}
