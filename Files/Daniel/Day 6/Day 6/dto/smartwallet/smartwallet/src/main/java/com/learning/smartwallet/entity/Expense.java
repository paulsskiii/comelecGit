package com.learning.smartwallet.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity; // or write getters/setters manually
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity // Tells Hibernate this is a table
@Table(name = "expenses")
@Data // Lombok: Generates Getters, Setters, ToString
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    
    // We use BigDecimal for money to avoid floating point errors
    private BigDecimal amount; 
    
    private String category;
}
