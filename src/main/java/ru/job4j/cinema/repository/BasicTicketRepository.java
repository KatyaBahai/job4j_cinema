package ru.job4j.cinema.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Ticket;

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
    public Optional<Ticket> findById(int id) {
        try (Connection connection = sql2o.open()) {
            String sql = "SELECT * FROM tickets WHERE id = :id";
            Query query = connection.createQuery(sql)
                    .addParameter("id", id);
            Ticket ticket = query.executeAndFetchFirst(Ticket.class);
            return Optional.ofNullable(ticket);
        }
    }

    @Override
    public Collection<Ticket> findAllByUserId(int userId) {
        try (Connection connection = sql2o.open()) {
            String sql = "SELECT * FROM tickets WHERE user_id = :id";
            Query query = connection.createQuery(sql)
                    .addParameter("user_id", userId);
            return query.setColumnMappings(Ticket.COLUMN_MAPPING).executeAndFetch(Ticket.class);
        }
    }

    @Override
    public Optional<Ticket> save(Ticket ticket) {
        try (Connection connection = sql2o.open()) {
            String sql = """
                      INSERT INTO tickets(session_id, row_number, place_number, user_id)
                      VALUES (:sessionId, :rowNumber, :placeNumber :user_id)
                      """;
            Query query = connection.createQuery(sql, true)
                    .addParameter("session_id", ticket.getSessionId())
                    .addParameter("row_number", ticket.getRowNumber())
                    .addParameter("place_number", ticket.getPlaceNumber())
                    .addParameter("user_id", ticket.getUserId());
            int serialID = query.executeUpdate().getKey(Integer.class);
            ticket.setId(serialID);
            return Optional.of(ticket);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Optional.empty();
    }
}
