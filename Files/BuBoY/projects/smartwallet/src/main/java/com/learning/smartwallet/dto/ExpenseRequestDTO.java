package com.learning.smartwallet.dto;


import java.math.BigDecimal;

public class ExpenseRequestDTO {
    private String description;
    private BigDecimal amount;
    private String category;

    // Getters and Setters
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
