package com.kodilla.library.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void getId() {
        // Given
        Book book = Book.builder().build();

        // When & Then
        assertNull(book.getId());
    }

    @Test
    void getTitle() {
        // Given
        Book book = Book.builder()
                .title("Effective Java")
                .build();

        // When & Then
        assertEquals("Effective Java", book.getTitle());
    }

    @Test
    void getAuthor() {
        // Given
        Book book = Book.builder()
                .author("Joshua Bloch")
                .build();

        // When & Then
        assertEquals("Joshua Bloch", book.getAuthor());
    }

    @Test
    void getPublicationDate() {
        // Given
        LocalDate date = LocalDate.of(2018, 1, 1);
        Book book = Book.builder()
                .publicationDate(date)
                .build();

        // When & Then
        assertEquals(date, book.getPublicationDate());
    }

    @Test
    void getCopies() {
        // Given
        Book book = Book.builder().build();

        // When & Then
        assertNotNull(book.getCopies());
        assertTrue(book.getCopies().isEmpty());
    }

    @Test
    void builder() {
        // Given
        LocalDate date = LocalDate.of(2020, 5, 10);
        Book book = Book.builder()
                .title("Java Concurrency in Practice")
                .author("Brian Goetz")
                .publicationDate(date)
                .build();

        // When & Then
        assertEquals("Java Concurrency in Practice", book.getTitle());
        assertEquals("Brian Goetz", book.getAuthor());
        assertEquals(date, book.getPublicationDate());
        assertNotNull(book.getCopies());
        assertTrue(book.getCopies().isEmpty());
    }
}
