package com.example.smartwallet;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;
    // 1. Add the new dependency
    private final BudgetService budgetService;

    // Constructor Injection (Best Practice)
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
            // System.out.println("WARNING: You have exceeded your budget for " +
            // dto.getCategory());
            throw new BudgetExceededException("Budget exceeded for " + dto.getCategory());
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
