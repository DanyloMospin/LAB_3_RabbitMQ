package com.cinema.starwars.repository;

import com.cinema.starwars.model.StarWarsFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StarWarsFilmRepository extends JpaRepository<StarWarsFilm, Long> {
    List<StarWarsFilm> findByFilmWriterWriterId(Long writerId);
}