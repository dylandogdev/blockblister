package com.dylandogdev.blockblister.controllers;

import com.dylandogdev.blockblister.entities.MovieEntity;
import com.dylandogdev.blockblister.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService service;

    @GetMapping
    public ResponseEntity<List<MovieEntity>> getAllMovies(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer limit,
        @RequestParam(defaultValue = "id") String sort
    ) {
        List<MovieEntity> movies = service.getAllMovies(page, limit, sort);

        return new ResponseEntity<List<MovieEntity>>(movies, new HttpHeaders(), HttpStatus.OK);
    }
}
