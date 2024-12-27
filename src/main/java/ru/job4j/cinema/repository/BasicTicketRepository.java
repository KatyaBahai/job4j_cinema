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
    public Optional<Ticket> find(int rowNumber, int placeNumber, int sessionId) {
        try (Connection connection = sql2o.open()) {
            String sql = "SELECT * FROM tickets WHERE row_number = :rowNumber AND place_number = :placeNumber AND session_id = :sessionId";
            Query query = connection.createQuery(sql)
                    .addParameter("rowNumber", rowNumber)
                    .addParameter("placeNumber", placeNumber)
                    .addParameter("sessionId", sessionId);
            Ticket ticket = query.setColumnMappings(Ticket.COLUMN_MAPPING).executeAndFetchFirst(Ticket.class);
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

}
