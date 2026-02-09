package com.learning.smartwallet.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.smartwallet.entity.Budget;
import com.learning.smartwallet.service.BudgetService;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    // POST /api/budgets?category=Food&limit=200
    @PostMapping
    public ResponseEntity<Budget> setBudget(@RequestParam String category,
            @RequestParam BigDecimal limit) {
        Budget budget = budgetService.setBudget(category, limit);
        return ResponseEntity.ok(budget);
    }

    // GET /api/budgets/Food
    @GetMapping("/{category}")
    public ResponseEntity<Budget> getBudget(@PathVariable String category) {
        return ResponseEntity.ok(budgetService.getBudgetByCategory(category));
    }

}
