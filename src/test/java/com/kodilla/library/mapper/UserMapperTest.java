package com.kodilla.library.mapper;

import com.kodilla.library.domain.User;
import com.kodilla.library.dto.UserDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private final UserMapper mapper = new UserMapper();

    @Test
    void shouldMapUserToUserDTO() {
        // Given
        User user = User.builder()
                .id(1L)
                .name("Anna")
                .surname("Nowak")
                .accountCreated(LocalDate.of(2024, 5, 10))
                .build();

        // When
        UserDTO dto = mapper.mapToUserDTO(user);

        // Then
        assertEquals(1L, dto.id());
        assertEquals("Anna", dto.name());
        assertEquals("Nowak", dto.surname());
        assertEquals("2024-05-10", dto.accountCreatedDate());
    }

    @Test
    void shouldMapUserDTOToUser() {
        // Given
        UserDTO dto = new UserDTO(2L, "John", "Smith", "2023-01-01");

        // When
        User user = mapper.mapToUser(dto);

        // Then
        assertEquals("John", user.getName());
        assertEquals("Smith", user.getSurname());
        assertNull(user.getAccountCreated());
    }

    @Test
    void shouldMapUserListToDTOList() {
        // Given
        List<User> users = List.of(
                User.builder().id(1L).name("A").surname("B").accountCreated(LocalDate.of(2022, 1, 1)).build(),
                User.builder().id(2L).name("C").surname("D").accountCreated(LocalDate.of(2022, 2, 2)).build()
        );

        // When
        List<UserDTO> result = mapper.mapToUserDTOList(users);

        // Then
        assertEquals(2, result.size());
        assertEquals("A", result.get(0).name());
        assertEquals("C", result.get(1).name());
    }
}
