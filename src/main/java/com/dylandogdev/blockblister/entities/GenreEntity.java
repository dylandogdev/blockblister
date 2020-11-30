package com.dylandogdev.blockblister.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "genres")
public class GenreEntity {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String genre;

    public GenreEntity() {};

    public GenreEntity(String genre) {
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
