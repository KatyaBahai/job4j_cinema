package ru.job4j.cinema.dto;

import java.time.LocalDateTime;

public class SessionDto {
    private int sessionId;
    private String film;
    private String hall;
    private LocalDateTime startTime;
    private int durationInMinutes;
    private int price;

    public SessionDto(int sessionId, String film, String hall, LocalDateTime startTime, int durationInMinutes, int price) {
        this.sessionId = sessionId;
        this.film = film;
        this.hall = hall;
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
        this.price = price;
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
}