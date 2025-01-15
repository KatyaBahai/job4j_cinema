package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DataSourceConfiguration;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.User;

import java.io.InputStream;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;

import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.*;

class BasicFilmRepositoryTest {
    private static BasicFilmRepository basicFilmRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        Properties properties = new Properties();
        try (InputStream inputStream = BasicFilmRepository.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DataSourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        basicFilmRepository = new BasicFilmRepository(sql2o);
    }

    @Test
    public void whenFindByIdThenFound() {
        Film film = new Film.FilmBuilder()
                .setId(0)
                .setName("Blade Runner")
                .setDescription("-")
                .setFileId(0)
                .setGenreId(5)
                .setMinimalAge(12)
                .setDurationInMinutes(120)
                .setYear(1982)
                .build();
        Optional<Film> expectedFilm = basicFilmRepository.findById(film.getId());
        assertThat(expectedFilm.get()).usingRecursiveComparison().isEqualTo(film);
    }

    @Test
    public void whenFindAllThenAllFound() {
        assertThat(basicFilmRepository.findAll()).hasSize(5);
    }
}