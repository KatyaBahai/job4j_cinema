package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSession;

import java.util.Collection;
import java.util.Optional;

@Repository
public class BasicFilmRepository implements FilmRepository {
    private final Sql2o sql2o;

    public BasicFilmRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<Film> findById(int id) {
        try (var connection = sql2o.open()) {
            Query query = connection.createQuery("SELECT * FROM films WHERE id = :id");
            return Optional.of(query
                    .setColumnMappings(Film.COLUMN_MAPPING)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Film.class));
        }
    }

    @Override
    public Collection<Film> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM films");
            return query.setColumnMappings(Film.COLUMN_MAPPING).executeAndFetch(Film.class);
        }
    }
}
