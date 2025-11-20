package com.cinema.starwars.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "film_writers")
public class FilmWriter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "writer_id")
    private Long writerId;

    @Column(name = "writer_first_name", nullable = false)
    private String writerFirstName;

    @Column(name = "writer_last_name", nullable = false)
    private String writerLastName;

    @OneToMany(mappedBy = "filmWriter", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<StarWarsFilm> starWarsFilms = new ArrayList<>();

    public FilmWriter() {}

    public FilmWriter(String writerFirstName, String writerLastName) {
        this.writerFirstName = writerFirstName;
        this.writerLastName = writerLastName;
    }

    public Long getWriterId() {
        return writerId;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
    }

    public String getWriterFirstName() {
        return writerFirstName;
    }

    public void setWriterFirstName(String writerFirstName) {
        this.writerFirstName = writerFirstName;
    }

    public String getWriterLastName() {
        return writerLastName;
    }

    public void setWriterLastName(String writerLastName) {
        this.writerLastName = writerLastName;
    }

    public List<StarWarsFilm> getStarWarsFilms() {
        return starWarsFilms;
    }

    public void setStarWarsFilms(List<StarWarsFilm> starWarsFilms) {
        this.starWarsFilms = starWarsFilms;
    }
}