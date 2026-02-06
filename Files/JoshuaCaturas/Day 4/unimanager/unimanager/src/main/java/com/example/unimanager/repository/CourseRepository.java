package com.example.unimanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.unimanager.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {}
