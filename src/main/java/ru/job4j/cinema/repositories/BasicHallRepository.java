package ru.job4j.cinema.repositories;

import org.springframework.stereotype.Repository;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Hall;

import java.util.Collection;
import java.util.Optional;

@Repository
public class BasicHallRepository implements HallRepository {
    private final Sql2o sql2o;

    public BasicHallRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<Hall> findById(int id) {
        try (var connection = sql2o.open()) {
            Query query = connection.createQuery("SELECT * FROM halls WHERE id = :id");
            return Optional.of(query
                    .addParameter("id", id)
                    .executeAndFetchFirst(Hall.class));
        }
    }

    @Override
    public Collection<Hall> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM halls");
            return query.executeAndFetch(Hall.class);
        }
    }
}
