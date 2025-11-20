package com.cinema.starwars.controller;

import com.cinema.starwars.model.FilmWriter;
import com.cinema.starwars.repository.FilmWriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/film-writers")
public class FilmWriterController {

    @Autowired
    private FilmWriterRepository filmWriterRepository;

    @GetMapping
    public List<FilmWriter> getAllFilmWriters() {
        return filmWriterRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmWriter> getFilmWriterById(@PathVariable Long id) {
        Optional<FilmWriter> filmWriter = filmWriterRepository.findById(id);
        return filmWriter.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FilmWriter createFilmWriter(@RequestBody FilmWriter filmWriter) {
        return filmWriterRepository.save(filmWriter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmWriter> updateFilmWriter(@PathVariable Long id,
                                                       @RequestBody FilmWriter filmWriterDetails) {
        Optional<FilmWriter> optionalFilmWriter = filmWriterRepository.findById(id);

        if (optionalFilmWriter.isPresent()) {
            FilmWriter filmWriter = optionalFilmWriter.get();
            filmWriter.setWriterFirstName(filmWriterDetails.getWriterFirstName());
            filmWriter.setWriterLastName(filmWriterDetails.getWriterLastName());
            return ResponseEntity.ok(filmWriterRepository.save(filmWriter));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilmWriter(@PathVariable Long id) {
        if (filmWriterRepository.existsById(id)) {
            filmWriterRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}