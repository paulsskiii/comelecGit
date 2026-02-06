package com.example.unimanager;

import com.example.unimanager.service.UniversityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UnimanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnimanagerApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UniversityService universityService) {
		return args -> {
			universityService.seedData();
			universityService.printData();
		};
	}
}
