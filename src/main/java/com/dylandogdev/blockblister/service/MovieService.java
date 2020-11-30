package com.dylandogdev.blockblister.service;

import com.dylandogdev.blockblister.entities.GenreEntity;
import com.dylandogdev.blockblister.entities.MediaEntity;
import com.dylandogdev.blockblister.entities.MovieEntity;
import com.dylandogdev.blockblister.repository.GenreRepository;
import com.dylandogdev.blockblister.repository.MediaRepository;
import com.dylandogdev.blockblister.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    MediaRepository mediaRepository;

    public List<MovieEntity> getAllMovies(Integer page, Integer limit, String sort) {
        Pageable paging = PageRequest.of(page, limit, Sort.by(sort));
        Page<MovieEntity> pagedResult = movieRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        }
        return new ArrayList<MovieEntity>();
    }

    public MovieEntity createNewMovie(
            String title,
            String description,
            String director,
            int year,
            int length,
            Optional<List<Integer>> genreIds
    ){
        MovieEntity movie = new MovieEntity(title, description, director, year, length);
        if(genreIds.isPresent()) {
            movie.setGenres((List<GenreEntity>) genreRepository.findAllById(genreIds.get()));
        }
        return movieRepository.save(movie);
    }

    public Optional<MovieEntity> getMovieById(Integer id) {
        return movieRepository.findById(id);
    }
}
