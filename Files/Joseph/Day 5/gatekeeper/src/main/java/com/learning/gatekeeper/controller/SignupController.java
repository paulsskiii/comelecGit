package com.learning.gatekeeper.controller;

import com.learning.gatekeeper.model.UserDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    // 1. Show the Form
    @GetMapping("/signup")
    public String showForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "signup";
    }

   @PostMapping("/signup")
public String processSignup(@Valid @ModelAttribute("userDto") UserDto userDto, 
                            BindingResult bindingResult, 
                            Model model) {
    if (bindingResult.hasErrors()) {
        return "signup"; // Send back to form to show errors
    }
    return "success";
}

    
    @GetMapping("/success")
    public String showSuccess() {
        return "success";
    }
}
