
package com.learning.nexus.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NexusController {

    @GetMapping("/")
    public String home() {
        return "home"; // Loads home.html, which pulls in layout.html
    }

    @GetMapping("/team")
    public String team() {
        return "team";
    }
}

