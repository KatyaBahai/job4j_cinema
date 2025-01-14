package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DataSourceConfiguration;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;

import java.io.InputStream;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;

import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.*;

class BasicTicketRepositoryTest {
    private static BasicTicketRepository basicTicketRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        Properties properties = new Properties();
        try (InputStream inputStream = BasicTicketRepository.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DataSourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        basicTicketRepository = new BasicTicketRepository(sql2o);
    }

    @AfterEach
    public void clearTickets() {
        Collection<Ticket> tickets = basicTicketRepository.findAll();
        for (var ticket : tickets) {
            basicTicketRepository.deleteById(ticket.getId());
        }
    }

    @Test
    public void whenSaveThenSaved() {
        Ticket ticket = new Ticket(1, 5, 3, 5);
        basicTicketRepository.save(ticket);
        Ticket expectedTicket = basicTicketRepository.find(ticket).get();
        ticket.setId(expectedTicket.getId());
        assertThat(ticket).usingRecursiveComparison().isEqualTo(expectedTicket);
    }

    @Test
    public void whenTicketAlreadyPurchasedThenNotSaved() {
        Ticket ticket = new Ticket(0, 1, 2, 2, 5);
        Ticket purchasedTicket = basicTicketRepository.save(ticket).get();
        Optional<Ticket> notPurchasedTicketOptional = basicTicketRepository.save(ticket);
        assertThat(notPurchasedTicketOptional).isEqualTo(empty());
    }
}