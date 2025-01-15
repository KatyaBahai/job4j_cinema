package ru.job4j.cinema.dto;

public class FilmDto {
    private int id;
    private String name;
    private String description;
    private int year;
    private int minimalAge;
    private int durationInMinutes;
    private int fileId;
    private String genre;

    private FilmDto(FilmDtoBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.year = builder.year;
        this.minimalAge = builder.minimalAge;
        this.durationInMinutes = builder.durationInMinutes;
        this.fileId = builder.fileId;
        this.genre = builder.genre;
    }

    public FilmDto() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public static class FilmDtoBuilder {
        private int id;
        private String name;
        private String description;
        private int year;
        private int minimalAge;
        private int durationInMinutes;
        private int fileId;
        private String genre;

        public FilmDtoBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public FilmDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public FilmDtoBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public FilmDtoBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        public FilmDtoBuilder setMinimalAge(int minimalAge) {
            this.minimalAge = minimalAge;
            return this;
        }

        public FilmDtoBuilder setDurationInMinutes(int durationInMinutes) {
            this.durationInMinutes = durationInMinutes;
            return this;
        }

        public FilmDtoBuilder setFileId(int fileId) {
            this.fileId = fileId;
            return this;
        }

        public FilmDtoBuilder setGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public FilmDto build() {
            return new FilmDto(this);
        }
    }
}
