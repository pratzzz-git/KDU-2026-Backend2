package com.library.service;

import com.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>();

    private ExecutorService executor = Executors.newFixedThreadPool(2);

    public synchronized Book addBook(String title) {
        Book book = new Book(UUID.randomUUID().toString(), title, "PROCESSING");
        books.add(book);

        executor.execute(() -> {
            try {
                Thread.sleep(3000);
                synchronized (books) {
                    book.setStatus("AVAILABLE");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return book;
    }

    public List<Book> getAllBooks() {
        return books;
    }
}
