package com.bibliotech.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// CONCEPT: @RestController tells;l Spring "This class handles web requests and returns JSON data directly."
// It replaces the need to add @ResponseBody to every single method.
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    // A fake database to store our books in memory
    private List<Book> bookLibrary = new ArrayList<>();

    // A constructor to populate some initial data so the list isn't empty
    public BookController() {
        bookLibrary.add(new Book(1L, "The Great Gatsby", "F. Scott Fitzgerald", 10.99));
        bookLibrary.add(new Book(2L, "1984", "George Orwell", 8.99));
    }

    // CONCEPT: @PostMapping handles HTTP POST requests (Creation).
    // @RequestBody tells Spring to take the JSON from the request and turn it into a Book object.
    @PostMapping
    public Book addBook(@RequestBody Book newBook) {
        bookLibrary.add(newBook);
        return newBook; // Echo back the created object
    }

    // CONCEPT: @PutMapping for updates.
    // URL: /api/v1/books/{id}
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

    // CONCEPT: @DeleteMapping for removal.
    // URL: /api/v1/books/{id} -> Noun-based resource identification.
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookLibrary.removeIf(book -> id.equals(book.getId()));
        return "Book with ID " + id + " was deleted.";
    }

}
