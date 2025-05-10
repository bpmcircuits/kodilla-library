package com.kodilla.library.mapper;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.BookStatus;
import com.kodilla.library.dto.BookCopyDTO;
import com.kodilla.library.dto.BookDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {

    private final BookMapper mapper = new BookMapper();

    @Test
    void shouldMapBookToBookDTO() {
        // Given
        Book book = Book.builder()
                .id(1L)
                .title("Clean Code")
                .author("Robert C. Martin")
                .publicationDate(LocalDate.of(2008, 8, 1))
                .build();

        // When
        BookDTO dto = mapper.mapToBookDTO(book);

        // Then
        assertEquals(1L, dto.id());
        assertEquals("Clean Code", dto.title());
        assertEquals("Robert C. Martin", dto.author());
        assertEquals("2008-08-01", dto.publicationDate());
    }

    @Test
    void shouldMapBookDTOToBook() {
        // Given
        BookDTO dto = new BookDTO(1L, "Effective Java", "Joshua Bloch", "2018-01-01");

        // When
        Book book = mapper.mapToBook(dto);

        // Then
        assertEquals("Effective Java", book.getTitle());
        assertEquals("Joshua Bloch", book.getAuthor());
        assertEquals(LocalDate.of(2018, 1, 1), book.getPublicationDate());
    }

    @Test
    void shouldMapBookListToBookDTOList() {
        // Given
        List<Book> books = List.of(
                Book.builder().id(1L).title("Java").author("A").publicationDate(LocalDate.of(2020, 1, 1)).build(),
                Book.builder().id(2L).title("Spring").author("B").publicationDate(LocalDate.of(2021, 1, 1)).build()
        );

        // When
        List<BookDTO> dtoList = mapper.mapToBookDTOList(books);

        // Then
        assertEquals(2, dtoList.size());
        assertEquals("Java", dtoList.get(0).title());
        assertEquals("Spring", dtoList.get(1).title());
    }

    @Test
    void shouldMapBookCopyToBookCopyDTO() {
        // Given
        Book book = Book.builder().id(10L).title("Test").build();
        BookCopy copy = BookCopy.builder()
                .id(5L)
                .book(book)
                .status(BookStatus.DAMAGED)
                .build();

        // When
        BookCopyDTO dto = mapper.mapToBookCopyDTO(copy);

        // Then
        assertEquals(5L, dto.id());
        assertEquals(10L, dto.bookId());
        assertEquals("DAMAGED", dto.status());
    }

    @Test
    void shouldMapBookCopyDTOToBookCopy() {
        // Given
        BookCopyDTO dto = new BookCopyDTO(7L, 15L, "AVAILABLE");
        Book book = Book.builder().id(15L).build();

        // When
        BookCopy copy = mapper.mapToBookCopy(dto, book);

        // Then
        assertEquals(book, copy.getBook());
        assertEquals(BookStatus.AVAILABLE, copy.getStatus());
    }

    @Test
    void shouldMapBookCopyListToBookCopyDTOList() {
        // Given
        Book book = Book.builder().id(11L).build();
        List<BookCopy> copies = List.of(
                BookCopy.builder().id(1L).book(book).status(BookStatus.AVAILABLE).build(),
                BookCopy.builder().id(2L).book(book).status(BookStatus.LOST).build()
        );

        // When
        List<BookCopyDTO> dtoList = mapper.mapToBookCopyDTOList(copies);

        // Then
        assertEquals(2, dtoList.size());
        assertEquals("AVAILABLE", dtoList.get(0).status());
        assertEquals("LOST", dtoList.get(1).status());
    }
}
