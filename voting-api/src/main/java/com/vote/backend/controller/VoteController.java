package com.vote.backend.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votingApi")
@CrossOrigin("*")
public class VoteController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping(path = "/{vote}")
    public String vote(@PathVariable(name = "vote") String lala) {
        System.out.println(lala);
        rabbitTemplate.convertAndSend("aaa", "bbb", lala);
        return "OK";
    }
}
