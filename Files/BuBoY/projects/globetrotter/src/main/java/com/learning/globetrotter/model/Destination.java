package com.learning.globetrotter.model;


public class Destination {
    private int id;
    private String city;
    private String country;
    private String description;

    public Destination(int id, String city, String country, String description) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.description = description;
    }

    // Getters are required for ${...} and *{...} expressions
    public int getId() { return id; }
    public String getCity() { return city; }
    public String getCountry() { return country; }
    public String getDescription() { return description; }
}
