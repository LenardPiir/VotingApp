package com.vote.backend.service;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Profile({"docker"})
public class MigrationService {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @PostConstruct()
    public void migrations() throws InterruptedException {
        Flyway flyway =
                Flyway.configure()
                        .dataSource( dbUrl , "voting" , "voting" )
                        .defaultSchema("voting")
                        .schemas("voting")
                        .locations("db/migration")
                        .load();

        try {
            flyway.migrate();
        }
        catch (Exception e) {
            Thread.sleep(5000L);
            migrations();
        }

    }
}
