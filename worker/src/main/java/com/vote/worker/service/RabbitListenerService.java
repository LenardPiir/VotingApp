package com.vote.worker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitListenerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(RabbitListenerService.class);

    @RabbitListener(queues = {"vote-queue"})
    public void listen(String in) {
        log.info(in);
        jdbcTemplate.execute("UPDATE voting.result SET number = number::int + 1 WHERE animal='" + in + "';");
    }
}
