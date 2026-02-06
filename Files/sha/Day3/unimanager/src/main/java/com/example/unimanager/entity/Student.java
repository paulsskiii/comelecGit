package com.example.unimanager.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 1:1 Relationship - OWNING SIDE
    // Cascade ALL: Saving Student saves Profile. Removing Student removes Profile.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private StudentProfile profile;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    // Helper Method to sync both sides (Best Practice)
    public void setProfile(StudentProfile profile) {
        this.profile = profile;
        if (profile != null) {
            profile.setStudent(this); // Sync the inverse side
        }
    }

    // N:M Relationship - OWNING SIDE
    // Creates the intermediate table "student_course"
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new HashSet<>();

    // Helper Method
    public void enrollInCourse(Course course) {
        this.courses.add(course);
        course.getStudents().add(this); // Sync both sides!
    }

    public Set<Course> getCourses() {
        return courses;
    }

    // Standard Getters/Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public StudentProfile getProfile() {
        return profile;
    }
}
