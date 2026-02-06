package com.example.unimanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.unimanager.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {}