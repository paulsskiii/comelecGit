package com.example.UniversityRegistrar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UniversityRegistrarApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityRegistrarApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UniversityService universityService) {
		return args -> {
			universityService.seedData();
			universityService.printData();
		};
	}

}
