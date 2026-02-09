package com.example.UniversityRegistrar;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UniversityService {
    private final StudentRepository studentRepo;
    private final DepartmentRepository deptRepo;
    private final CourseRepository courseRepo;

    public UniversityService(StudentRepository studentRepo, DepartmentRepository deptRepo,
            CourseRepository courseRepo) {
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

    // 1. Create Student + Profile
    public Student createStudent(String name, String bio) {
        Student s = new Student(name);
        StudentProfile p = new StudentProfile(bio);

        // CRITICAL: Link them together (Bi-directional)
        s.setProfile(p);

        return studentRepo.save(s);
    }

    // 2. Create Department + Professors
    public Department createDepartment(String name, List<String> profNames) {
        Department d = new Department(name);

        for (String profName : profNames) {
            Professor p = new Professor(profName);
            d.addProfessor(p); // Helper method handles the link
        }

        return deptRepo.save(d);
    }

    // 3. Create Course
    public Course createCourse(String title) {
        return courseRepo.save(new Course(title));
    }

    // 4. Enroll Student in Course (The "Join Table" logic)
    public void enrollStudent(Long studentId, Long courseId) {
        Student s = studentRepo.findById(studentId).orElseThrow();
        Course c = courseRepo.findById(courseId).orElseThrow();

        s.enrollInCourse(c); // This updates both sides automatically!

        studentRepo.save(s); // Save the student (which updates the join table)
    }

    // 5. Find Student + Profile
    public Student getStudentWithProfile(Long id) {
        return studentRepo.findById(id).orElse(null);
    }

    // 6. Find Department + Professors
    public Department getDepartmentWithProfessors(Long id) {
        return deptRepo.findById(id).orElse(null);
    }

    // 7. Find Course + Students
    public Course getCourseWithStudents(Long id) {
        return courseRepo.findById(id).orElse(null);
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
