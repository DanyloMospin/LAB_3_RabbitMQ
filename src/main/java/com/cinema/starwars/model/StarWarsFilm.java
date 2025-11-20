package com.cinema.starwars.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "star_wars_films")
public class StarWarsFilm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long filmId;

    @Column(name = "film_title", nullable = false)
    private String filmTitle;

    @Column(name = "film_release_year")
    private Integer filmReleaseYear;

    @ManyToOne
    @JoinColumn(name = "film_writer_id")
    @JsonBackReference
    private FilmWriter filmWriter;

    public StarWarsFilm() {}

    public StarWarsFilm(String filmTitle, Integer filmReleaseYear) {
        this.filmTitle = filmTitle;
        this.filmReleaseYear = filmReleaseYear;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public Integer getFilmReleaseYear() {
        return filmReleaseYear;
    }

    public void setFilmReleaseYear(Integer filmReleaseYear) {
        this.filmReleaseYear = filmReleaseYear;
    }

    public FilmWriter getFilmWriter() {
        return filmWriter;
    }

    public void setFilmWriter(FilmWriter filmWriter) {
        this.filmWriter = filmWriter;
    }
}