package com.learning.smartwallet.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ExpenseResponseDTO {
    private String description;
    private BigDecimal amount;
}
