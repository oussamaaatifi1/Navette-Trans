package com.platformtrasnport.platformtransport.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/register/registerEmploye", "/auth/authenticate","/auth/register/registerAdmin").permitAll()
                        .requestMatchers("/api/offres-transport/approved","/api/utilisateurs/all","/api/utilisateurs/{id}","/api/rapports/**").hasAuthority("EMPLOYE")
                        .requestMatchers("/api/employeurs/**", "/auth/register/registerEmployeur","/api/utilisateurs/**","/api/reservations/count-by-employe").hasAuthority("ADMIN")
                        .requestMatchers("/api/reservations/**").hasAnyAuthority("EMPLOYEUR", "EMPLOYE") // Use hasAuthority here
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
