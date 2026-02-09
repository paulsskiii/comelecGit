package com.comelec.smart_classroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.comelec.smart_classroom.service.WelcomeService;

@SpringBootApplication
public class SmartClassroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartClassroomApplication.class, args);
	}

	@Bean
	@Primary
	public WelcomeService customWelcomeService() {
		return () -> "Hola! (Custom User Message)";
	}

}
