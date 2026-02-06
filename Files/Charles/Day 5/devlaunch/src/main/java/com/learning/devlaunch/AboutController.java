package com.learning.devlaunch;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 1. Marks this class as a Web Controller (handlers of HTML)
public class AboutController {

    // 2. We map the root URL ("/") to this method
    @GetMapping("/about") 
    public String loadAboutpage(Model model) {
        
        // 3. We add data to the Model (just like the Context in Module 1)
        model.addAttribute("aboutMe", "This page is everything about me! Message loaded properly.");

        // 4. View Resolution:
        // We return "home". Spring Boot checks 'src/main/resources/templates/' 
        // finds 'home.html', merges it with the model, and sends it to the user.
        return "about";
    }
}
