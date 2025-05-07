package com.kodilla.library.mapper;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.BookStatus;
import com.kodilla.library.domain.User;
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
        Long id = userDTO.id();
        String name = userDTO.name();
        String surname = userDTO.surname();
        LocalDate date = userDTO.accountCreatedDate() == null ?
                null : LocalDate.parse(userDTO.accountCreatedDate());
        return new User(id, name, surname, date);
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
        Long id = bookDTO.id();
        String title = bookDTO.title();
        String author = bookDTO.author();
        LocalDate date = bookDTO.publicationDate() == null ?
                null : LocalDate.parse(bookDTO.publicationDate());
        return new Book(id, title, author, date);
    }

    public BookCopyDTO mapToBookCopyDTO(BookCopy bookCopy) {
        Long id = bookCopy.getId();
        BookDTO bookDTO = this.mapToBookDTO(bookCopy.getBook());
        String status = bookCopy.getStatus().toString();
        return new BookCopyDTO(id, bookDTO, status);
    }

    public BookCopy mapToBookCopy(BookCopyDTO bookCopyDTO) {
        Long id = bookCopyDTO.id();
        Book book = this.mapToBook(bookCopyDTO.bookDTO());
        BookStatus status = BookStatus.valueOf(bookCopyDTO.status());
        return new BookCopy(id, book, status);
    }

}
