package com.registrar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_records") // Critical: We renamed the table here!
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String status; // e.g., "ACTIVE", "GRADUATED", "SUSPENDED"
    private int age;

    // Default Constructor (Required by JPA)
    public Student() {}

    // Convenience Constructor (For our data seeder)
    public Student(String firstName, String lastName, String email, String status, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.age = age;
    }

    // Getters
    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getStatus() { return status; }
    public int getAge() { return age; }
    
    // toString for debugging
    @Override
    public String toString() {
        return "Student{id=" + id + ", email='" + email + "', status='" + status + "'}";
    }
}
