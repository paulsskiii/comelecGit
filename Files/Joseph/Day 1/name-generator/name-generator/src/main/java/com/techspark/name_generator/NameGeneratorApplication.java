package com.techspark.name_generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// This annotation combines @Configuration, @EnableAutoConfiguration, and @ComponentScan
@SpringBootApplication
public class NameGeneratorApplication {

    public static void main(String[] args) {
        // This command starts the Tomcat server and the Spring Container
        SpringApplication.run(NameGeneratorApplication.class, args);
    }

}
