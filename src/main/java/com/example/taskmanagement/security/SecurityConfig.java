package com.example.taskmanagement.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic(Customizer.withDefaults()) // enable basic HTTP authentication
                .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()))
                .authorizeHttpRequests(auth -> auth
                        // 🔓 Public endpoints
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/error", // expose the /error endpoint
                                "/actuator/shutdown", // required for tests?
                                "/h2-console/**", // expose H2 console
                                "/api/accounts" // registration
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/token").authenticated()
                        .requestMatchers("/api/tasks/*/*").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/tasks").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/tasks").authenticated()
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable) // allow modifying requests from tests
                .sessionManagement(sessions ->
                        sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no session
                )
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder(KeyPair keyPair) {
        return NimbusJwtDecoder
                .withPublicKey((RSAPublicKey) keyPair.getPublic())
                .build();
    }

    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(KeyPair keyPair) {
        RSAKey rsaKey = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey(keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();

        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}