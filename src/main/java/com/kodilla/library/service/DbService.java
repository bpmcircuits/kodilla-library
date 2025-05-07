package com.kodilla.library.service;

import com.kodilla.library.domain.*;
import com.kodilla.library.repository.BookCopyRepository;
import com.kodilla.library.repository.BookRepository;
import com.kodilla.library.repository.RentRepository;
import com.kodilla.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DbService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final RentRepository rentRepository;
    private final BookCopyRepository bookCopyRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public BookCopy addBookCopy(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public BookCopy changeBookCopyStatus(BookCopy bookCopy, BookStatus newStatus) {
        return bookCopyRepository.changeBookCopyStatus(bookCopy.getId(), newStatus);
    }

    public Rent rentBook(Rent rent) {
        return rentRepository.save(rent);
    }

    public Rent returnBook(Rent rent) {
        return rentRepository.save(rent);
    }

}
