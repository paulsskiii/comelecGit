package com.example.bullet_proof;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusContoller {
    
    // This endpoint tells the world our server is running
    @GetMapping("/status")
    public String checkStatus() {
        return "System Operational";
    }

}
