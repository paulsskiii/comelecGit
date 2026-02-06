package com.codealong.registrar;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.codealong.registrar.entity.Student;
import com.codealong.registrar.repository.StudentRepository;

@SpringBootApplication
public class RegistrarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrarApplication.class, args);
	}

    @Bean
    public CommandLineRunner demo(StudentRepository repository) {
        return (args) -> {
            // Save a few students for testing
            repository.saveAll(Arrays.asList(
                new Student("Alice", "Smith", "alice@university.edu", "ACTIVE", 20),
                new Student("Bob", "Jones", "bob@gmail.com", "SUSPENDED", 22),
                new Student("Charlie", "Brown", "charlie@university.edu", "ACTIVE", 19),
                new Student("Diana", "Prince", "diana@yahoo.com", "GRADUATED", 25)
            ));
            System.out.println("--- Data Seeding Complete ---");
        };
    }
	
}
