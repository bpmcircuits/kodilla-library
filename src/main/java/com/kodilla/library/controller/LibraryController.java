package com.kodilla.library.controller;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.RentBook;
import com.kodilla.library.domain.User;
import com.kodilla.library.dto.BookCopyDTO;
import com.kodilla.library.dto.BookDTO;
import com.kodilla.library.dto.RentBookDTO;
import com.kodilla.library.dto.UserDTO;
import com.kodilla.library.exceptions.BookNotFoundException;
import com.kodilla.library.exceptions.BookStatusException;
import com.kodilla.library.exceptions.UserNotFoundException;
import com.kodilla.library.mapper.LibraryMapper;
import com.kodilla.library.mapper.RentMapper;
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
    private final RentMapper rentMapper;

    @PostMapping("/user/add")
    public ResponseEntity<Void> addUser(@RequestBody UserDTO userDTO) {
        User user = service.addUser(mapper.mapToUser(userDTO));
        service.addUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) throws UserNotFoundException {
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

    @GetMapping("/book/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id) throws BookNotFoundException {
        return ResponseEntity.ok(mapper.mapToBookDTO(service.getBookById(id)));
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

    @PutMapping("/book/copy/status")
    public ResponseEntity<BookCopyDTO> changeBookCopyStatus(@RequestBody BookCopyDTO bookCopyDTO) {
        BookCopy bookCopy = mapper.mapToBookCopy(bookCopyDTO);
        service.changeBookCopyStatus(bookCopy);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/book/copy/amount/{id}")
    public ResponseEntity<Long> getAmountOfCopiesByTitle(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAmountOfCopiesById(id));
    }

    @PostMapping("/book/rent")
    public ResponseEntity<RentBookDTO> rentBook(@RequestBody RentBookDTO rentBookDTO) throws BookStatusException {
        RentBook rentBook = rentMapper.mapToRent(rentBookDTO);
        rentBook = service.rentBook(rentBook);
        return ResponseEntity.ok(rentMapper.mapToRentDTO(rentBook));
    }

    @PostMapping("/book/return")
    public ResponseEntity<RentBookDTO> returnBook(@RequestBody RentBookDTO rentBookDTO) {
        RentBook rentBook = rentMapper.mapToRent(rentBookDTO);
        rentBook = service.returnBook(rentBook);
        return ResponseEntity.ok(rentMapper.mapToRentDTO(rentBook));
    }

    @GetMapping("/book/rent/all")
    public ResponseEntity<List<RentBookDTO>> getAllRents() {
        List<RentBook> rentBooks = service.getAllRents();
        return ResponseEntity.ok(rentMapper.mapToRentDTOList(rentBooks));
    }
}
