package com.library.the_archive.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_books") // We name the table explicitly to differentiate it from the Class later
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private boolean isActive; // true = on shelf, false = in storage
    private LocalDate publishedDate;
    private int pageCount;

    // Constructors
    public Book() {}

    public Book(String title, String author, boolean isActive, LocalDate publishedDate, int pageCount) {
        this.title = title;
        this.author = author;
        this.isActive = isActive;
        this.publishedDate = publishedDate;
        this.pageCount = pageCount;
    }

    // Getters and Setters (Omitted for brevity, but generate them in your IDE)
    public String toString() {
        return "Book [Title=" + title + ", Author=" + author + ", Active=" + isActive + "]";
    }
}
