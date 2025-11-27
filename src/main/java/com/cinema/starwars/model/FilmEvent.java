package com.cinema.starwars.model;

import java.time.LocalDateTime;

public class FilmEvent {
    private String eventType;
    private Long filmId;
    private String filmTitle;
    private String writerName;
    private LocalDateTime timestamp;

    public FilmEvent() {}

    public FilmEvent(String eventType, Long filmId, String filmTitle, String writerName) {
        this.eventType = eventType;
        this.filmId = filmId;
        this.filmTitle = filmTitle;
        this.writerName = writerName;
        this.timestamp = LocalDateTime.now();
    }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public Long getFilmId() { return filmId; }
    public void setFilmId(Long filmId) { this.filmId = filmId; }

    public String getFilmTitle() { return filmTitle; }
    public void setFilmTitle(String filmTitle) { this.filmTitle = filmTitle; }

    public String getWriterName() { return writerName; }
    public void setWriterName(String writerName) { this.writerName = writerName; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}