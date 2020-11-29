package com.vote.resultsapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vote.resultsapi.data.Result;
import com.vote.resultsapi.message.CurrentState;
import com.vote.resultsapi.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

@Service
@EnableScheduling
public class ResultsService {

    private CurrentState lastState;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ResultRepository resultRepository;

    private HashSet<WebSocketSession> sessions = new HashSet<>();

    public void addSession(WebSocketSession socketSession) {
        sessions.add(socketSession);
    }

    @Scheduled(initialDelay = 1000L, fixedDelay = 1000L)
    public void sendResults() throws JsonProcessingException {
        sessions.removeIf(socketSession -> !socketSession.isOpen());
        List<Result> all = resultRepository.findAll();
        all.sort(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
        String s = objectMapper.writeValueAsString(all);
        sessions
            .forEach(
                session -> {
                    try {
                        session.sendMessage(new TextMessage(s));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            );


    }

}
