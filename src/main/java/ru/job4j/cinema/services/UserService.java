package ru.job4j.cinema.services;

import ru.job4j.cinema.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> save(User user);

    Optional<User> findByEmailAndPassword(String email, String password);
}
