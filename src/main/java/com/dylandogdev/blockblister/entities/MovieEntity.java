package com.dylandogdev.blockblister.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="movies")
@Data
public class MovieEntity {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String  description;
    private  String director;
    private String year;
    private int length;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<MediaEntity> media;
}
