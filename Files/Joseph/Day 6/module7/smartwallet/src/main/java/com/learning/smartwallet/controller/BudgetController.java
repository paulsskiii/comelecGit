package com.learning.smartwallet.controller;

import com.learning.smartwallet.entity.Budget;
import com.learning.smartwallet.service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

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
