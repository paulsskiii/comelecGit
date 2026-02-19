package com.learning.smartwallet.repository;

import com.learning.smartwallet.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // Basic CRUD methods are automatically provided!
}
