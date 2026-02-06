package com.unilog.UniLog;

// Standard JPA imports (Vendor Independence)
import jakarta.persistence.*;

@Entity // Marks this class as a database object
@Table(name = "student_records") // Customizing the table name (default would be "Student")
public class Student {
    @Id // The Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment (handles ID generation)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    // IMPEDANCE MISMATCH SOLUTION:
    // We are using the Address object here. Hibernate will flatten this.
    // In the DB, columns will be: ID, NAME, EMAIL, STREET, CITY, ZIPCODE
    @Embedded
    private Address address;

    // Constructors
    public Student() {} // Required by JPA
    
    public Student(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    // Getters (Standard Boilerplate)
    public Long getId() { return id; }
    public String getName() { return name; }
    
    @Override
    public String toString() {
        return "Student ID: " + id + " | Name: " + name + " | Located: " + address;
    }

}
