package com.kodilla.library.repository;

import com.kodilla.library.domain.RentBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RentRepository extends CrudRepository<RentBook, Long> {
    @Override
    List<RentBook> findAll();
}
