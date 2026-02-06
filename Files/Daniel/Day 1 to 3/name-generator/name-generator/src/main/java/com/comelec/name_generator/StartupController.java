package com.comelec.name_generator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Tells Spring this class handles web requests and returns data directly
@RestController
public class StartupController {

    // Maps HTTP GET requests at the root URL ("/") to this method
    @GetMapping("/")
    public String generateName() {
        // In a real app, this might come from a database. 
        // For now, we return a simple string to the browser.
        return "Your new startup name is: 'Quantum Potato Solutions'";
    }
}
