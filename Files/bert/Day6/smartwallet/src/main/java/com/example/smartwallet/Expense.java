package com.example.smartwallet;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity // Tells Hibernate this is a table
@Table(name = "expenses")
@Data // Lombok: Generates Getters, Setters, ToString
@NoArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    // We use BigDecimal for money to avoid floating point errors
    private BigDecimal amount;

    private String category;

    // public Expense() {
    // }

    public Expense(String description, BigDecimal amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

}
