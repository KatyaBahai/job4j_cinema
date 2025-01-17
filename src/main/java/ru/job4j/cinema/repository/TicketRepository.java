package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Ticket;

import java.util.Collection;
import java.util.Optional;

public interface TicketRepository {

    Optional<Ticket> find(Ticket ticket);

    Optional<Ticket> save(Ticket ticket);
}
