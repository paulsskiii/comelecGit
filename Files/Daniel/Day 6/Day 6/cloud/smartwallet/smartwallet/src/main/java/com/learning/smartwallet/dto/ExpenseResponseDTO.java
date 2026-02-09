package com.learning.smartwallet.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ExpenseResponseDTO {
    // No ID field here! The user shouldn't set the ID.
    private String description;
    private BigDecimal amount;

    public ExpenseResponseDTO(String description, BigDecimal amount) {
        this.setDescription(description);
        this.setAmount(amount);
    }
}
