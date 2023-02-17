package ru.kata.spring.boot_security.demo.services;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    User findById(Long id);

    List<User> findAll();

    void saveUser(User user);

    void deleteById(Long id);

    User findByUsername(String username);

    void updateUser(User user);

}