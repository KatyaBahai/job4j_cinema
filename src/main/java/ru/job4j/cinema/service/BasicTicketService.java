package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.repository.BasicTicketRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class BasicTicketService implements TicketService {
    private final BasicTicketRepository ticketRepository;

    public BasicTicketService(BasicTicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Optional<Ticket> find(Ticket ticket) {
        return ticketRepository.find(ticket);
    }

    @Override
    public Optional<Ticket> save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}
