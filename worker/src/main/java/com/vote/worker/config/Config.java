package com.vote.worker.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange("aaa");
    }

    @Bean
    public Binding declareBindingGeneric() {
        return BindingBuilder.bind(appQueueGeneric()).to(appExchange()).with("bbb");
    }

    @Value("${rabbitmq.url:localhost}")
    private String rabbitmqUrl;

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(rabbitmqUrl);
    }

}
