package com.example.unimanager;

import com.example.unimanager.service.UniversityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UniManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniManagerApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(UniversityService universityService) {
        return args -> {
            universityService.seedData();
            universityService.printData();
        };
    }
}

