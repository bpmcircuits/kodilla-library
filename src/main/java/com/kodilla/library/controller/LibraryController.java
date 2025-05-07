package com.kodilla.library.controller;

import com.kodilla.library.domain.Book;
import com.kodilla.library.dto.BookDTO;
import com.kodilla.library.dto.UserDTO;
import com.kodilla.library.mapper.LibraryMapper;
import com.kodilla.library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/library")
@RequiredArgsConstructor
public class LibraryController {

    private final DbService service;
    private final LibraryMapper mapper;

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.mapToUserDTO(service.getUserById(id)));
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks() {
        List<Book> books = service.getAllBooks();
        return ResponseEntity.ok(mapper.mapToBookDTOList(books));
    }

    @PostMapping("/book/add")
    public ResponseEntity<BookDTO> addBook(@RequestBody Book book) {
        Book savedBook = service.addBook(book);
        return ResponseEntity.ok(mapper.mapToBookDTO(savedBook));
    }


}
