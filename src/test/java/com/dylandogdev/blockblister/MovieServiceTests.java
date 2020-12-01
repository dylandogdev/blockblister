package com.dylandogdev.blockblister;

import com.dylandogdev.blockblister.entities.GenreEntity;
import com.dylandogdev.blockblister.entities.MovieEntity;
import com.dylandogdev.blockblister.entities.PriceEntity;
import com.dylandogdev.blockblister.repository.GenreRepository;
import com.dylandogdev.blockblister.repository.PriceRepository;
import com.dylandogdev.blockblister.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MovieServiceTests {

    @Autowired
    MovieService service;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    PriceRepository priceRepository;

    @Test
    void testCreateMovieNoGenres() {
        PriceEntity price = priceRepository.save(new PriceEntity("General", new BigDecimal(2.99)));
        MovieEntity movie = service.createNewMovie(
              "Dracula",
              "He vants to suck your blood!",
              "Tod Browning",
                1931,
                85,
                price.getId(),
                Optional.empty()
        );
        Assertions.assertNotNull(movie);
        Assertions.assertEquals("Dracula", movie.getTitle());
    }

    @Test
    void testCreateNewMovieWithGenres() {
        PriceEntity price = priceRepository.save(new PriceEntity("General", new BigDecimal(2.99)));
        ArrayList<Integer> genreIds = new ArrayList<>();
        List<GenreEntity> genres = (List<GenreEntity>) genreRepository.saveAll(
                new ArrayList<GenreEntity>() {
                    {
                        add(new GenreEntity("Slasher"));
                        add(new GenreEntity("Supernatural"));
                    }
                }
        );
        genres.stream().forEach(g -> genreIds.add(g.getId()));

        MovieEntity movie = service.createNewMovie(
            "The Fly",
                "Be afraid. Be VERY afraid.",
                "David Cronenberg",
                1986,
                96,
                price.getId(),
                Optional.of(genreIds)
        );

        Assertions.assertNotNull(movie);
        Assertions.assertEquals("The Fly", movie.getTitle());
        Assertions.assertEquals(2, movie.getGenres().size());
        Assertions.assertTrue(
                movie
                        .getGenres()
                        .stream()
                .filter(
                        g -> g.getGenre()
                        .equalsIgnoreCase("Slasher")
                )
                .findFirst()
                .isPresent()
        );
        Assertions.assertTrue(
                movie
                        .getGenres()
                        .stream()
                        .filter(
                                g -> g.getGenre()
                                        .equalsIgnoreCase("Supernatural")
                        )
                        .findFirst()
                        .isPresent()
        );
    }
}
