package com.library.the_archive.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.the_archive.entity.Book;

import jakarta.transaction.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // 1. JPQL Query
    // We select 'b' (alias) from the Class 'Book'.
    // This decouples the method name from the complex logic.
    @Query("SELECT b FROM Book b WHERE b.isActive = true AND b.pageCount > 100")
    List<Book> findThickActiveBooks();

    // 2. Named Parameters
    // We bind :authName to the 'author' parameter and :date to 'publishedDate'.
    // The order of arguments in the method signature no longer matters!
    @Query("SELECT b FROM Book b WHERE b.author = :authName AND b.publishedDate > :date")
    List<Book> findRecentBooksByAuthor(@Param("authName") String author,
            @Param("date") LocalDate publishedDate);

    // 3. Native Query
    // This is raw SQL. We must use 'tbl_books'.
    // We use CONCAT to safely join the '%' wildcards with the parameter.
    @Query(
            value = "SELECT * FROM tbl_books WHERE title LIKE CONCAT('%', :keyword, '%')",
            nativeQuery = true
    )
    List<Book> findBooksByTitleKeyword(@Param("keyword") String keyword);

    // 4. Modifying Query
    // We update the 'isActive' field.
    // @Transactional is required for Update/Delete operations.
    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.isActive = false WHERE b.publishedDate < :cutoff")
    int archiveOldBooks(@Param("cutoff") LocalDate cutoffDate);


}
