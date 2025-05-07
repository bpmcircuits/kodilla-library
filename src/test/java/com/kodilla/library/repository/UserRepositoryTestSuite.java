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
    void testGetUserNameById() {
        //given when
        User user = userRepository.findById(1L).get();
        String actual = user.getName();
        System.out.println(actual);
        //then
        assertEquals("John", actual);
    }

}