package com.example.library_system.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Configuration
public class WebConfig {

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        // Register Spring Security dialect to enable security tags in Thymeleaf templates
        return new SpringSecurityDialect();
    }
}
