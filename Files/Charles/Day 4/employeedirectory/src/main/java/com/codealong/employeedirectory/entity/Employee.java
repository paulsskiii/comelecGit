package com.codealong.employeedirectory.entity;

import jakarta.persistence.*;
import lombok.Data; // Generates Getters/Setters

@Entity
@Data
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // SENSITIVE: This should never be visible in a generic API response
    private String password; 
    
    // SENSITIVE: Only HR should see this
    private Double salary; 
}
