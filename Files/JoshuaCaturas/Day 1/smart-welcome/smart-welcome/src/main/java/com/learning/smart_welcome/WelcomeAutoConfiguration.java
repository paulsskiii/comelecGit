package com.learning.smart_welcome;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Marks this as a configuration source
public class WelcomeAutoConfiguration {
    // "The Opinion": If no one else defined a WelcomeService, use this one.
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
