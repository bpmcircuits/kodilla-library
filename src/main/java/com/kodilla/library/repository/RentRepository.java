package com.kodilla.library.repository;

import com.kodilla.library.domain.Rent;
import org.springframework.data.repository.CrudRepository;

public interface RentRepository extends CrudRepository<Rent, Long> {
}
