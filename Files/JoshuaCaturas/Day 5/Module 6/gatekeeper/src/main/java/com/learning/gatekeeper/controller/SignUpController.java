package com.learning.gatekeeper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.learning.gatekeeper.model.UserDTO;

import jakarta.validation.Valid;

@Controller
public class SignUpController {
    // 1. Show the Form
    @GetMapping("/signup")
    public String showForm(Model model) {
        model.addAttribute("userDto", new UserDTO());
        return "signup";
    }

    // 2. Process the Form
    @PostMapping("/signup")
    public String processSignup(
            @Valid @ModelAttribute("userDto") UserDTO userDto, // @Valid triggers the checks
            BindingResult bindingResult, // Capture errors here. MUST come immediately after the @Valid object.
            Model model) {

        // Check if there are errors
        if (bindingResult.hasErrors()) {
            // Return to the form. Thymeleaf will see the errors in the object.
            return "signup";
        }

        // If no errors, proceed to success page (stubbed)
        return "success";
    }
    
    @GetMapping("/success")
    public String showSuccess() {
        return "success";
    }

}
