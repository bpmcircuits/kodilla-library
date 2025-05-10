package com.kodilla.library.repository;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.BookStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class BookCopyRepositoryTest {

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void shouldSaveAndFindAll() {
        // Given
        Book book = bookRepository.save(
                Book.builder().title("Clean Code").author("Robert Martin").build()
        );

        bookCopyRepository.save(BookCopy.builder().book(book).status(BookStatus.AVAILABLE).build());
        bookCopyRepository.save(BookCopy.builder().book(book).status(BookStatus.LOST).build());

        // When
        List<BookCopy> all = bookCopyRepository.findAll();

        // Then
        assertEquals(2, all.size());
    }

    @Test
    void shouldFindAllByBookId() {
        // Given
        Book book1 = bookRepository.save(Book.builder().title("Book A").author("Author A").build());
        Book book2 = bookRepository.save(Book.builder().title("Book B").author("Author B").build());

        bookCopyRepository.save(BookCopy.builder().book(book1).status(BookStatus.AVAILABLE).build());
        bookCopyRepository.save(BookCopy.builder().book(book1).status(BookStatus.LOST).build());
        bookCopyRepository.save(BookCopy.builder().book(book2).status(BookStatus.DAMAGED).build());

        // When
        List<BookCopy> copiesForBook1 = bookCopyRepository.findAllByBookId(book1.getId());

        // Then
        assertEquals(2, copiesForBook1.size());
        assertTrue(copiesForBook1.stream().allMatch(copy -> copy.getBook().getId().equals(book1.getId())));
    }

    @Test
    void shouldUpdateStatusById() {
        // Given
        Book book = bookRepository.save(Book.builder().title("Refactoring").author("Martin Fowler").build());
        BookCopy copy = bookCopyRepository.save(BookCopy.builder().book(book).status(BookStatus.AVAILABLE).build());

        // When
        bookCopyRepository.updateStatusById(copy.getId(), BookStatus.DAMAGED);
        entityManager.clear();
        // Then
        BookCopy updated = bookCopyRepository.findById(copy.getId()).orElseThrow();
        assertEquals(BookStatus.DAMAGED, updated.getStatus());
    }

    @Test
    void shouldCountAvailableCopiesByBookId() {
        // Given
        Book book = bookRepository.save(Book.builder().title("Effective Java").author("Joshua Bloch").build());

        bookCopyRepository.save(BookCopy.builder().book(book).status(BookStatus.AVAILABLE).build());
        bookCopyRepository.save(BookCopy.builder().book(book).status(BookStatus.LOST).build());
        bookCopyRepository.save(BookCopy.builder().book(book).status(BookStatus.AVAILABLE).build());

        // When
        long count = bookCopyRepository.getAmountOfCopiesById(book.getId());

        // Then
        assertEquals(2, count);
    }
}
