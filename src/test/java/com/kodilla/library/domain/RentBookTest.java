package com.kodilla.library.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RentBookTest {

    @Test
    void getId() {
        // Given
        RentBook rentBook = new RentBook();

        // When & Then
        assertNull(rentBook.getId());
    }

    @Test
    void getBookCopy() {
        // Given
        Book book = Book.builder().title("Test Book").build();
        BookCopy bookCopy = BookCopy.builder().book(book).status(BookStatus.AVAILABLE).build();
        RentBook rentBook = new RentBook(null, bookCopy, null, null, null);

        // When & Then
        assertEquals(bookCopy, rentBook.getBookCopy());
        assertEquals("Test Book", rentBook.getBookCopy().getBook().getTitle());
    }

    @Test
    void getUser() {
        // Given
        User user = User.builder().name("Alice").surname("Smith").build();
        RentBook rentBook = new RentBook(null, null, user, null, null);

        // When & Then
        assertEquals("Alice", rentBook.getUser().getName());
        assertEquals("Smith", rentBook.getUser().getSurname());
    }

    @Test
    void getRentAndReturnDates() {
        // Given
        LocalDate rentDate = LocalDate.of(2024, 5, 1);
        LocalDate returnDate = LocalDate.of(2024, 5, 10);
        RentBook rentBook = new RentBook(null, null, null, rentDate, returnDate);

        // When & Then
        assertEquals(rentDate, rentBook.getRentDate());
        assertEquals(returnDate, rentBook.getReturnDate());
    }

    @Test
    void allArgsConstructor() {
        // Given
        BookCopy bookCopy = BookCopy.builder().status(BookStatus.LOST).build();
        User user = User.builder().name("John").surname("Doe").build();
        LocalDate rentDate = LocalDate.of(2023, 12, 1);
        LocalDate returnDate = LocalDate.of(2024, 1, 1);

        RentBook rentBook = new RentBook(99L, bookCopy, user, rentDate, returnDate);

        // When & Then
        assertEquals(99L, rentBook.getId());
        assertEquals(bookCopy, rentBook.getBookCopy());
        assertEquals(user, rentBook.getUser());
        assertEquals(rentDate, rentBook.getRentDate());
        assertEquals(returnDate, rentBook.getReturnDate());
    }
}