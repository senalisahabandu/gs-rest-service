package com.example.restservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

import java.util.concurrent.ConcurrentHashMap;

@EnableSpringHttpSession
@Configuration
public class SessionConfig extends AbstractHttpSessionApplicationInitializer {
    @Bean
    public MapSessionRepository sessionRepository() {
        return new MapSessionRepository(new ConcurrentHashMap<>());
    }
}