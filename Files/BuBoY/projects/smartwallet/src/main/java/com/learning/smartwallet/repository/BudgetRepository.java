package com.learning.smartwallet.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.smartwallet.entity.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    // This magic method finds a budget by its name (e.g., "Food")
    Optional<Budget> findByCategory(String category);
}
