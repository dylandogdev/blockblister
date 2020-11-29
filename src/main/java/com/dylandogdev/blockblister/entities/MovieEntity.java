package com.dylandogdev.blockblister.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
@Table(name="movies")
public class MovieEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private String title;
    @NotNull
    private String  description;
    @NotNull
    private  String director;
    @NotNull
    private int year;
    @NotNull
    private int length;
    @ManyToMany
    @JoinTable(name = "movies_genres",
    joinColumns = @JoinColumn(name = "movie"),
    inverseJoinColumns = @JoinColumn(name = "genre"))
    private List<GenreEntity> genres;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<MediaEntity> media;

    public MovieEntity(String title, String description, String director, int year, int length ) {
        this.title = title;
        this.description = description;
        this.director = director;
        this.year = year;
        this.length = length;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    public int getLength() {
        return length;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public List<MediaEntity> getMedia() {
        return media;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setGenres(List<GenreEntity> genres) {
        this.genres = genres;
    }

    public void setMedia(List<MediaEntity> media) {
        this.media = media;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
