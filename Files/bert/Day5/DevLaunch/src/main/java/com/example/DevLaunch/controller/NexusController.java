package com.example.DevLaunch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NexusController {
    @GetMapping("/fragments")
    public String home() {
        return "home_fragment"; // Loads home.html, which pulls in layout.html
    }

    @GetMapping("/team")
    public String team() {
        return "team_fragment";
    }

}
