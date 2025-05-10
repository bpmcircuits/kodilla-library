package com.kodilla.library.service;

import com.kodilla.library.domain.*;
import com.kodilla.library.exceptions.BookCopyNotFoundException;
import com.kodilla.library.exceptions.BookNotFoundException;
import com.kodilla.library.exceptions.BookStatusException;
import com.kodilla.library.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DbServiceTest {

    @Autowired
    private DbService dbService;

    @Test
    void shouldAddAndGetUser() throws UserNotFoundException {
        // Given
        User user = User.builder().name("Anna").surname("Kowalska").build();

        // When
        User saved = dbService.addUser(user);
        User found = dbService.getUserById(saved.getId());

        // Then
        assertEquals("Anna", found.getName());
        assertEquals("Kowalska", found.getSurname());
        assertNotNull(found.getAccountCreated());
    }

    @Test
    void shouldAddAndGetBook() throws BookNotFoundException {
        // Given
        Book book = Book.builder().title("Clean Code").author("Robert C. Martin").build();

        // When
        Book saved = dbService.addBook(book);
        Book found = dbService.getBookById(saved.getId());

        // Then
        assertEquals("Clean Code", found.getTitle());
    }

    @Test
    void shouldAddAndGetBookCopy() throws BookCopyNotFoundException {
        // Given
        Book book = dbService.addBook(Book.builder().title("DDD").author("Evans").build());
        BookCopy copy = BookCopy.builder().book(book).status(BookStatus.AVAILABLE).build();

        // When
        BookCopy savedCopy = dbService.addBookCopy(copy);
        BookCopy foundCopy = dbService.getBookCopyById(savedCopy.getId());

        // Then
        assertEquals(BookStatus.AVAILABLE, foundCopy.getStatus());
        assertEquals(book.getId(), foundCopy.getBook().getId());
    }

    @Test
    void shouldRentAndReturnBook() throws BookCopyNotFoundException, UserNotFoundException, BookStatusException {
        // Given
        User user = dbService.addUser(User.builder().name("John").surname("Smith").build());
        Book book = dbService.addBook(Book.builder().title("Java").author("Author").build());
        BookCopy copy = dbService.addBookCopy(BookCopy.builder().book(book).status(BookStatus.AVAILABLE).build());

        RentBook rent = RentBook.builder()
                .user(user)
                .bookCopy(copy)
                .rentDate(LocalDate.now())
                .returnDate(LocalDate.now().plusDays(7))
                .build();

        // When
        RentBook rented = dbService.rentBook(rent);
        BookCopy rentedCopy = dbService.getBookCopyById(copy.getId());

        // Then
        assertEquals(BookStatus.RENTED, rentedCopy.getStatus());

        // When returning
        RentBook returned = dbService.returnBook(rent);
        BookCopy returnedCopy = dbService.getBookCopyById(copy.getId());

        // Then
        assertEquals(BookStatus.AVAILABLE, returnedCopy.getStatus());
    }

    @Test
    void shouldThrowWhenRentingUnavailableBook() {
        // Given
        User user = dbService.addUser(User.builder().name("Ula").surname("Nowak").build());
        Book book = dbService.addBook(Book.builder().title("Unavailable").author("Nobody").build());
        BookCopy copy = dbService.addBookCopy(BookCopy.builder().book(book).status(BookStatus.LOST).build());

        RentBook rent = RentBook.builder()
                .user(user)
                .bookCopy(copy)
                .rentDate(LocalDate.now())
                .build();

        // Then
        assertThrows(BookStatusException.class, () -> dbService.rentBook(rent));
    }

    @Test
    void shouldGetAmountOfAvailableCopies() {
        // Given
        Book book = dbService.addBook(Book.builder().title("Counted").author("X").build());
        dbService.addBookCopy(BookCopy.builder().book(book).status(BookStatus.AVAILABLE).build());
        dbService.addBookCopy(BookCopy.builder().book(book).status(BookStatus.LOST).build());
        dbService.addBookCopy(BookCopy.builder().book(book).status(BookStatus.AVAILABLE).build());

        // When
        Long count = dbService.getAmountOfCopiesById(book.getId());

        // Then
        assertEquals(2L, count);
    }
}
