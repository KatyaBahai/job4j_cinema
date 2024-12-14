package ru.job4j.cinema.services;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.repositories.BasicFilmRepository;
import ru.job4j.cinema.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class BasicFilmService implements FilmService {
    private final BasicFilmRepository filmRepository;
    private final GenreRepository genreRepository;

    public BasicFilmService(BasicFilmRepository filmRepository, GenreRepository genreRepository) {
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Optional<FilmDto> getFilmById(int id) {
        Optional<Film> filmOptional = filmRepository.findById(id);
        if (filmOptional.isEmpty()) {
            return Optional.empty();
        }
        Film film = filmOptional.get();
        Optional<Genre> genreOptional = genreRepository.findById(film.getGenreId());
        
        FilmDto filmDto = new FilmDto(film.getId(), film.getName(),
                film.getDescription(), film.getYear(),
                film.getMinimalAge(), film.getDurationInMinutes(),
                film.getFileId(), genreOptional.get().getName());
        return Optional.of(filmDto);

    }

    @Override
    public Collection<FilmDto> getAllFilms() {
        Collection<Film> filmCollection = filmRepository.findAll();
        Collection<FilmDto> filmDtoCollection = new ArrayList<>();
        for (Film film : filmCollection) {
            Optional<Genre> genreOptional = genreRepository.findById(film.getGenreId());
           filmDtoCollection.add(new FilmDto(film.getId(), film.getName(),
                   film.getDescription(), film.getYear(),
                   film.getMinimalAge(), film.getDurationInMinutes(),
                   film.getFileId(), genreOptional.get().getName()));
        }
        return filmDtoCollection;
    }
}
