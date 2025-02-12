package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.File;

import java.util.Collection;
import java.util.Optional;

@Repository
public class BasicFileRepository implements FileRepository {
    private final Sql2o sql2o;

    public BasicFileRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<File> findById(int id) {
        try (var connection = sql2o.open()) {
            Query query = connection.createQuery("SELECT * FROM files WHERE id = :id");
            return Optional.of(query
                    .addParameter("id", id)
                    .executeAndFetchFirst(File.class));
        }
    }

    @Override
    public Collection<File> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM files");
            return query.executeAndFetch(File.class);
        }
    }
}
