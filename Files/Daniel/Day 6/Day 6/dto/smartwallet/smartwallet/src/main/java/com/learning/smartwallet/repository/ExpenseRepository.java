package com.learning.smartwallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.smartwallet.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // Basic CRUD methods are automatically provided!
}
