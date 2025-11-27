package com.cinema.starwars.service;

import com.cinema.starwars.config.RabbitMQConfig;
import com.cinema.starwars.model.FilmEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RabbitMQConsumerService {

    private Map<String, Integer> writerFilmCount = new HashMap<>();

    //@RabbitListener(queues = RabbitMQConfig.FILM_QUEUE)
    public void receiveFilmEvent(FilmEvent filmEvent) {
        System.out.println("   ОТРИМАНО ПОВІДОМЛЕННЯ З ЧЕРГИ:");
        System.out.println("   Тип події: " + filmEvent.getEventType());
        System.out.println("   ID фільму: " + filmEvent.getFilmId());
        System.out.println("   Назва: " + filmEvent.getFilmTitle());
        System.out.println("   Сценарист: " + filmEvent.getWriterName());
        System.out.println("   Час: " + filmEvent.getTimestamp());

        processFilmEvent(filmEvent);

        System.out.println("---");
    }

    private void processFilmEvent(FilmEvent filmEvent) {
        String writerName = filmEvent.getWriterName();

        if ("FILM_CREATED".equals(filmEvent.getEventType())) {
            writerFilmCount.put(writerName, writerFilmCount.getOrDefault(writerName, 0) + 1);
            System.out.println("    Фільмів у " + writerName + ": " + writerFilmCount.get(writerName));

        } else if ("FILM_DELETED".equals(filmEvent.getEventType())) {
            if (writerFilmCount.containsKey(writerName)) {
                int count = writerFilmCount.get(writerName) - 1;
                if (count > 0) {
                    writerFilmCount.put(writerName, count);
                } else {
                    writerFilmCount.remove(writerName);
                }
                System.out.println("   Фільмів у " + writerName + ": " + count);
            }
        }

        System.out.println("   Всього сценаристів в системі: " + writerFilmCount.size());
    }
}