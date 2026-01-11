package com.library.service;

import com.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>();

    // Thread pool for background jobs
    private ExecutorService executor = Executors.newFixedThreadPool(2);

    // Called by Controller when a book is added
    public Book addBook(String title) {

        // Create book with PROCESSING state
        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setTitle(title);
        book.setStatus("PROCESSING");

        // Save it immediately
        books.add(book);

        // Submit slow work to background thread
        executor.execute(() -> {
            try {
                // Simulate barcode printing
                Thread.sleep(10000);

                // Update book status after delay
                synchronized (books) {
                    book.setStatus("AVAILABLE");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Return immediately (HTTP thread does not wait)
        return book;
    }

    public List<Book> getAllBooks() {
        return books;
    }
}
