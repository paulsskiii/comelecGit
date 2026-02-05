package com.unilog.UniLog;

import jakarta.persistence.Embeddable;

// @Embeddable tells Hibernate: "This is not a separate table."
// "Take the fields from this class and FLATTEN them into the owning entity's table."
@Embeddable
public class Address {
    
    private String street;
    private String city;
    private String zipCode;

    // Constructors
    public Address() {} // JPA always requires a no-arg constructor
    public Address(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    // Getters and Setters (omitted for brevity, but assume they exist or generate them)
    public String toString() { return city + ", " + zipCode; }
}
