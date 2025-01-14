package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DataSourceConfiguration;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.model.Hall;

import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

class BasicHallRepositoryTest {
    private static BasicHallRepository basicHallRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        Properties properties = new Properties();
        try (InputStream inputStream = BasicHallRepository.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DataSourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        basicHallRepository = new BasicHallRepository(sql2o);
    }

    @Test
    public void whenFindByIdThenFound() {
        Hall hall = new Hall(0, "small", 5, 30, "small hall");
        Optional<Hall> expectedHall = basicHallRepository.findById(hall.getId());
        assertThat(expectedHall.get()).usingRecursiveComparison().isEqualTo(hall);
    }

    @Test
    public void whenFindAllThenAllFound() {
        assertThat(basicHallRepository.findAll()).hasSize(2);
    }
}