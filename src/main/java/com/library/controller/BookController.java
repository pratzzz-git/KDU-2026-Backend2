package com.library.controller;

import com.library.model.Book;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        book.setId(UUID.randomUUID().toString());
        book.setStatus("AVAILABLE");
        books.add(book);
        return book;
    }

    @PreAuthorize("hasAnyRole('LIBRARIAN','MEMBER')")
    @GetMapping
    public List<Book> getBooks() {
        return books;
    }
}
