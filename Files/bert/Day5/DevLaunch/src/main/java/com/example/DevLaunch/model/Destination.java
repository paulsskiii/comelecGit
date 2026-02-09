package com.example.DevLaunch.model;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}