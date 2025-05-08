package com.kodilla.library.repository;

import com.kodilla.library.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateUserAndGetItById() {
        //given
        User user = User.builder().name("John").surname("Doe").build();
        //when
        userRepository.save(user);
        Long id = user.getId();
        User actual = userRepository.findById(id).get();
        //then
        assertEquals("John", actual.getName());
        //cleanup
        userRepository.deleteById(id);
    }

    @Test
    void getUserByName() {
        User user = userRepository.findUserByNameIs("user");
        assertNotNull(user);
        assertEquals("user", user.getName());
    }

}