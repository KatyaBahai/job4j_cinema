package ru.job4j.cinema.model;

import java.util.Map;
import java.util.Objects;

public class Film {
    private int id;
    private String name;
    private String description;
    private int year;
    private int genreId;
    private int minimalAge;
    private int durationInMinutes;
    private int fileId;
    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "name", "name",
            "description", "description",
            "year", "year",
            "genre_id", "genreId",
            "minimal_age", "minimalAge",
            "duration_in_minutes", "durationInMinutes",
            "file_id", "fileId"
    );

    public Film() {
    }

    private Film(FilmBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.year = builder.year;
        this.genreId = builder.genreId;
        this.minimalAge = builder.minimalAge;
        this.durationInMinutes = builder.durationInMinutes;
        this.fileId = builder.fileId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getMinimalAge() {
        return minimalAge;
    }

    public void setMinimalAge(int minimalAge) {
        this.minimalAge = minimalAge;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Film film = (Film) o;
        return id == film.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class FilmBuilder {
        private int id;
        private String name;
        private String description;
        private int year;
        private int genreId;
        private int minimalAge;
        private int durationInMinutes;
        private int fileId;

        public FilmBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public FilmBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public FilmBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public FilmBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        public FilmBuilder setGenreId(int genreId) {
            this.genreId = genreId;
            return this;
        }

        public FilmBuilder setMinimalAge(int minimalAge) {
            this.minimalAge = minimalAge;
            return this;
        }

        public FilmBuilder setDurationInMinutes(int durationInMinutes) {
            this.durationInMinutes = durationInMinutes;
            return this;
        }

        public FilmBuilder setFileId(int fileId) {
            this.fileId = fileId;
            return this;
        }

        public Film build() {
            return new Film(this);
        }
    }
}
