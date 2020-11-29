package com.vote.resultsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication()
@EnableJpaRepositories
public class ResultsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResultsApiApplication.class, args);
	}

}
