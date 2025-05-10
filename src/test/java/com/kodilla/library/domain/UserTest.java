package com.kodilla.library.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getId() {
        // Given
        User user = User.builder().build();

        // When & Then
        assertNull(user.getId());
    }

    @Test
    void getName() {
        // Given
        User user = User.builder().name("Anna").build();

        // When & Then
        assertEquals("Anna", user.getName());
    }

    @Test
    void getSurname() {
        // Given
        User user = User.builder().surname("Kowalska").build();

        // When & Then
        assertEquals("Kowalska", user.getSurname());
    }

    @Test
    void getAccountCreated() {
        // Given
        LocalDate date = LocalDate.of(2023, 1, 1);
        User user = User.builder().accountCreated(date).build();

        // When & Then
        assertEquals(date, user.getAccountCreated());
    }

    @Test
    void getRentBooks() {
        // Given
        User user = User.builder().build();

        // When & Then
        assertNotNull(user.getRentBooks());
        assertTrue(user.getRentBooks().isEmpty());
    }

    @Test
    void builder() {
        // Given
        LocalDate date = LocalDate.of(2024, 12, 31);
        User user = User.builder()
                .name("John")
                .surname("Smith")
                .accountCreated(date)
                .build();

        // When & Then
        assertEquals("John", user.getName());
        assertEquals("Smith", user.getSurname());
        assertEquals(date, user.getAccountCreated());
    }
}
