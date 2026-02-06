package com.library.the_archive;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.library.the_archive.entity.Book;
import com.library.the_archive.repository.BookRepository;

@SpringBootApplication
public class TheArchiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheArchiveApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository repository) {
        return (args) -> {
            // 1. Setup Data
            System.out.println("--- Seeding Database ---");
            repository.save(new Book("Spring in Action", "Craig Walls", true, LocalDate.of(2020, 1, 1), 500));
            repository.save(new Book("Java Basics", "John Doe", true, LocalDate.of(1995, 5, 20), 150)); // Old & Thick enough
            repository.save(new Book("Advanced JPA", "Jane Smith", true, LocalDate.of(2023, 8, 10), 300));
            repository.save(new Book("The Encryption Code", "Alan Turing", true, LocalDate.of(1940, 1, 1), 900));

            // 2. Test JPQL Query
            System.out.println("\n--- 1. Testing JPQL (Thick Active Books) ---");
            List<Book> thickBooks = repository.findThickActiveBooks();
            thickBooks.forEach(System.out::println);

            // 3. Test Named Parameters
            System.out.println("\n--- 2. Testing Named Params (Craig Walls > 2019) ---");
            List<Book> recent = repository.findRecentBooksByAuthor("Craig Walls", LocalDate.of(2019, 1, 1));
            recent.forEach(System.out::println);

            // 4. Test Native Query
            System.out.println("\n--- 3. Testing Native Query (Keyword 'Encryption') ---");
            List<Book> encryptionBooks = repository.findBooksByTitleKeyword("Encryption");
            encryptionBooks.forEach(System.out::println);

            // 5. Test Modifying Query
            System.out.println("\n--- 4. Testing Modifying Query (Archive books before 2000) ---");
            int updatedCount = repository.archiveOldBooks(LocalDate.of(2000, 1, 1));
            System.out.println("Books Archived: " + updatedCount);

            // Verify Update
            System.out.println("--- Verifying Archive Status for 'Java Basics' ---");
            repository.findAll().forEach(System.out::println);
        };
    }
}
