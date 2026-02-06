package com.example.unimanager.service;

import org.springframework.stereotype.Service;

import com.example.unimanager.entity.Course;
import com.example.unimanager.entity.Department;
import com.example.unimanager.entity.Professor;
import com.example.unimanager.entity.Student;
import com.example.unimanager.entity.StudentProfile;
import com.example.unimanager.repository.CourseRepository;
import com.example.unimanager.repository.DepartmentRepository;
import com.example.unimanager.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class UniversityService {
    private final StudentRepository studentRepo;
    private final DepartmentRepository deptRepo;
    private final CourseRepository courseRepo;

    // Constructor Injection
    public UniversityService(StudentRepository studentRepo, DepartmentRepository deptRepo, CourseRepository courseRepo) {
        this.studentRepo = studentRepo;
        this.deptRepo = deptRepo;
        this.courseRepo = courseRepo;
    }

    @Transactional 
    public void seedData() {
        System.out.println("----- CREATING DATA -----");

        // 1. Create a Department and Professors (1:N)
        Department dept = new Department("Computer Science");
        // Using Helper method to sync relationship
        dept.addProfessor(new Professor("Dr. Alan Turing"));
        dept.addProfessor(new Professor("Dr. Grace Hopper"));
        
        deptRepo.save(dept); // Saving Dept automatically saves Professors (Cascade)

        // 2. Create Student with Profile (1:1) and Courses (N:M)
        Student s1 = new Student("John Doe");
        s1.setProfile(new StudentProfile("Loves coding and coffee."));

        Course c1 = new Course("Java 101");
        Course c2 = new Course("Data Structures");
        courseRepo.save(c1);
        courseRepo.save(c2);

        s1.enrollInCourse(c1);
        s1.enrollInCourse(c2);

        studentRepo.save(s1); // Saving Student saves Profile and Links to Courses
    }

    @Transactional 
    public void printData() {
        System.out.println("----- FETCHING DATA -----");

        // Test 1:N Lazy Loading
        // We fetch the department. Professors are NOT loaded yet.
        Department d = deptRepo.findAll().get(0); 
        System.out.println("Department: " + d.getName());
        
        // This line would crash without @Transactional. 
        // With it, Hibernate runs a second query here to fetch professors.
        System.out.println("  > Prof Count: " + d.getProfessors().size()); 
        d.getProfessors().forEach(p -> System.out.println("  > Prof: " + p.getName()));

        // Test N:M
        Student s = studentRepo.findAll().get(0);
        System.out.println("Student: " + s.getName());
        System.out.println("  > Courses: ");
        s.getCourses().forEach(c -> System.out.println("    - " + c.getTitle()));
    } 
}
