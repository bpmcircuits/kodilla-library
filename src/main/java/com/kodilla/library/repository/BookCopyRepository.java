package com.kodilla.library.repository;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.BookStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {

    @Override
    BookCopy save(BookCopy bookCopy);

    List<BookCopy> findAllByBookId(Long bookId);

    @Override
    List<BookCopy> findAll();

    @Modifying
    @Query
    Integer updateStatusById(@Param("id")Long id, @Param("status")BookStatus status);

    @Query
    long getAmountOfCopiesById(@Param("id")Long id);
}
