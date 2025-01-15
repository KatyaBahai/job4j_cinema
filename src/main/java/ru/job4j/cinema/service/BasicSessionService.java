package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.SessionDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@Service
public class BasicSessionService implements SessionService {
    private final SessionRepository sessionRepository;
    private final HallRepository hallRepository;
    private final FilmRepository filmRepository;

    public BasicSessionService(SessionRepository sessionRepository, HallRepository hallRepository, FilmRepository filmRepository) {
        this.sessionRepository = sessionRepository;
        this.hallRepository = hallRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    public Optional<SessionDto> getSessionById(int id) {
        Optional<FilmSession> sessionOptional = sessionRepository.findById(id);
        if (sessionOptional.isEmpty()) {
            return Optional.empty();
        }
        FilmSession session = sessionOptional.get();
        Optional<Film> filmOptional = filmRepository.findById(session.getFilmId());
        Optional<Hall> hallOptional = hallRepository.findById(session.getHallId());

        SessionDto sessionDto = new SessionDto.SessionDtoBuilder()
                .setSessionId(session.getId())
                .setFilm(filmOptional.get().getName())
                .setDurationInMinutes(filmOptional.get().getDurationInMinutes())
                .setHall(hallOptional.get().getName())
                .setPlaceCount(hallOptional.get().getPlaceCount())
                .setPrice(session.getPrice())
                .setRowCount(hallOptional.get().getRowCount())
                .setStartTime(session.getStartTime())
                .build();
        return Optional.of(sessionDto);
    }

    @Override
    public Collection<SessionDto> getAllSessions() {
        Collection<FilmSession> sessionCollection = sessionRepository.findAll();
        Collection<SessionDto> sessionDtoCollection = new ArrayList<>();

        for (FilmSession session : sessionCollection) {
            Optional<Hall> hallOptional = hallRepository.findById(session.getHallId());
            Optional<Film> filmOptional = filmRepository.findById(session.getFilmId());
            sessionDtoCollection.add(new SessionDto.SessionDtoBuilder()
                    .setSessionId(session.getId())
                    .setFilm(filmOptional.get().getName())
                    .setDurationInMinutes(filmOptional.get().getDurationInMinutes())
                    .setHall(hallOptional.get().getName())
                    .setPlaceCount(hallOptional.get().getPlaceCount())
                    .setPrice(session.getPrice())
                    .setRowCount(hallOptional.get().getRowCount())
                    .setStartTime(session.getStartTime())
                    .build());
        }
        return sessionDtoCollection;
    }
}
