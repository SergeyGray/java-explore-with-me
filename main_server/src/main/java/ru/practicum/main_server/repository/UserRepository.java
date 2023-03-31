package ru.practicum.main_server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.main_server.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    Page<User> findByIdIn(List<Integer> ids, Pageable page);
}