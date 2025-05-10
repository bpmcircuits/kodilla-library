package com.kodilla.library.mapper;

import com.kodilla.library.domain.*;
import com.kodilla.library.dto.BookCopyDTO;
import com.kodilla.library.dto.BookDTO;
import com.kodilla.library.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LibraryMapper {

    public UserDTO mapToUserDTO(User user) {
        Long id = user.getId();
        String date = user.getAccountCreated().toString();
        String name = user.getName();
        String surname = user.getSurname();
        return new UserDTO(id, name, surname, date);
    }

    public User mapToUser(UserDTO userDTO) {
        String name = userDTO.name();
        String surname = userDTO.surname();
        return User.builder().name(name).surname(surname).build();
    }

    public List<UserDTO> mapToUserDTOList(final List<User> users) {
        return users.stream().map(this::mapToUserDTO).toList();
    }

    public BookDTO mapToBookDTO(Book book) {
        Long id = book.getId();
        String title = book.getTitle();
        String author = book.getAuthor();
        String date = book.getPublicationDate() == null ?
                null : book.getPublicationDate().toString();
        return new BookDTO(id, title, author, date);
    }

    public List<BookDTO> mapToBookDTOList(final List<Book> books) {
        return books.stream().map(this::mapToBookDTO).toList();
    }

    public Book mapToBook(BookDTO bookDTO) {
        String title = bookDTO.title();
        String author = bookDTO.author();
        LocalDate date = bookDTO.publicationDate() == null ?
                null : LocalDate.parse(bookDTO.publicationDate());
        return Book.builder().title(title).author(author).publicationDate(date).build();
    }

    public BookCopyDTO mapToBookCopyDTO(BookCopy bookCopy) {
        Long id = bookCopy.getId();
        Long book_id = this.mapToBookDTO(bookCopy.getBook()).id();
        String status = bookCopy.getStatus().toString();
        return new BookCopyDTO(id, book_id, status);
    }

    public List<BookCopyDTO> mapToBookCopyDTOList(final List<BookCopy> bookCopies) {
        return bookCopies.stream().map(this::mapToBookCopyDTO).toList();
    }

    public BookCopy mapToBookCopy(BookCopyDTO dto, Book book) {
        BookStatus status = BookStatus.valueOf(dto.status());
        return BookCopy.builder().book(book).status(status).build();
    }
}
