package com.learning.smart_welcome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class SmartWelcomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartWelcomeApplication.class, args);
	}

/* 	@Bean
    @Primary
    public WelcomeService customWelcomeService() {
        return () -> "Hola! (Custom User Message)";
    }
*/

}
