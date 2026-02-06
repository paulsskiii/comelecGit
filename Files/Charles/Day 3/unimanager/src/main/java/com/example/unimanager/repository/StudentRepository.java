package com.example.unimanager.repository;

import com.example.unimanager.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}