package ru.job4j.cinema.repositories;

import org.springframework.stereotype.Repository;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Genre;

import java.util.Collection;
import java.util.Optional;

@Repository
public class BasicGenreRepository implements GenreRepository {
    private final Sql2o sql2o;

    public BasicGenreRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<Genre> findById(int id) {
        try (var connection = sql2o.open()) {
            Query query = connection.createQuery("SELECT * FROM genres WHERE id = :id");
            return Optional.of(query
                    .addParameter("id", id)
                    .executeAndFetchFirst(Genre.class));
        }
    }

    @Override
    public Collection<Genre> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM genres");
            return query.executeAndFetch(Genre.class);
        }
    }
}
