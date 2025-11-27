package com.cinema.starwars.controller;

import com.cinema.starwars.model.FilmWriter;
import com.cinema.starwars.model.StarWarsFilm;
import com.cinema.starwars.repository.FilmWriterRepository;
import com.cinema.starwars.repository.StarWarsFilmRepository;
import com.cinema.starwars.service.RabbitMQProducerService;
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

    @Autowired
    private FilmWriterRepository filmWriterRepository; // Додаємо репозиторій сценаристів

    @Autowired
    private RabbitMQProducerService rabbitMQProducerService;

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
        StarWarsFilm savedFilm = starWarsFilmRepository.save(film);

        String writerName = "Невідомий сценарист";
        if (film.getFilmWriter() != null && film.getFilmWriter().getWriterId() != null) {
            // Отримуємо повну інформацію про сценариста з бази
            Optional<FilmWriter> writerOpt = filmWriterRepository.findById(film.getFilmWriter().getWriterId());
            if (writerOpt.isPresent()) {
                FilmWriter writer = writerOpt.get();
                writerName = writer.getWriterFirstName() + " " + writer.getWriterLastName();
            }
        }

        rabbitMQProducerService.sendFilmCreatedEvent(
                savedFilm.getFilmId(),
                savedFilm.getFilmTitle(),
                writerName
        );

        return savedFilm;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        if (starWarsFilmRepository.existsById(id)) {
            Optional<StarWarsFilm> filmOpt = starWarsFilmRepository.findById(id);

            filmOpt.ifPresent(film -> {
                String writerName = "Невідомий сценарист";
                if (film.getFilmWriter() != null) {
                    writerName = film.getFilmWriter().getWriterFirstName() + " " +
                            film.getFilmWriter().getWriterLastName();
                }

                rabbitMQProducerService.sendFilmDeletedEvent(
                        film.getFilmId(),
                        film.getFilmTitle()
                );
            });

            starWarsFilmRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}