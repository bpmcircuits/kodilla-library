package com.kodilla.library.service;

import com.kodilla.library.domain.*;
import com.kodilla.library.exceptions.BookNotFoundException;
import com.kodilla.library.exceptions.BookStatusException;
import com.kodilla.library.exceptions.UserNotFoundException;
import com.kodilla.library.repository.BookCopyRepository;
import com.kodilla.library.repository.BookRepository;
import com.kodilla.library.repository.RentRepository;
import com.kodilla.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final RentRepository rentRepository;
    private final BookCopyRepository bookCopyRepository;

    public User addUser(User user) {
        User newUser = User.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .accountCreated(LocalDate.now())
                .build();
        return userRepository.save(newUser);
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(Long id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public BookCopy addBookCopy(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public BookCopy changeBookCopyStatus(BookCopy bookCopy) {
        return bookCopyRepository.updateStatusById(bookCopy.getId(), bookCopy.getStatus());
    }

    public RentBook rentBook(RentBook rentBook) throws BookStatusException, UserNotFoundException {
        userRepository.findById(rentBook.getUser().getId()).orElseThrow(UserNotFoundException::new);
        BookCopy bookCopy = rentBook.getBookCopy();
        if (bookCopy.getStatus() == BookStatus.RENTED) {
            throw new BookStatusException("Book is currently borrowed until %s".formatted(rentBook.getReturnDate()));
        } else if (bookCopy.getStatus() == BookStatus.DAMAGED || bookCopy.getStatus() == BookStatus.LOST) {
            throw new BookStatusException("Book is not available for renting.");
        }
        bookCopy.setStatus(BookStatus.RENTED);
        return rentRepository.save(rentBook);
    }

    public RentBook returnBook(RentBook rentBook) throws UserNotFoundException {
        userRepository.findById(rentBook.getUser().getId()).orElseThrow(UserNotFoundException::new);
        BookCopy bookCopy = rentBook.getBookCopy();
        bookCopy.setStatus(BookStatus.AVAILABLE);
        return rentRepository.save(rentBook);
    }

    public List<RentBook> getAllRents() {
        return rentRepository.findAll();
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<BookCopy> getAllBookCopies() {
        return bookCopyRepository.findAll();
    }

    public Long getAmountOfCopiesById(Long id) {
        return bookCopyRepository.getAmountOfCopiesById(id);
    }
}
