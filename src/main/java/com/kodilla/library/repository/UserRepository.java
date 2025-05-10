package com.kodilla.library.repository;

import com.kodilla.library.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    User findUserByNameIs(String name);
}
