package com.comelec.Tech.Startup.Name.Generator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartUpController {

    @GetMapping("/")
    public String generateName() {
        // In a real app, this might come from a database.
        // For now, we return a simple string to the browser.
        return "Your new startup name is: 'Quantum Potato Solutions'";
    }

}