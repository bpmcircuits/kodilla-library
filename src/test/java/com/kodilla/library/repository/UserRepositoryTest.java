package com.kodilla.library.repository;

import com.kodilla.library.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndFindUserById() {
        // Given
        User user = User.builder()
                .name("John")
                .surname("Smith")
                .accountCreated(LocalDate.of(2024, 5, 10))
                .build();

        // When
        User saved = userRepository.save(user);
        Optional<User> found = userRepository.findById(saved.getId());

        // Then
        assertTrue(found.isPresent());
        assertEquals("John", found.get().getName());
        assertEquals("Smith", found.get().getSurname());
    }

    @Test
    void shouldFindUserByName() {
        // Given
        userRepository.save(User.builder().name("Alice").surname("Nowak").build());

        // When
        User found = userRepository.findUserByNameIs("Alice");

        // Then
        assertNotNull(found);
        assertEquals("Nowak", found.getSurname());
    }

    @Test
    void shouldFindAllUsers() {
        // Given
        userRepository.save(User.builder().name("A").surname("X").build());
        userRepository.save(User.builder().name("B").surname("Y").build());

        // When
        List<User> users = userRepository.findAll();

        // Then
        assertEquals(3, users.size()); //three because there is already one user in db
    }

    @Test
    void shouldReturnEmptyWhenUserNotFound() {
        // When
        Optional<User> result = userRepository.findById(999L);

        // Then
        assertTrue(result.isEmpty());
    }
}
