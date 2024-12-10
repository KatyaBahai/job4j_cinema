package ru.job4j.cinema.repositories;

import ru.job4j.cinema.model.FilmSession;

import java.util.Collection;
import java.util.Optional;

public interface SessionRepository {
    Optional<FilmSession> findById(int id);

    Collection<FilmSession> findAll();
}
