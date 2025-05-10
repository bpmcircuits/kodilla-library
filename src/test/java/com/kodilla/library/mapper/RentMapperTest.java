package com.kodilla.library.mapper;

import com.kodilla.library.domain.*;
import com.kodilla.library.dto.RentBookDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RentMapperTest {

    private RentMapper rentMapper;

    @BeforeEach
    void setUp() {
        rentMapper = new RentMapper(null, null); // repozytoria nie są używane
    }

    @Test
    void shouldMapToRentDTO() {
        // Given
        BookCopy bookCopy = BookCopy.builder().id(100L).build();
        User user = User.builder().id(200L).build();
        RentBook rentBook = RentBook.builder()
                .id(1L)
                .bookCopy(bookCopy)
                .user(user)
                .rentDate(LocalDate.of(2024, 5, 1))
                .returnDate(LocalDate.of(2024, 5, 10))
                .build();

        // When
        RentBookDTO dto = rentMapper.mapToRentDTO(rentBook);

        // Then
        assertEquals(1L, dto.id());
        assertEquals(100L, dto.bookCopyId());
        assertEquals(200L, dto.userId());
        assertEquals("2024-05-01", dto.rentDate());
        assertEquals("2024-05-10", dto.returnDate());
    }

    @Test
    void shouldMapToRent() {
        // Given
        RentBookDTO dto = new RentBookDTO(5L, 10L, 20L, "2024-04-15", "2024-04-20");
        BookCopy bookCopy = BookCopy.builder().id(10L).build();
        User user = User.builder().id(20L).build();

        // When
        RentBook rentBook = rentMapper.mapToRent(dto, bookCopy, user);

        // Then
        assertEquals(5L, rentBook.getId());
        assertEquals(bookCopy, rentBook.getBookCopy());
        assertEquals(user, rentBook.getUser());
        assertEquals(LocalDate.of(2024, 4, 15), rentBook.getRentDate());
        assertEquals(LocalDate.of(2024, 4, 20), rentBook.getReturnDate());
    }

    @Test
    void shouldHandleNullDates() {
        // Given
        RentBookDTO dto = new RentBookDTO(1L, 1L, 1L, null, null);
        BookCopy bookCopy = BookCopy.builder().id(1L).build();
        User user = User.builder().id(1L).build();

        // When
        RentBook rentBook = rentMapper.mapToRent(dto, bookCopy, user);

        // Then
        assertNull(rentBook.getRentDate());
        assertNull(rentBook.getReturnDate());
    }

    @Test
    void shouldMapToRentDTOList() {
        // Given
        BookCopy bookCopy = BookCopy.builder().id(1L).build();
        User user = User.builder().id(2L).build();
        List<RentBook> rents = List.of(
                new RentBook(1L, bookCopy, user, LocalDate.of(2024, 1, 1), null),
                new RentBook(2L, bookCopy, user, LocalDate.of(2024, 2, 1), LocalDate.of(2024, 2, 5))
        );

        // When
        List<RentBookDTO> dtos = rentMapper.mapToRentDTOList(rents);

        // Then
        assertEquals(2, dtos.size());
        assertEquals("2024-01-01", dtos.get(0).rentDate());
        assertNull(dtos.get(0).returnDate());
        assertEquals("2024-02-05", dtos.get(1).returnDate());
    }
}
