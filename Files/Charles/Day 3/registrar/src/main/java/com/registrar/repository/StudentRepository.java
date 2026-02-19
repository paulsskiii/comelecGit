package com.registrar.repository;

import com.registrar.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // ---------------------------------------------------------
    // CONCEPT 1: JPQL (Object-Oriented Query)
    // Query the Class (Student), not the table.
    // ---------------------------------------------------------
    @Query("SELECT s FROM Student s WHERE s.status = 'ACTIVE'")
    List<Student> findAllActiveStudents();

    // ---------------------------------------------------------
    // CONCEPT 2: Named Parameters
    // Using @Param matches the :minAge placeholder to the method argument.
    // ---------------------------------------------------------
    @Query("SELECT s FROM Student s WHERE s.age >= :minAge AND s.status = :status")
    List<Student> findByAgeAndStatus(
            @Param("minAge") int age, 
            @Param("status") String status
    );

    // ---------------------------------------------------------
    // CONCEPT 3: Native Query (Raw SQL)
    // Accessing the specific table 'student_records'.
    // If you used 'Student' here, it would fail!
    // ---------------------------------------------------------
    @Query(
        value = "SELECT * FROM student_records WHERE email LIKE CONCAT('%', :domain)", 
        nativeQuery = true
    )
    List<Student> findStudentsByEmailDomainNative(@Param("domain") String domain);
}
