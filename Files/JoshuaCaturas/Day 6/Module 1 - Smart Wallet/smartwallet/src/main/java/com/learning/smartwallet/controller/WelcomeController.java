package com.learning.smartwallet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.smartwallet.entity.Expense;
import com.learning.smartwallet.service.ExpenseService;

@RestController
@RequestMapping("/api/welcome")
public class WelcomeController {
    
    private final ExpenseService service;

    public WelcomeController(ExpenseService service) {
        this.service = service;
    }

    // GET: Get all expenses
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        return ResponseEntity.ok(service.getAllExpenses());
    }
}
