package com.learning.bookvault;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// JPA Annotation: Marks this class as a database table
@Entity 
// Lombok Annotation: Generates Getters, Setters, toString, etc.
@Data 
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String author;
}
