package com.dylandogdev.blockblister.entities;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode
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
    private Integer year;
    @NotNull
    private Integer length;
    @Column(name = "tmdb_id")
    private Integer tmdbId;
    @Column(name = "image_url")
    private String imageUrl;
    @NotNull
    @ManyToOne(targetEntity = PriceEntity.class)
    @JoinColumn(name = "price_id")
    private PriceEntity price;
    @ManyToMany
    @JoinTable(name = "movies_genres",
    joinColumns = @JoinColumn(name = "movie"),
    inverseJoinColumns = @JoinColumn(name = "genre"))
    private List<GenreEntity> genres;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<MediaEntity> media;

    public MovieEntity() {};

    public MovieEntity(String title, String description, String director, Integer year, Integer length, PriceEntity price ) {
        this.title = title;
        this.description = description;
        this.director = director;
        this.year = year;
        this.length = length;
        this.price = price;
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

    public Integer getYear() {
        return year;
    }

    public Integer getLength() {
        return length;
    }

    public Integer getTmdbId() {
        return tmdbId;
    }

    public String getImageUrl() {
        return imageUrl;
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

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setLength(Integer length) {
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

    public void setTmdbId(Integer tmdbId) {
        this.tmdbId = tmdbId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public PriceEntity getPrice() {
        return price;
    }

    public void setPrice(PriceEntity price) {
        this.price = price;
    }
}
