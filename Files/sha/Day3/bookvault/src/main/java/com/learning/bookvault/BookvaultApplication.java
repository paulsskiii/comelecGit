package com.learning.bookvault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// This annotation triggers the Auto-Configuration scanning
@SpringBootApplication
public class BookvaultApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookvaultApplication.class, args);
        System.out.println("BookVault has started successfully!");
    }
}
