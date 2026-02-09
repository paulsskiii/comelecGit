package com.learning.smartwallet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.beans.factory.annotation.Value;




@Configuration
@EnableWebSecurity // Hints to Spring that this is a security config
public class SecurityConfig {

    // We define the variable here, but we DON'T use it to build the user yet.
    @Value("${ADMIN_PASS:securePass}")
    private String adminPassword;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Hashing algorithm
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {


        // Define User 1: The Viewer
        UserDetails user = User.withUsername("john")
                .password(encoder.encode("password123")) // Hash the password
                .roles("USER")
                .build();

        // Define User 2: The Manager
        UserDetails admin = User.withUsername("boss")
                .password(encoder.encode(adminPassword))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for Postman testing
            .authorizeHttpRequests(auth -> auth

                // Allow anyone to view welcome (GET)
                .requestMatchers(HttpMethod.GET, "/welcome", "/welcome/**").permitAll()

                // Allow anyone with USER or ADMIN role to view expenses (GET)
                .requestMatchers(HttpMethod.GET, "/api/expenses/**").hasAnyRole("USER", "ADMIN")
                
                // Only ADMIN can create or modify expenses (POST)
                .requestMatchers(HttpMethod.POST, "/api/expenses/**").hasRole("ADMIN")                

                // Lock everything else down
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()); // Enable Basic Authentication

        return http.build();
    }



}
