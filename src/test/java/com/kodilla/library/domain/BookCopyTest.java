package com.kodilla.library.domain;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class BookCopyTest {

    @Test
    void getId() {
        // Given
        BookCopy bookCopy = BookCopy.builder().build();

        // When & Then
        assertNull(bookCopy.getId());
    }

    @Test
    void getBook() {
        // Given
        Book book = Book.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .build();

        BookCopy bookCopy = BookCopy.builder()
                .book(book)
                .build();

        // When & Then
        assertEquals("Clean Code", bookCopy.getBook().getTitle());
        assertEquals("Robert C. Martin", bookCopy.getBook().getAuthor());
    }

    @Test
    void getStatusAndSetStatus() {
        // Given
        BookCopy bookCopy = BookCopy.builder().build();

        // When
        bookCopy.setStatus(BookStatus.LOST);

        // Then
        assertEquals(BookStatus.LOST, bookCopy.getStatus());
    }

    @Test
    void getRentBooks() {
        // Given
        BookCopy bookCopy = BookCopy.builder()
                .build();

        // When & Then
        assertNotNull(bookCopy.getRentBooks());
        assertTrue(bookCopy.getRentBooks().isEmpty());
    }

    @Test
    void builder() {
        // Given
        Book book = Book.builder().title("Test Book").build();
        BookCopy bookCopy = BookCopy.builder()
                .book(book)
                .status(BookStatus.AVAILABLE)
                .build();

        // When & Then
        assertEquals(book, bookCopy.getBook());
        assertEquals(BookStatus.AVAILABLE, bookCopy.getStatus());
        assertNotNull(bookCopy.getRentBooks());
        assertTrue(bookCopy.getRentBooks().isEmpty());
    }

}