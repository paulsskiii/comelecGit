package com.example.smartwallet;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    // Constructor Injection (Best Practice)
    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    @Transactional // Guarantees ACID properties for this method
    public Expense createExpense(ExpenseRequestDTO dto) {
        // 1. Convert DTO to Entity (Mapping)
        Expense expense = new Expense();
        expense.setDescription(dto.getDescription());
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());

        // 2. Save to DB
        return repository.save(expense);
    }

    public Expense getExpenseById(Long id) {
        // Try to find the expense, OR throw our custom exception
        return repository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + id));
    }

    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }

}
