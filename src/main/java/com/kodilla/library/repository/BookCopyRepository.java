package com.kodilla.library.repository;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.BookStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {

    @Override
    BookCopy save(BookCopy bookCopies);

    List<BookCopy> findAllByBookId(Long bookId);

    BookCopy changeBookCopyStatus(Long bookCopyId, BookStatus newStatus);
}
