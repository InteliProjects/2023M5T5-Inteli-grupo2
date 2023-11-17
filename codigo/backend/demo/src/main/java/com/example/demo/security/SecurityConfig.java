package com.example.demo.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Configuration class for defining security-related settings in the
 * application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    /**
     * Configures the security filter chain, which defines security policies and
     * behavior.
     *
     * @param HttpSecurity The HttpSecurity object to configure.
     * @return A SecurityFilterChain representing the security filter chain
     *         configuration.
     * @throws Exception If there is an exception during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity HttpSecurity) throws Exception {
        return HttpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // .authorizeHttpRequests(authorize -> authorize
                //         .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                //         .requestMatchers(HttpMethod.POST, "/auth/logout").permitAll()
                //         .requestMatchers(HttpMethod.GET, "/auth/login").permitAll()
                //         .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                //         .anyRequest().authenticated())
                // .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // @Bean
    // public CorsConfigurationSource corsConfigurationSource() {
    // CorsConfiguration configuration = new CorsConfiguration();
    // configuration.setAllowedOrigins(Arrays.asList("*"));
    // configuration.setAllowedMethods(
    // Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE",
    // "CONNECT", "PATCH"));
    // configuration.setAllowCredentials(true);
    // UrlBasedCorsConfigurationSource source = new
    // UrlBasedCorsConfigurationSource();
    // source.registerCorsConfiguration("/**", configuration);
    // return source;
    // }

    /**
     * Configures the AuthenticationManager, which is responsible for
     * authentication.
     *
     * @param authenticationConfiguration The AuthenticationConfiguration object to
     *                                    configure.
     * @return An AuthenticationManager for handling authentication.
     * @throws Exception If there is an exception during configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configures the password encoder used for hashing and verifying passwords.
     *
     * @return A PasswordEncoder implementation (BCryptPasswordEncoder) for password
     *         handling.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
