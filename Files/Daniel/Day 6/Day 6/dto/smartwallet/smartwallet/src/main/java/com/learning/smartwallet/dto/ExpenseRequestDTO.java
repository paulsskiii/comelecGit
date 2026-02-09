package com.learning.smartwallet.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ExpenseRequestDTO {
    // No ID field here! The user shouldn't set the ID.
    private String description;
    private BigDecimal amount;
    private String category;
}

