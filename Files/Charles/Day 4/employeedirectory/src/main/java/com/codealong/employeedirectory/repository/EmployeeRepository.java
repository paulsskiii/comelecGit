package com.codealong.employeedirectory.repository;

import com.codealong.employeedirectory.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
