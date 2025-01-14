package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.service.FilmService;


import java.util.List;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilmControllerTest {
    private FilmController filmController;
    private FilmService filmService;

    @BeforeEach
    public void initServices() {
        filmService = mock(FilmService.class);
        filmController = new FilmController(filmService);
    }

    @Test
    public void whenGetAllFilmsThenFilmsList() {
        FilmDto filmDto1 = new FilmDto(
                0, "Finding Nemo", "-", 1990, 12, 120, 2, "family");
        FilmDto filmDto2 = new FilmDto(
                0, "Shrek", "small", 1992, 12, 120, 2, "family");
        List<FilmDto> expectedFilmDtos = List.of(filmDto1, filmDto2);
        when(filmService.getAllFilms()).thenReturn(expectedFilmDtos);

        Model model = new ConcurrentModel();
        String view = filmController.getAll(model);
        var actualFilmDtos = model.getAttribute("filmDtos");

        assertThat(view).isEqualTo("/films/list");
        assertThat(actualFilmDtos).isEqualTo(expectedFilmDtos);
    }
}