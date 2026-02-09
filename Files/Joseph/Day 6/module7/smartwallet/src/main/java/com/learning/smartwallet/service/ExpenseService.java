package com.learning.smartwallet.service;

import com.learning.smartwallet.dto.ExpenseRequestDTO;
import com.learning.smartwallet.entity.Budget;
import com.learning.smartwallet.entity.Expense;
import com.learning.smartwallet.exception.ExpenseNotFoundException;
import com.learning.smartwallet.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;
    // 1. Add the new dependency
    private final BudgetService budgetService;

    // 2. Update the Constructor to include BudgetService
    public ExpenseService(ExpenseRepository repository, BudgetService budgetService) {
        this.repository = repository;
        this.budgetService = budgetService;
    }

    @Transactional 
    public Expense createExpense(ExpenseRequestDTO dto) {
        // --- Existing Mapping Code ---
        Expense expense = new Expense();
        expense.setDescription(dto.getDescription());
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());

        // --- 3. NEW LOGIC START: Check Budget ---
        Budget budget = budgetService.getBudgetByCategory(dto.getCategory());
        
        // If a budget exists AND the expense amount is greater than the limit
        if (budget != null && dto.getAmount().compareTo(budget.getLimitAmount()) > 0) {
            System.out.println("WARNING: You have exceeded your budget for " + dto.getCategory());
        }
        // --- NEW LOGIC END ---

        return repository.save(expense);
    }

    public Expense getExpenseById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + id));
    }
    
    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }
}
