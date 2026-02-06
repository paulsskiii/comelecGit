package com.example.cloud_cafe;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// Tell Spring this is a config class
@Configuration
// Map all properties starting with "cafe.contact" to fields in this class
@ConfigurationProperties(prefix = "cafe.contact")
public class CafeContactSettings {

    private String email;
    private String phone;

    // Getters and Setters are MANDATORY for ConfigurationProperties
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
