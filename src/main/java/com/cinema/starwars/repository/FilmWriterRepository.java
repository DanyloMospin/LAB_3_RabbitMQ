package com.cinema.starwars.repository;

import com.cinema.starwars.model.FilmWriter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmWriterRepository extends JpaRepository<FilmWriter, Long> {
}