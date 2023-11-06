package com.cev.sircapcev.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class AuditorAwareImpl {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return Optional.ofNullable(username);
        };
    }
}
