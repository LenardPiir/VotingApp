package com.vote.resultsapi.data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Result {

    @Id
    private Long id;

    private String animal;

    private Integer number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
