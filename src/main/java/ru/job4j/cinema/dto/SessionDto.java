package ru.job4j.cinema.dto;

import java.time.LocalDateTime;

public class SessionDto {
    private int sessionId;
    private String film;
    private String hall;
    private LocalDateTime startTime;
    private int durationInMinutes;
    private int price;
    private int rowCount;
    private int placeCount;

    private SessionDto(SessionDtoBuilder builder) {
        this.sessionId = builder.sessionId;
        this.film = builder.film;
        this.hall = builder.hall;
        this.startTime = builder.startTime;
        this.durationInMinutes = builder.durationInMinutes;
        this.price = builder.price;
        this.rowCount = builder.rowCount;
        this.placeCount = builder.placeCount;
    }

    public SessionDto() {
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(int placeCount) {
        this.placeCount = placeCount;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int id) {
        this.sessionId = sessionId;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static class SessionDtoBuilder {
        private int sessionId;
        private String film;
        private String hall;
        private LocalDateTime startTime;
        private int durationInMinutes;
        private int price;
        private int rowCount;
        private int placeCount;

        public SessionDtoBuilder setSessionId(int sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public SessionDtoBuilder setFilm(String film) {
            this.film = film;
            return this;
        }

        public SessionDtoBuilder setHall(String hall) {
            this.hall = hall;
            return this;
        }

        public SessionDtoBuilder setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public SessionDtoBuilder setDurationInMinutes(int durationInMinutes) {
            this.durationInMinutes = durationInMinutes;
            return this;
        }

        public SessionDtoBuilder setPrice(int price) {
            this.price = price;
            return this;
        }

        public SessionDtoBuilder setRowCount(int rowCount) {
            this.rowCount = rowCount;
            return this;
        }

        public SessionDtoBuilder setPlaceCount(int placeCount) {
            this.placeCount = placeCount;
            return this;
        }

        public SessionDto build() {
            return new SessionDto(this);
        }
    }
}
