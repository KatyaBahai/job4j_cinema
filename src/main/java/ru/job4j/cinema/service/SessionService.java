package ru.job4j.cinema.service;

import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.dto.SessionDto;

import java.util.Collection;
import java.util.Optional;

public interface SessionService {

    Optional<SessionDto> getSessionById(int id);

    Collection<SessionDto> getAllSessions();
}
