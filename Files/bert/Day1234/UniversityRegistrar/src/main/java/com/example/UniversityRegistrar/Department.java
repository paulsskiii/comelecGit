package com.example.UniversityRegistrar;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 1:N Relationship - INVERSE SIDE
    // mappedBy = "department" (the field in Professor class)
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Professor> professors = new ArrayList<>();

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    // HELPER METHOD
    public void addProfessor(Professor professor) {
        this.professors.add(professor);
        professor.setDepartment(this); // Manual sync of FK
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }
}
