package com.dylandogdev.blockblister.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="media")
public class MediaEntity {
    @Id
    @GeneratedValue
    private int id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "movie")
    private MovieEntity movie;
    private Date acquired;
    @ManyToOne(targetEntity = PriceEntity.class)
    @JoinColumn(name="price")
    private PriceEntity price;
    @ManyToOne(targetEntity = ConditionEntity.class)
    @JoinColumn(name="condition")
    private ConditionEntity condition;
}
