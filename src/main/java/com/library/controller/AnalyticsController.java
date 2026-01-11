package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final BookService service;

    public AnalyticsController(BookService service) {
        this.service = service;
    }

    @GetMapping("/audit")
    public Map<String, Long> audit() {

        return service.getAllBooks()
                .stream()                       // turn List<Book> into a stream
                .collect(                       // collect final result
                        Collectors.groupingBy(      // group by a key
                                Book::getStatus,        // key = book status
                                Collectors.counting()   // value = how many
                        )
                );
    }
}
