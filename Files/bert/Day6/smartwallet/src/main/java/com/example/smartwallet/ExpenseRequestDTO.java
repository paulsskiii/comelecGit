package com.example.smartwallet;

import java.math.BigDecimal;

//import lombok.Data;

// @Data
public class ExpenseRequestDTO {
    private String description;
    private BigDecimal amount;
    private String category;

    public ExpenseRequestDTO() {
    }

    public ExpenseRequestDTO(String description, BigDecimal amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
