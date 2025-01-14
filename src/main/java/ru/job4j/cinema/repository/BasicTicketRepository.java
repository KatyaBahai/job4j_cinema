package ru.job4j.cinema.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;

import java.util.Collection;
import java.util.Optional;

@Repository
@Slf4j
public class BasicTicketRepository implements TicketRepository {
    Sql2o sql2o;

    public BasicTicketRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<Ticket> find(Ticket ticket) {
        try (Connection connection = sql2o.open()) {
            String sql = "SELECT * FROM tickets WHERE row_number = :rowNumber AND place_number = :placeNumber AND session_id = :sessionId";
            Query query = connection.createQuery(sql)
                    .addParameter("rowNumber", ticket.getRowNumber())
                    .addParameter("placeNumber", ticket.getPlaceNumber())
                    .addParameter("sessionId", ticket.getSessionId());
            Ticket existingTicket = query.setColumnMappings(Ticket.COLUMN_MAPPING).executeAndFetchFirst(Ticket.class);
            return Optional.ofNullable(existingTicket);
        }
    }

    @Override
    public Optional<Ticket> save(Ticket ticket) {
        try (Connection connection = sql2o.open()) {
            String sql = """
                      INSERT INTO tickets(session_id, row_number, place_number, user_id)
                      VALUES (:sessionId, :rowNumber, :placeNumber, :userId)
                      """;
            Query query = connection.createQuery(sql, true)
                    .addParameter("sessionId", ticket.getSessionId())
                    .addParameter("rowNumber", ticket.getRowNumber())
                    .addParameter("placeNumber", ticket.getPlaceNumber())
                    .addParameter("userId", ticket.getUserId());
            int serialID = query.executeUpdate().getKey(Integer.class);
            ticket.setId(serialID);
            return Optional.of(ticket);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    public boolean deleteById(int id) {
        try (Connection connection = sql2o.open()) {
            String sql = "DELETE FROM tickets WHERE id = :id";
            Query query = connection.createQuery(sql)
                    .addParameter("id", id);
            int affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

    public Collection<Ticket> findAll() {
        try (Connection connection = sql2o.open()) {
            String sql = "SELECT * FROM tickets";
            Query query = connection.createQuery(sql);
            return query.setColumnMappings(Ticket.COLUMN_MAPPING).executeAndFetch(Ticket.class);
        }
    }

}
