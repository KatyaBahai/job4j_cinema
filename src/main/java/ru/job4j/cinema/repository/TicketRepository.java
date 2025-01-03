package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Ticket;

import java.util.Collection;
import java.util.Optional;

public interface TicketRepository {

    Optional<Ticket> find(int rowNumber, int placeNumber, int sessionId);

    Collection<Ticket> findAllByUserId(int userId);

    Optional<Ticket> save(Ticket ticket);
}
