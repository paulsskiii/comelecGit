package com.learning.smartwallet.service;


import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.smartwallet.entity.Budget;
import com.learning.smartwallet.repository.BudgetRepository;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    //Add the setBudget method
    @Transactional
    public Budget setBudget(String category, BigDecimal limit) {
        // Check if a budget already exists for this category
        Optional<Budget> existing = budgetRepository.findByCategory(category);

        if (existing.isPresent()) {
            // Update the existing one
            Budget budget = existing.get();
            budget.setLimitAmount(limit);
            return budgetRepository.save(budget);
        } else {
            // Create a brand new one
            Budget newBudget = new Budget();
            newBudget.setCategory(category);
            newBudget.setLimitAmount(limit);
            return budgetRepository.save(newBudget);
        }
    }

    //Return 'Budget' 
    public Budget getBudgetByCategory(String category) {
        // .orElse(null) unwraps the Optional. If found, return Budget. If not, return null.
        return budgetRepository.findByCategory(category).orElse(null);
    }
}
