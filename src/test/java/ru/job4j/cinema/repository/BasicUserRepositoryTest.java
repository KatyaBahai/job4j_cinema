package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;
import ru.job4j.cinema.configuration.DataSourceConfiguration;
import ru.job4j.cinema.model.User;
import static java.util.Optional.empty;

import static org.assertj.core.api.Assertions.*;

class BasicUserRepositoryTest {
    private static BasicUserRepository basicUserRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        Properties properties = new Properties();
        try (InputStream inputStream = BasicUserRepository.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DataSourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        basicUserRepository = new BasicUserRepository(sql2o);
    }

    @AfterEach
    public void clearUsers() {
        Collection<User> users = basicUserRepository.findAll();
        for (var user : users) {
            basicUserRepository.deleteById(user.getId());
        }
    }

    @Test
    public void whenSaveThenSaved() {
        User user = new User(0, "Ivan", "email@mail.ru", "1234");
        basicUserRepository.save(user);
        assertThat(user).usingRecursiveComparison().isEqualTo(basicUserRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).get());
    }

    @Test
    public void whenEmailAlreadyExistsThenNotSaved() {
        User user = new User(0,  "Ivan", "email@mail.ru", "1234");
        User user2 = new User(0, "Ian", "email@mail.ru", "3456");
        basicUserRepository.save(user);
        Optional<User> secondUser = basicUserRepository.save(user2);
        assertThat(secondUser).isEqualTo(empty());
    }

}