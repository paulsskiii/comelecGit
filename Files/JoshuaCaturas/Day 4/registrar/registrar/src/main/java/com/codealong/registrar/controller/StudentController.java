package com.codealong.registrar.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codealong.registrar.entity.Student;
import com.codealong.registrar.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    
    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    // Test JPQL Simple
    @GetMapping("/active")
    public List<Student> getActiveStudents() {
        return repository.findAllActiveStudents();
    }

    // Test JPQL with Parameters
    @GetMapping("/filter")
    public List<Student> filterStudents(
            @RequestParam int age, 
            @RequestParam String status) {
        return repository.findByAgeAndStatus(age, status);
    }

    // Test Native Query
    @GetMapping("/native-domain")
    public List<Student> getByDomain(@RequestParam String domain){
        return repository.findStudentsByEmailDomainNative(domain);
    }
}
