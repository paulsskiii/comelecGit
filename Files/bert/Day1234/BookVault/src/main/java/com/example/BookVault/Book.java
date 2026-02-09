package com.example.BookVault;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
// Lombok Annotation: Generates Getters, Setters, toString, etc.
@Data
public class Book {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
}
