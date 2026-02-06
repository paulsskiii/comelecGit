package com.library.alexandria.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.library.model.ResourceNotFoundException;

@RestController
public class BookController {
    
    @GetMapping("/books/{id}")
    public String getBook(@PathVariable String id) {
        // Simulate a database check
        if ("123".equals(id)) {
            return "The Great Gatsby"; // The Happy Path
        } else if ("999".equals(id)) {
            // Simulate a system crash for the generic handler
            throw new RuntimeException("Database connection lost!");
        } else {
            // Signal a 404 using our custom exception
            throw new ResourceNotFoundException("Book with ID " + id + " not found.");
        }
    }
}
