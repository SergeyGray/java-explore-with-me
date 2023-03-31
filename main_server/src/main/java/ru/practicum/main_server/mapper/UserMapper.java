package ru.practicum.main_server.mapper;

import ru.practicum.main_server.dto.UserCreateDto;
import ru.practicum.main_server.dto.UserDto;
import ru.practicum.main_server.model.User;

public class UserMapper {

    public static UserDto userToDto(User user) {
        return new UserDto(user.getEmail(),
                user.getId(),
                user.getName());
    }

    public static User userCreateDtoToUser(UserCreateDto userCreateDto) {
        return new User(userCreateDto.getEmail(), userCreateDto.getName());
    }
}
