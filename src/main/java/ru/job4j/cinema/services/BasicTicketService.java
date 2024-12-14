package ru.job4j.cinema.services;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.repositories.BasicTicketRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class BasicTicketService implements TicketService {
    private final BasicTicketRepository ticketRepository;

    public BasicTicketService(BasicTicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Optional<Ticket> findById(int id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Collection<Ticket> findAllByUserId(int userId) {
        return ticketRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<Ticket> save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}
