package com.learning.smartwallet.service;

import com.learning.smartwallet.dto.ExpenseRequestDTO;
import com.learning.smartwallet.dto.ExpenseResponseDTO;
import com.learning.smartwallet.entity.Expense;
import com.learning.smartwallet.exception.ExpenseNotFoundException;
import com.learning.smartwallet.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public ExpenseResponseDTO getExpenseById(Long id) {
        // Try to find the expense, OR throw our custom exception
        Expense val= repository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + id));
        return mapToResponseDTO(val);
    }
    
    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }

    //FOR TESTING PURPOSES ONLY
    public List<ExpenseResponseDTO> getAllExpenseResponseDTOs() {
        return repository.findAll()
                .stream()
                .map(ExpenseResponseDTO::new)
                .toList();
    }

    private ExpenseResponseDTO mapToResponseDTO(Expense expense){
        ExpenseResponseDTO dto = new ExpenseResponseDTO();
        dto.setDescription(expense.getDescription());
        dto.setAmount(expense.getAmount());
        dto.setCategory(expense.getCategory());
        return dto;
    }
}
