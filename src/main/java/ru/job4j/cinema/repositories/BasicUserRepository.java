package ru.job4j.cinema.repositories;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Optional;

@Repository
@Slf4j
public class BasicUserRepository implements UserRepository {
    Sql2o sql2o;

    public BasicUserRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<User> save(User user) {
        try (Connection connection = sql2o.open()) {
            String sql = """
                      INSERT INTO users(full_name, email, password)
                      VALUES (:fullName, :email, :password)
                      """;
            Query query = connection.createQuery(sql, true)
                    .addParameter("fullName", user.getFullName())
                    .addParameter("email", user.getEmail())
                    .addParameter("password", user.getPassword());
            int serialID = query.executeUpdate().getKey(Integer.class);
            user.setId(serialID);
            return Optional.of(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (Connection connection = sql2o.open()) {
            String sql = "SELECT * FROM users WHERE email = :email AND password = :password";
            Query query = connection.createQuery(sql)
                    .addParameter("email", email)
                    .addParameter("password", password);
            User user = query.setColumnMappings(User.COLUMN_MAPPING).executeAndFetchFirst(User.class);
            return Optional.ofNullable(user);
        }

    }

    public boolean deleteById(int id) {
        try (Connection connection = sql2o.open()) {
            String sql = "DELETE FROM users WHERE id = :id";
            Query query = connection.createQuery(sql)
                    .addParameter("id", id);
            int affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

    public Collection<User> findAll() {
        try (Connection connection = sql2o.open()) {
            String sql = "SELECT * FROM users";
            Query query = connection.createQuery(sql);
            return query.setColumnMappings(User.COLUMN_MAPPING).executeAndFetch(User.class);
        }
    }

}
