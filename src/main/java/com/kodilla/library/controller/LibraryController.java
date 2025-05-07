package com.kodilla.library.controller;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.User;
import com.kodilla.library.dto.BookCopyDTO;
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

    @PostMapping("/user/add")
    public ResponseEntity<Void> addUser(@RequestBody UserDTO userDTO) {
        User user = service.addUser(mapper.mapToUser(userDTO));
        service.addUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.mapToUserDTO(service.getUserById(id)));
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(mapper.mapToUserDTOList(users));
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks() {
        List<Book> books = service.getAllBooks();
        return ResponseEntity.ok(mapper.mapToBookDTOList(books));
    }

    @PostMapping("/book/add")
    public ResponseEntity<Void> addBook(@RequestBody BookDTO bookDTO) {
        Book book = service.addBook(mapper.mapToBook(bookDTO));
        service.addBook(book);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/book/copy/all")
    public ResponseEntity<List<BookCopyDTO>> getAllBookCopies() {
        List<BookCopy> bookCopies = service.getAllBookCopies();
        return ResponseEntity.ok(mapper.mapToBookCopyDTOList(bookCopies));
    }

    @PostMapping("/book/copy/add")
    public ResponseEntity<Void> addBookCopy(@RequestBody BookCopyDTO bookCopyDTO) {
        BookCopy bookCopy = service.addBookCopy(mapper.mapToBookCopy(bookCopyDTO));
        service.addBookCopy(bookCopy);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/book/copy/update")
    public ResponseEntity<BookCopyDTO> changeBookCopyStatus(@RequestBody BookCopyDTO bookCopyDTO) {
        BookCopy bookCopy = mapper.mapToBookCopy(bookCopyDTO);
        service.changeBookCopyStatus(bookCopy);
        return ResponseEntity.ok().build();
    }
}
