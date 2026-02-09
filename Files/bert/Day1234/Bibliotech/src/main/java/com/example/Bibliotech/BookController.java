package com.example.Bibliotech;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private List<Book> bookLibrary = new ArrayList<>();

    public BookController() {
        bookLibrary.add(new Book(1L, "The Great Gatsby", "F. Scott Fitzgerald", 10.99));
        bookLibrary.add(new Book(2L, "1984", "George Orwell", 8.99));
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookLibrary;
    }

    @PostMapping
    public Book addBook(@RequestBody Book newBook) {
        bookLibrary.add(newBook);
        return newBook; // Echo back the created object
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        for (Book book : bookLibrary) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null; // Or throw an exception
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        // Simple loop to find the book and update it
        for (Book book : bookLibrary) {
            // Safety Check: We use id.equals(book.getId()) to avoid NullPointerExceptions
            if (id.equals(book.getId())) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setPrice(updatedBook.getPrice());
                return book;
            }
        }
        return null; // Simplified error handling
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookLibrary.removeIf(book -> id.equals(book.getId()));
        return "Book with ID " + id + " was deleted.";
    }

}
