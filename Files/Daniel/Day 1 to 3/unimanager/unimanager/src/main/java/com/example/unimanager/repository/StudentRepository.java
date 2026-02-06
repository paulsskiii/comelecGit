package com.example.unimanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.unimanager.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {}

