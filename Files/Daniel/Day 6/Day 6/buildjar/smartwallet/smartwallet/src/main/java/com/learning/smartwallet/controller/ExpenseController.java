package com.learning.smartwallet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.smartwallet.dto.ExpenseRequestDTO;
import com.learning.smartwallet.dto.ExpenseResponseDTO;
import com.learning.smartwallet.entity.Expense;
import com.learning.smartwallet.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    // POST: Create a new expense
    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody ExpenseRequestDTO dto) {
        Expense created = service.createExpense(dto);
        return ResponseEntity.ok(created);
    }

    // GET: Get all expenses
    @GetMapping
    public ResponseEntity<List<ExpenseResponseDTO>> getAllExpenses() {
        return ResponseEntity.ok(service.getAllExpenses());
    }

    // GET: Get single expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(@PathVariable Long id) {
        ExpenseResponseDTO expense = service.getExpenseById(id);
        return ResponseEntity.ok(expense);
    }
}

