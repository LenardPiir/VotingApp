package com.vote.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue
    private Long id;

    private Integer cat;
    private Integer dog;
}
