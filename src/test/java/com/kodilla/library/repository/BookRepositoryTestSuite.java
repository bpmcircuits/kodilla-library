package com.kodilla.library.repository;

import com.kodilla.library.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTestSuite {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testCreateBookAndGetIt() {
        //given
        Book book = new Book("Test book", "Test author", LocalDate.now());

        //when
        bookRepository.save(book);

        //then
        Long id = book.getId();
        Optional<Book> actual = bookRepository.findById(id);
        assertTrue(actual.isPresent());
        assertEquals("Test book", actual.get().getTitle());
        //clean up
        bookRepository.deleteById(id);
    }

}