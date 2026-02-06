package com.example.unimanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;

    // Inverse side: maps back to 'profile' field in Student
    @OneToOne(mappedBy = "profile") 
    private Student student;

    // Constructors, Getters, Setters
    public StudentProfile() {}
    public StudentProfile(String bio) { this.bio = bio; }
    
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public String getBio() { return bio; }

}
