package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DataSourceConfiguration;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Genre;

import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

class BasicGenreRepositoryTest {
    private static BasicGenreRepository basicGenreRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        Properties properties = new Properties();
        try (InputStream inputStream = BasicGenreRepository.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DataSourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        basicGenreRepository = new BasicGenreRepository(sql2o);
    }

    @Test
    public void whenFindByIdThenFound() {
        Genre genre = new Genre(1, "romcom");
        Optional<Genre> expectedGenre = basicGenreRepository.findById(genre.getId());
        assertThat(expectedGenre.get()).usingRecursiveComparison().isEqualTo(genre);
    }

    @Test
    public void whenFindAllThenAllFound() {
        assertThat(basicGenreRepository.findAll()).hasSize(6);
    }
}