package com.kodilla.library.controller;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.RentBook;
import com.kodilla.library.domain.User;
import com.kodilla.library.dto.BookCopyDTO;
import com.kodilla.library.dto.BookDTO;
import com.kodilla.library.dto.RentBookDTO;
import com.kodilla.library.dto.UserDTO;
import com.kodilla.library.exceptions.BookCopyNotFoundException;
import com.kodilla.library.exceptions.BookNotFoundException;
import com.kodilla.library.exceptions.BookStatusException;
import com.kodilla.library.exceptions.UserNotFoundException;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.mapper.RentMapper;
import com.kodilla.library.mapper.UserMapper;
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
    private final UserMapper userMapper;
    private final BookMapper bookMapper;
    private final RentMapper rentMapper;

    @PostMapping("/user/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO dto) {
        User user = userMapper.mapToUser(dto);
        return ResponseEntity.ok(userMapper.mapToUserDTO(service.addUser(user)));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id)
            throws UserNotFoundException {

        return ResponseEntity.ok(userMapper.mapToUserDTO(service.getUserById(id)));
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDTOList(users));
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks() {
        List<Book> books = service.getAllBooks();
        return ResponseEntity.ok(bookMapper.mapToBookDTOList(books));
    }

    @PostMapping("/book/add")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO dto) {
        Book book = bookMapper.mapToBook(dto);
        return ResponseEntity.ok(bookMapper.mapToBookDTO(service.addBook(book)));
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id)
            throws BookNotFoundException {

        return ResponseEntity.ok(bookMapper.mapToBookDTO(service.getBookById(id)));
    }

    @GetMapping("/book/copy/all")
    public ResponseEntity<List<BookCopyDTO>> getAllBookCopies() {
        List<BookCopy> bookCopies = service.getAllBookCopies();
        return ResponseEntity.ok(bookMapper.mapToBookCopyDTOList(bookCopies));
    }

    @PostMapping("/book/copy/add")
    public ResponseEntity<BookCopyDTO> addBookCopy(@RequestBody BookCopyDTO dto)
            throws BookNotFoundException {

        Book book = service.getBookById(dto.bookId());
        BookCopy bookCopy = bookMapper.mapToBookCopy(dto, book);
        return ResponseEntity.ok(bookMapper.mapToBookCopyDTO(service.addBookCopy(bookCopy)));
    }

    @PutMapping("/book/copy/status")
    public ResponseEntity<Void> changeBookCopyStatus(@RequestBody BookCopyDTO dto)
            throws BookNotFoundException {

        Book book = service.getBookById(dto.bookId());
        BookCopy bookCopy = bookMapper.mapToBookCopy(dto, book);
        service.changeBookCopyStatus(bookCopy);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/book/copy/amount/{id}")
    public ResponseEntity<Long> getAmountOfCopiesByTitle(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAmountOfCopiesById(id));
    }

    @PostMapping("/book/rent")
    public ResponseEntity<RentBookDTO> rentBook(@RequestBody RentBookDTO dto)
            throws BookStatusException, UserNotFoundException, BookCopyNotFoundException {
        User user = service.getUserById(dto.userId());
        BookCopy bookCopy = service.getBookCopyById(dto.bookCopyId());
        RentBook rentBook = rentMapper.mapToRent(dto, bookCopy, user);
        rentBook = service.rentBook(rentBook);
        return ResponseEntity.ok(rentMapper.mapToRentDTO(rentBook));
    }

    @PostMapping("/book/return")
    public ResponseEntity<RentBookDTO> returnBook(@RequestBody RentBookDTO dto)
            throws UserNotFoundException {
        User user = service.getUserById(dto.userId());
        BookCopy bookCopy = service.getBookCopyById(dto.bookCopyId());
        RentBook rentBook = rentMapper.mapToRent(dto, bookCopy, user);
        rentBook = service.returnBook(rentBook);
        return ResponseEntity.ok(rentMapper.mapToRentDTO(rentBook));
    }

    @GetMapping("/book/rent/all")
    public ResponseEntity<List<RentBookDTO>> getAllRents() {
        List<RentBook> rentBooks = service.getAllRents();
        return ResponseEntity.ok(rentMapper.mapToRentDTOList(rentBooks));
    }
}
