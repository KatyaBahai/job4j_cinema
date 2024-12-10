package ru.job4j.cinema.repositories;

import ru.job4j.cinema.model.File;

import java.util.Collection;
import java.util.Optional;

public interface FileRepository {
    Optional<File> findById(int id);

    Collection<File> findAll();
}
