package com.library.controller;

import com.library.model.Book;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import com.library.service.BookService;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book created = service.addBook(book.getTitle());
        return ResponseEntity.accepted().body(created); // 202
    }

    @PreAuthorize("hasAnyRole('LIBRARIAN','MEMBER')")
    @GetMapping
    public List<Book> getBooks() {
        return service.getAllBooks();
    }
}

