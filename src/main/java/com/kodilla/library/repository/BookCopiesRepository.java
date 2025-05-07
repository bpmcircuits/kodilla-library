package com.kodilla.library.repository;

import com.kodilla.library.domain.BookCopies;
import org.springframework.data.repository.CrudRepository;

public interface BookCopiesRepository extends CrudRepository<BookCopies, Long> {

}
