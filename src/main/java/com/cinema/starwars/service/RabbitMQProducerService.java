package com.cinema.starwars.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cinema.starwars.config.RabbitMQConfig;
import com.cinema.starwars.model.FilmEvent;

@Service
public class RabbitMQProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendFilmCreatedEvent(Long filmId, String filmTitle, String writerName) {
        FilmEvent event = new FilmEvent("FILM_CREATED", filmId, filmTitle, writerName);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.FILM_EXCHANGE,
                RabbitMQConfig.FILM_ROUTING_KEY,
                event
        );
        System.out.println("  Відправлено повідомлення через Exchange: " + filmTitle);
    }

    public void sendFilmDeletedEvent(Long filmId, String filmTitle) {
        FilmEvent event = new FilmEvent("FILM_DELETED", filmId, filmTitle, "Unknown");
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.FILM_EXCHANGE,
                RabbitMQConfig.FILM_ROUTING_KEY,
                event
        );
        System.out.println("  Відправлено повідомлення про видалення через Exchange: " + filmTitle);
    }
}