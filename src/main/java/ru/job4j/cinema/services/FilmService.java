package ru.job4j.cinema.services;

import ru.job4j.cinema.dto.FilmDto;


import java.util.Collection;
import java.util.Optional;

public interface FilmService {

    Optional<FilmDto> getFilmById(int id);

    Collection<FilmDto> getAllFilms();
}
