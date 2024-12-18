package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.FilmSession;

import java.util.Collection;
import java.util.Optional;

@Repository
public class BasicSessionRepository implements SessionRepository {
    private final Sql2o sql2o;

    public BasicSessionRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<FilmSession> findById(int id) {
        try (var connection = sql2o.open()) {
            Query query = connection.createQuery("SELECT * FROM film_sessions WHERE id = :id");
            return Optional.of(query
                    .addParameter("id", id)
                    .executeAndFetchFirst(FilmSession.class));
        }
    }

    @Override
    public Collection<FilmSession> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM film_sessions");
            return query.executeAndFetch(FilmSession.class);
        }
    }
}
