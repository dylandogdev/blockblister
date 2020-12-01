package com.dylandogdev.blockblister.controllers;

import com.dylandogdev.blockblister.entities.MovieEntity;
import com.dylandogdev.blockblister.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService service;

    @GetMapping(path = "/{id}")
    public ResponseEntity<MovieEntity> getMovieById(
            @PathVariable Integer id
    ) {
        Optional<MovieEntity> movie = service.getMovieById(id);
        if(movie.isPresent()) {
            return new ResponseEntity<MovieEntity>(movie.get(), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<MovieEntity>> getAllMovies(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer limit,
        @RequestParam(defaultValue = "id") String sort
    ) {
        List<MovieEntity> movies = service.getAllMovies(page, limit, sort);

        return new ResponseEntity<List<MovieEntity>>(movies, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MovieEntity> createMovie(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String director,
            @RequestParam Integer year,
            @RequestParam Integer length,
            @RequestParam Integer priceId,
            @RequestParam Optional<List<Integer>> genreIds
    ) {
        return new ResponseEntity<MovieEntity>(
            service.createNewMovie(
                    title,
                    description,
                    director,
                    year,
                    length,
                    priceId,
                    genreIds
            ),
            new HttpHeaders(),
            HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieEntity> updateMovie(
            @PathVariable Integer id,
            @RequestParam String operation,
            @RequestParam Optional<String> title,
            @RequestParam Optional<String> description,
            @RequestParam Optional<String> director,
            @RequestParam Optional<Integer> year,
            @RequestParam Optional<Integer> length,
            @RequestParam Optional<Integer> priceId,
            @RequestParam Optional<List<Integer>> genreIds,
            @RequestParam Optional<Integer> tmdbId,
            @RequestParam Optional<String> imageUrl
    ) {
        MovieEntity updatedMovie = service.updateMovie(
                id,
                operation,
                title,
                description,
                director,
                year,
                length,
                priceId,
                genreIds,
                tmdbId,
                imageUrl
        );

        return new ResponseEntity<MovieEntity>(updatedMovie, new HttpHeaders(), HttpStatus.OK);
    }
}
