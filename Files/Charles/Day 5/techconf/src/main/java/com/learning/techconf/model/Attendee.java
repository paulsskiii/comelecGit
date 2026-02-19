package com.learning.techconf.model;

public class Attendee {
    private String firstName;
    private String lastName;
    private String email;
    private String workshop; // e.g., "AI", "Cloud", "Security"

    // 1. Default Constructor (Required for Spring to create the object)
    public Attendee() {}

    // 2. Getters and Setters (CRITICAL: Thymeleaf needs these to read/write data)
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getWorkshop() { return workshop; }
    public void setWorkshop(String workshop) { this.workshop = workshop; }
}
