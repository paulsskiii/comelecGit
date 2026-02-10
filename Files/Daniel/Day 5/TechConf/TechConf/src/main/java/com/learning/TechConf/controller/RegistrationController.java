package com.learning.TechConf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.learning.TechConf.model.Attendee;

@Controller
public class RegistrationController {

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // model.addAttribute("attendee", new Attendee());
        Attendee existingUser = new Attendee();
        existingUser.setFirstName("John");
        existingUser.setEmail("john@company.com");
        model.addAttribute("attendee", existingUser);

        return "register";
    }

    // ... inside the class
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute Attendee attendee, Model model) {
        // 1. The 'attendee' object now contains the data the user typed!
        System.out.println("New Registration: " + attendee.getEmail());

        // 2. We pass the FILLED object to the next view (badge.html) to display it
        // Note: We can reuse the same key "attendee" or use a new one.
        model.addAttribute("confirmedAttendee", attendee);

        return "badge";
    }
}