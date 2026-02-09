package com.example.DevLaunch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import com.example.DevLaunch.model.Attendee;

@Controller
public class RegistrationController {
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // CONCEPT: We pass an empty object to the form.
        // The form will "bind" its input fields to this object's properties.
        model.addAttribute("attendee", new Attendee());
        return "register";
    }

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
