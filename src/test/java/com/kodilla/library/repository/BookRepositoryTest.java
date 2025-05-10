package com.kodilla.library.repository;

import com.kodilla.library.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldSaveAndFindBookById() {
        // Given
        Book book = Book.builder()
                .title("Effective Java")
                .author("Joshua Bloch")
                .publicationDate(LocalDate.of(2018, 1, 1))
                .build();

        // When
        Book saved = bookRepository.save(book);
        Optional<Book> found = bookRepository.findById(saved.getId());

        // Then
        assertTrue(found.isPresent());
        assertEquals("Effective Java", found.get().getTitle());
        assertEquals("Joshua Bloch", found.get().getAuthor());
        assertEquals(LocalDate.of(2018, 1, 1), found.get().getPublicationDate());
    }

    @Test
    void shouldFindAllBooks() {
        // Given
        Book book1 = Book.builder().title("Book A").author("Author A").build();
        Book book2 = Book.builder().title("Book B").author("Author B").build();

        bookRepository.save(book1);
        bookRepository.save(book2);

        // When
        List<Book> allBooks = bookRepository.findAll();

        // Then
        assertEquals(2, allBooks.size());
    }

    @Test
    void shouldReturnEmptyOptionalWhenBookNotFound() {
        // When
        Optional<Book> result = bookRepository.findById(999L);

        // Then
        assertTrue(result.isEmpty());
    }
}
