package com.learning.smartwallet.entity;

import jakarta.persistence.*;
import lombok.Data; // or write getters/setters manually
import java.math.BigDecimal;

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
