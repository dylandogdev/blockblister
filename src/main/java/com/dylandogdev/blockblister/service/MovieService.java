package com.dylandogdev.blockblister.service;

import com.dylandogdev.blockblister.entities.GenreEntity;
import com.dylandogdev.blockblister.entities.MediaEntity;
import com.dylandogdev.blockblister.entities.MovieEntity;
import com.dylandogdev.blockblister.entities.PriceEntity;
import com.dylandogdev.blockblister.repository.GenreRepository;
import com.dylandogdev.blockblister.repository.MediaRepository;
import com.dylandogdev.blockblister.repository.MovieRepository;
import com.dylandogdev.blockblister.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

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

    @Autowired
    PriceRepository priceRepository;

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
            Integer year,
            Integer length,
            Integer priceId,
            Optional<List<Integer>> genreIds
    ){
        PriceEntity price = priceRepository
                .findById(priceId)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Invalid price id provided."
                        )
                );
        MovieEntity movie = new MovieEntity(title, description, director, year, length, price);
        if(genreIds.isPresent()) {
            movie.setGenres((List<GenreEntity>) genreRepository.findAllById(genreIds.get()));
        }
        return movieRepository.save(movie);
    }

    public Optional<MovieEntity> getMovieById(Integer id) {
        return movieRepository.findById(id);
    }

    public MovieEntity updateMovie(
            Integer id,
            String operation,
            Optional<String> title,
            Optional<String> description,
            Optional<String> director,
            Optional<Integer> year,
            Optional<Integer> length,
            Optional<Integer> priceId,
            Optional<List<Integer>> genreIds,
            Optional<Integer> tmdbId,
            Optional<String> imageUrl
    ) {
        MovieEntity movie = movieRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "No movie matches the id provided."
                        )
                );
        if(title.isPresent()){
            movie.setTitle(title.get());
        }
        if(description.isPresent()){
            movie.setDescription(description.get());
        }
        if(director.isPresent()){
            movie.setDirector(director.get());
        }
        if(year.isPresent()){
            movie.setYear(year.get());
        }
        if(length.isPresent()){
            movie.setLength(length.get());
        }
        if(priceId.isPresent()){
            PriceEntity newPrice = priceRepository
                    .findById(priceId.get())
                    .orElseThrow(
                            () -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND,
                                    "Invalid price id provided."
                            )
                    );
            movie.setPrice(newPrice);
        }
        if(genreIds.isPresent()){
            if(operation.equalsIgnoreCase("DELETE")) {
                movie.setGenres(null);
            } else {
                List<GenreEntity> newGenres = (List<GenreEntity>) genreRepository.findAllById(genreIds.get());
                movie.setGenres(newGenres);
            }
        }
        if(tmdbId.isPresent()) {
            if(operation.equalsIgnoreCase("DELETE")) {
                movie.setTmdbId(null);
            } else {
                movie.setTmdbId(tmdbId.get());
            }
        }
        if(imageUrl.isPresent()) {
            if(operation.equalsIgnoreCase("DELETE")){
                movie.setImageUrl(null);
            } else {
                movie.setImageUrl(imageUrl.get());
            }
        }
        movieRepository.save(movie);
        return movie;
    }
}
