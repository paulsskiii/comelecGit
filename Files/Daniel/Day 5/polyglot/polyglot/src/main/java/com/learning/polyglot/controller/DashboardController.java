package com.learning.polyglot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String showDashboard(Model model) {
        // We pass a dynamic value "Alex" to the model.
        // We want the result to be "Welcome, Alex!" or "¡Bienvenido, Alex!"
        model.addAttribute("username", "Alex");

        return "dashboard";
    }
}
