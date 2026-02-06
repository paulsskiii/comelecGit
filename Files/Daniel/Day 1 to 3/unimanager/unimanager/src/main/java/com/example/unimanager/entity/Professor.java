package com.example.unimanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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
