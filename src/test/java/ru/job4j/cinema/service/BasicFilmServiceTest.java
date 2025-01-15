package ru.job4j.cinema.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.GenreRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BasicFilmServiceTest {
    private FilmRepository filmRepository;
    private GenreRepository genreRepository;
    private FilmService filmService;

    @BeforeEach
    public void initServices() {
        filmRepository = mock(FilmRepository.class);
        genreRepository = mock(GenreRepository.class);
        filmService = new BasicFilmService(filmRepository, genreRepository);
    }

    @Test
    public void whenGetFilmByIdThenGetFilmDto() {
        int filmId = 1;
        Film film = new Film.FilmBuilder()
                .setId(filmId)
                .setName("Film Name")
                .setDescription("Description")
                .setFileId(101)
                .setGenreId(2)
                .setMinimalAge(13)
                .setDurationInMinutes(120)
                .setYear(2021)
                .build();

        Genre genre = new Genre(2, "Action");

        when(filmRepository.findById(filmId)).thenReturn(Optional.of(film));
        when(genreRepository.findById(film.getGenreId())).thenReturn(Optional.of(genre));

        Optional<FilmDto> optionalFilmDto = filmService.getFilmById(filmId);

        FilmDto filmDto = optionalFilmDto.get();
        assertEquals(filmId, filmDto.getId());
        assertEquals(film.getName(), filmDto.getName());
        assertEquals(genre.getName(), filmDto.getGenre());
    }

    @Test
    public void whenGetNonExistingFilmByIdThenGetEmptyOptional() {
        int filmId = 1;

        when(filmRepository.findById(filmId)).thenReturn(Optional.empty());

        Optional<FilmDto> optionalFilmDto = filmService.getFilmById(filmId);

        assertThat(optionalFilmDto).isEmpty();
        verifyNoInteractions(genreRepository);
    }

    @Test
    public void whenGetNonExistingGenreThenGetEmptyOptional() {
        int filmId = 1;
        Film film = new Film.FilmBuilder()
                .setId(filmId)
                .setName("Film Name")
                .setDescription("Description")
                .setFileId(101)
                .setGenreId(2)
                .setMinimalAge(13)
                .setDurationInMinutes(120)
                .setYear(2021)
                .build();

        when(filmRepository.findById(filmId)).thenReturn(Optional.of(film));
        when(genreRepository.findById(filmId)).thenReturn(Optional.empty());

        Optional<FilmDto> optionalFilmDto = filmService.getFilmById(filmId);

        assertThat(optionalFilmDto).isEmpty();
    }

    @Test
    public void whenGetAllFilmsThenGetFilmDtos() {
        Film film1 = new Film.FilmBuilder()
                .setId(2)
                .setName("Film Name")
                .setDescription("Description")
                .setFileId(101)
                .setGenreId(2)
                .setMinimalAge(13)
                .setDurationInMinutes(120)
                .setYear(2021)
                .build();
        Film film2 = new Film.FilmBuilder()
                .setId(1)
                .setName("Film Name")
                .setDescription("Description")
                .setFileId(101)
                .setGenreId(3)
                .setMinimalAge(13)
                .setDurationInMinutes(120)
                .setYear(2021)
                .build();
        Genre genre1 = new Genre(2, "Action");
        Genre genre2 = new Genre(3, "Comedy");

        List<Film> expectedFilms = List.of(film1, film2);
        when(filmRepository.findAll()).thenReturn(expectedFilms);
        when(genreRepository.findById(film1.getGenreId())).thenReturn(Optional.of(genre1));
        when(genreRepository.findById(film2.getGenreId())).thenReturn(Optional.of(genre2));

        Collection<FilmDto> filmDtoCollection = filmService.getAllFilms();

        List<FilmDto> filmDtos = new ArrayList<>(filmDtoCollection);
        assertEquals(film1.getName(), filmDtos.get(0).getName());
        assertEquals(film2.getName(), filmDtos.get(1).getName());
        assertEquals(genre1.getName(), filmDtos.get(0).getGenre());
        assertEquals(genre2.getName(), filmDtos.get(1).getGenre());

        assertThat(filmDtoCollection).hasSize(2);
    }

}