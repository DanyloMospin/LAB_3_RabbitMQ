package com.cinema.starwars.controller;

import com.cinema.starwars.model.StarWarsFilm;
import com.cinema.starwars.repository.StarWarsFilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/star-wars-films")
public class StarWarsFilmController {

    @Autowired
    private StarWarsFilmRepository starWarsFilmRepository;

    @GetMapping
    public List<StarWarsFilm> getAllFilms() {
        return starWarsFilmRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StarWarsFilm> getFilmById(@PathVariable Long id) {
        Optional<StarWarsFilm> film = starWarsFilmRepository.findById(id);
        return film.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-writer/{writerId}")
    public List<StarWarsFilm> getFilmsByWriter(@PathVariable Long writerId) {
        return starWarsFilmRepository.findByFilmWriterWriterId(writerId);
    }

    @PostMapping
    public StarWarsFilm createFilm(@RequestBody StarWarsFilm film) {
        return starWarsFilmRepository.save(film);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        if (starWarsFilmRepository.existsById(id)) {
            starWarsFilmRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}