package ru.practicum.main_server.service.user_service;

import ru.practicum.main_server.dto.UserDto;
import ru.practicum.main_server.model.User;

import java.util.List;

public interface UserService {

    List<UserDto> getUsers(List<Integer> ids, int from, int size);

    UserDto create(User user);

    void deleteUserId(int userId);

    User getById(int userId);
}
