package com.kodilla.library.repository;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.BookStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookCopyRepositoryTestSuite {

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testBookCopiesRepository() {
        //given
        Book bookOne = new Book(100L, "TestOne", "TestAuthor", null);
        Book bookTwo = new Book(101L,"TestTwo", "TestAuthor", null);
        bookRepository.save(bookOne);
        bookRepository.save(bookTwo);

        BookCopy bookCopy1 = new BookCopy(201L, bookOne, BookStatus.AVAILABLE);
        BookCopy bookCopy2 = new BookCopy(202L, bookOne, BookStatus.DAMAGED);
        BookCopy bookCopy3 = new BookCopy(203L, bookTwo, BookStatus.AVAILABLE);
        BookCopy bookCopy4 = new BookCopy(204L, bookTwo, BookStatus.LOST);
        bookCopyRepository.save(bookCopy1);
        bookCopyRepository.save(bookCopy2);
        bookCopyRepository.save(bookCopy3);
        bookCopyRepository.save(bookCopy4);

        //when
        Long bookOneId = bookOne.getId();
        Long bookTwoId = bookTwo.getId();

        List<BookCopy> actualBookOne = bookCopyRepository.findAllByBookId(bookOneId);
        List<BookCopy> actualBookTwo = bookCopyRepository.findAllByBookId(bookTwoId);

        //then
        assertEquals(2, actualBookOne.size());
        assertEquals(2, actualBookTwo.size());

        //clean up
        bookCopyRepository.deleteAll();
        bookRepository.deleteAll();
    }
}