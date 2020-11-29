package com.vote.backend.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Queue appQueueGeneric() {
        return new Queue("vote-queue");
    }

    @Value("${rabbitmq.url:localhost}")
    private String rabbitmqUrl;

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(rabbitmqUrl);
    }
}
