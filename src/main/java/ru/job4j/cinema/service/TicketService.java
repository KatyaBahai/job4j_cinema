package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Ticket;

import java.util.Collection;
import java.util.Optional;

public interface TicketService {

    Optional<Ticket> findById(int id);

    Collection<Ticket> findAllByUserId(int userId);

    Optional<Ticket> save(Ticket ticket);
}
