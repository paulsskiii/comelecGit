package com.example.registrar.controller;

import com.example.registrar.entity.Student;
import com.example.registrar.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public List<Student> getByDomain(@RequestParam String domain) {
        return repository.findStudentsByEmailDomainNative(domain);
    }
}
