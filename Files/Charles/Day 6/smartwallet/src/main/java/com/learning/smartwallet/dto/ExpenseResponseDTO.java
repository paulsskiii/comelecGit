package com.learning.smartwallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import com.learning.smartwallet.entity.Expense;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseResponseDTO {
    // No ID field here! The user shouldn't set the ID.
    private String description;
    private BigDecimal amount;
    private String category;

    public ExpenseResponseDTO(Expense expense) {
        this.description = expense.getDescription();
        this.amount = expense.getAmount();
        this.category = expense.getCategory();
    }

}