package com.example.cloud_cafe.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
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
