package com.example.unimanager.entity;

import jakarta.persistence.*;

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // N:1 Relationship - OWNING SIDE
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "dept_id") // The Foreign Key Column
    private Department department;

    public Professor() {}
    public Professor(String name) { this.name = name; }

    public void setDepartment(Department department) { this.department = department; }
    public String getName() { return name; }
}
