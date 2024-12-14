package ru.job4j.cinema.services;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.repositories.BasicUserRepository;

import java.util.Optional;

@Service
public class BasicUserService implements UserService {
    private final BasicUserRepository userRepository;

    public BasicUserService(BasicUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
