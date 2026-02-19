package com.learning.gatekeeper.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {

    // Rule: Cannot be empty, must be at least 3 chars
    @NotBlank(message = "Username is required")
    @Size(min = 3, message = "Username must be at least 3 characters")
    private String username;

    // Rule: Must look like an email (@ symbol, domain, etc.)
    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    private String email;

    // Rule: Must be at least 18
    @Min(value = 18, message = "You must be 18 or older to join")
    private int age;

    @Size(max=50, message="Bio cannot exceed 50 characters")
    private String bio;

    // Getters and Setters (Required for Thymeleaf)
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getBio(){ return bio;}
    public void setBio(String bio){ this.bio=bio;}
}
