package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.GenreRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class BasicFilmService implements FilmService {
    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;

    public BasicFilmService(FilmRepository filmRepository, GenreRepository genreRepository) {
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
        if (genreOptional.isEmpty()) {
            return Optional.empty();
        }
        FilmDto filmDto = new FilmDto.FilmDtoBuilder()
                .setId(film.getId())
                .setName(film.getName())
                .setFileId(film.getFileId())
                .setDescription(film.getDescription())
                .setDurationInMinutes(film.getDurationInMinutes())
                .setGenre(genreOptional.get().getName())
                .setMinimalAge(film.getMinimalAge())
                .setYear(film.getYear())
                .build();
        return Optional.of(filmDto);

    }

    @Override
    public Collection<FilmDto> getAllFilms() {
        Collection<Film> filmCollection = filmRepository.findAll();
        Collection<FilmDto> filmDtoCollection = new ArrayList<>();
        for (Film film : filmCollection) {
            Optional<Genre> genreOptional = genreRepository.findById(film.getGenreId());
           filmDtoCollection.add(new FilmDto.FilmDtoBuilder()
                   .setId(film.getId())
                   .setName(film.getName())
                   .setFileId(film.getFileId())
                   .setDescription(film.getDescription())
                   .setDurationInMinutes(film.getDurationInMinutes())
                   .setGenre(genreOptional.get().getName())
                   .setMinimalAge(film.getMinimalAge())
                   .setYear(film.getYear())
                   .build());
        }
        return filmDtoCollection;
    }
}
