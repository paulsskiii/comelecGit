package com.learning.techconf.controller;

import com.learning.techconf.model.Attendee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        Attendee existingUser = new Attendee();
existingUser.setFirstName("John");
existingUser.setEmail("john@company.com");

        model.addAttribute("attendee", existingUser);
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute Attendee attendee, Model model) {
        model.addAttribute("confirmedAttendee", attendee);
        return "badge";
    }
}
