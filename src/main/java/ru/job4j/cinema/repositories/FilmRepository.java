package ru.job4j.cinema.repositories;

import ru.job4j.cinema.model.Film;

import java.util.Collection;
import java.util.Optional;

public interface FilmRepository {

    Optional<Film> findById(int id);

    Collection<Film> findAll();
}
