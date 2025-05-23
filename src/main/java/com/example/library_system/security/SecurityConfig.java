package com.example.library_system.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        // Inject UserDetailsService for authentication
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // Use BCrypt to encode passwords securely
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        // Set up authentication provider with user details and password encoder
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Configure HTTP security rules
        http
                // Disable CSRF protection for simplicity (adjust if needed)
                .csrf(csrf -> csrf.disable())
                // Define URL access rules
                .authorizeHttpRequests(auth -> auth
                        // Allow public access to login, register, and static resources
                        .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll()
                        // Restrict /admin/** URLs to users with ADMIN role
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                // Configure form login settings
                .formLogin(form -> form
                        .loginPage("/login")                  // Custom login page
                        .defaultSuccessUrl("/home", true)    // Redirect after successful login
                        .permitAll()
                )
                // Configure logout settings
                .logout(logout -> logout
                        .logoutUrl("/logout")                 // URL to trigger logout
                        .logoutSuccessUrl("/login?logout")   // Redirect after logout
                        .permitAll()
                );

        return http.build();
    }
}
