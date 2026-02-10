package com.learning.smartwallet.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.smartwallet.dto.ExpenseRequestDTO;
import com.learning.smartwallet.dto.ExpenseResponseDTO;
import com.learning.smartwallet.entity.Budget;
import com.learning.smartwallet.entity.Expense;
import com.learning.smartwallet.exception.BudgetExceededException;
import com.learning.smartwallet.exception.ExpenseNotFoundException;
import com.learning.smartwallet.repository.ExpenseRepository;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;
    private final BudgetService budgetService;

    // Constructor Injection (Best Practice)
    public ExpenseService(ExpenseRepository repository, BudgetService budgetService) {
        this.repository = repository;
        this.budgetService = budgetService;
    }

    @Transactional // Guarantees ACID properties for this method
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

            throw new BudgetExceededException("WARNING: You have exceeded your budget for " + dto.getCategory());
        }
        return repository.save(expense);
    }

    private ExpenseResponseDTO toDTO(Expense e) {
        return new ExpenseResponseDTO(
                e.getDescription(),
                e.getAmount());
    }

    public ExpenseResponseDTO getExpenseById(Long id) {
        // Try to find the expense, OR throw our custom exception
        Expense e = repository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + id));
        return toDTO(e);
    }

    public List<ExpenseResponseDTO> getAllExpenses() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

}
