package com.comelec.smart_classroom;

import org.springframework.context.annotation.Configuration;

import com.comelec.smart_classroom.service.WelcomeService;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@Configuration
public class WelcomeAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(WelcomeService.class)
    public WelcomeService defaultWelcomeService() {
        return new WelcomeService() {
            @Override
            public String getWelcomeMessage() {
                return "Welcome! (Default Auto-Configured Message)";
            }
        };
    }
}
