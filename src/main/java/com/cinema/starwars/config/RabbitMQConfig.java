package com.cinema.starwars.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FILM_QUEUE = "film_queue";
    public static final String FILM_EXCHANGE = "film_exchange";
    public static final String FILM_ROUTING_KEY = "film.routing.key";

    @Bean
    public DirectExchange filmExchange() {
        return new DirectExchange(FILM_EXCHANGE);
    }

    @Bean
    public Queue filmQueue() {
        return new Queue(FILM_QUEUE, true);
    }

    @Bean
    public Binding filmBinding(Queue filmQueue, DirectExchange filmExchange) {
        return BindingBuilder.bind(filmQueue)
                .to(filmExchange)
                .with(FILM_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        factory.setDefaultRequeueRejected(false);
        return factory;
    }
}