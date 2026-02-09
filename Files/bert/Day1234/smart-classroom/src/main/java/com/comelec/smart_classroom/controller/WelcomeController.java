package com.comelec.smart_classroom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comelec.smart_classroom.service.WelcomeService;

@RestController // Tells Spring this handles HTTP requests
public class WelcomeController {

    private final WelcomeService welcomeService;

    // Spring injects whichever bean matches WelcomeService
    public WelcomeController(WelcomeService welcomeService) {
        this.welcomeService = welcomeService;
    }

    @GetMapping("/greet")
    public String greet() {
        return welcomeService.getWelcomeMessage();
    }

}
