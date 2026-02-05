package com.example.cloud_cafe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class CafeInfoController {

    @Value("${cafe.name}")
    private String cafeName;

    @Value("${cafe.special:House Blend}")
    private String dailySpecial;

    // Autowire the Type-Safe configuration class
    @Autowired
    private CafeContactSettings contactSettings;

    @GetMapping("/info")
    public String getCafeInfo() {
        return "Welcome to " + cafeName + "! <br>" +
                "Special: " + dailySpecial + "<br>" +
                "Contact: " + contactSettings.getEmail() + " | " + contactSettings.getPhone();
    }
}
