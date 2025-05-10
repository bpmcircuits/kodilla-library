package com.kodilla.library.repository;

import com.kodilla.library.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RentRepositoryTest {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndFindRent() {
        // Given
        Book book = bookRepository.save(Book.builder()
                .title("Java Book").author("Author").build());

        BookCopy copy = bookCopyRepository.save(BookCopy.builder()
                .book(book).status(BookStatus.AVAILABLE).build());

        User user = userRepository.save(User.builder()
                .name("John").surname("Doe").build());

        RentBook rent = RentBook.builder()
                .bookCopy(copy)
                .user(user)
                .rentDate(LocalDate.of(2024, 5, 1))
                .returnDate(LocalDate.of(2024, 5, 10))
                .build();

        // When
        RentBook saved = rentRepository.save(rent);
        Optional<RentBook> found = rentRepository.findById(saved.getId());

        // Then
        assertTrue(found.isPresent());
        assertEquals("John", found.get().getUser().getName());
        assertEquals(BookStatus.AVAILABLE, found.get().getBookCopy().getStatus());
    }

    @Test
    void shouldFindAllRents() {
        // Given
        Book book = bookRepository.save(Book.builder().title("Book").author("Author").build());
        BookCopy copy = bookCopyRepository.save(BookCopy.builder().book(book).status(BookStatus.LOST).build());
        User user = userRepository.save(User.builder().name("Alice").surname("Smith").build());

        rentRepository.save(RentBook.builder()
                .bookCopy(copy)
                .user(user)
                .rentDate(LocalDate.of(2024, 1, 1))
                .build());

        rentRepository.save(RentBook.builder()
                .bookCopy(copy)
                .user(user)
                .rentDate(LocalDate.of(2024, 2, 1))
                .returnDate(LocalDate.of(2024, 2, 5))
                .build());

        // When
        List<RentBook> rents = rentRepository.findAll();

        // Then
        assertEquals(2, rents.size());
    }
}
