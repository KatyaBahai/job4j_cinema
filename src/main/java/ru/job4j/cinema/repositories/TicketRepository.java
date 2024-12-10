package ru.job4j.cinema.repositories;

import ru.job4j.cinema.model.Ticket;

import java.util.Collection;
import java.util.Optional;

public interface TicketRepository {

    Optional<Ticket> findById(int id);

    Collection<Ticket> findAllByUserId(int userId);

    Optional<Ticket> save(Ticket ticker);
}
