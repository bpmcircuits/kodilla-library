package com.kodilla.library.mapper;

import com.kodilla.library.domain.User;
import com.kodilla.library.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper {

    public UserDTO mapToUserDTO(User user) {
        Long id = user.getId();
        String date = user.getAccountCreated().toString();
        String name = user.getName();
        String surname = user.getSurname();
        return new UserDTO(id, name, surname, date);
    }

    public User mapToUser(UserDTO userDTO) {
        String name = userDTO.name();
        String surname = userDTO.surname();
        return User.builder().name(name).surname(surname).build();
    }

    public List<UserDTO> mapToUserDTOList(final List<User> users) {
        return users.stream().map(this::mapToUserDTO).toList();
    }

}
