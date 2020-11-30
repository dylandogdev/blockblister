package com.dylandogdev.blockblister.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="media")
public class MediaEntity {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "movie")
    private MovieEntity movie;
    @NotNull
    @ManyToOne(targetEntity = PriceEntity.class)
    @JoinColumn(name="price")
    private PriceEntity price;
    @NotNull
    @ManyToOne(targetEntity = ConditionEntity.class)
    @JoinColumn(name="condition")
    private ConditionEntity condition;

    public MediaEntity() {};

    public MediaEntity(MovieEntity movie, PriceEntity price, ConditionEntity condition) {
        this.movie = movie;
        this.price = price;
        this.condition = condition;
    }

    public int getId() {
        return id;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public PriceEntity getPrice() {
        return price;
    }

    public ConditionEntity getCondition() {
        return condition;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public void setPrice(PriceEntity price) {
        this.price = price;
    }

    public void setCondition(ConditionEntity condition) {
        this.condition = condition;
    }
}
