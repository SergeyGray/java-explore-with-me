package ru.practicum.main_server.service.user_service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.main_server.dto.UserDto;
import ru.practicum.main_server.exception.NotFoundException;
import ru.practicum.main_server.mapper.UserMapper;
import ru.practicum.main_server.model.User;
import ru.practicum.main_server.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository repository;

    @Override
    public List<UserDto> getUsers(List<Integer> ids, int from, int size) {

        PageRequest page = PageRequest.of(from, size);
        if (ids.isEmpty()) {
            return repository.findAll(page).getContent()
                    .stream().map(UserMapper::userToDto).collect(Collectors.toList());
        }
        return repository.findByIdIn(ids, page).getContent()
                .stream().map(UserMapper::userToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto create(User user) {
        return UserMapper.userToDto(repository.save(user));
    }

    @Override
    public void deleteUserId(int userId) {
        repository.deleteById(userId);

    }

    @Override
    public User getById(int userId) {
        try {
            return repository.findById(userId).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException("Пользователь не найден");
        }
    }
}
