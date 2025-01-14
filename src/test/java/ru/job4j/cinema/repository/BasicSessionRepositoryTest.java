package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DataSourceConfiguration;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Hall;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

class BasicSessionRepositoryTest {
    private static BasicSessionRepository basicSessionRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        Properties properties = new Properties();
        try (InputStream inputStream = BasicSessionRepository.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DataSourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        basicSessionRepository = new BasicSessionRepository(sql2o);
    }

    @Test
    public void whenFindByIdThenFound() {
        var startTime = LocalDateTime.of(2025, 11, 19, 20, 30);
        var endTime = LocalDateTime.of(2025, 11, 19, 22, 30);
        FilmSession session = new FilmSession(1, 1, 0, startTime, endTime, 500);
        Optional<FilmSession> expectedSession = basicSessionRepository.findById(session.getId());
        assertThat(expectedSession.get()).usingRecursiveComparison().isEqualTo(session);
    }

    @Test
    public void whenFindAllThenAllFound() {
        assertThat(basicSessionRepository.findAll()).hasSize(5);
    }

}