package com.dylandogdev.blockblister;


import com.dylandogdev.blockblister.entities.*;
import com.dylandogdev.blockblister.repository.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class MovieRepositoryTests {

    @Autowired
    MovieRepository repository;

    @Autowired
    PriceRepository priceRepository;

    @Test
    void testCreateRetrieveMovie() {
        PriceEntity price = priceRepository.save(new PriceEntity("General", new BigDecimal(2.99)));
        MovieEntity testMovie = repository.save(
                new MovieEntity(
                        "Halloween",
                        "The night HE came back!",
                        "David Gordon Green",
                        2018,
                        100,
                        price
                )
        );
        Assertions.assertNotNull(testMovie);
        Assertions.assertEquals("Halloween", testMovie.getTitle());
        Assertions.assertEquals("The night HE came back!", testMovie.getDescription());
        Assertions.assertEquals("David Gordon Green", testMovie.getDirector());
        Assertions.assertEquals(2018, testMovie.getYear());
        Assertions.assertEquals(100, testMovie.getLength());
    }

    @Test
    void testUpdateMovie() {
        PriceEntity price = priceRepository.save(new PriceEntity("General", new BigDecimal(2.99)));
        MovieEntity testMovie = repository.save(
                new MovieEntity(
                        "Friday the 13th",
                        "Ki ki ki... ma ma ma...",
                        "Sean S. Cunningham",
                        1980,
                        95,
                        price
                )
        );
        Integer testId = testMovie.getId();
        testMovie.setTitle("Friday the 13th Part 2");
        MovieEntity savedMovie = repository.save(testMovie);
        Assertions.assertEquals(testId, savedMovie.getId());
        Assertions.assertEquals("Friday the 13th Part 2", savedMovie.getTitle());
    }

    @Test
    void testDeleteMovie() {
        PriceEntity price = priceRepository.save(new PriceEntity("General", new BigDecimal(2.99)));
        MovieEntity movieToDelete = repository.save(
                new MovieEntity(
                        "Child's Play",
                        "Evil doll runs amok.",
                        "Todd Holland",
                        1988,
                        92,
                        price
                )
        );
        Assertions.assertNotNull(repository.findById(movieToDelete.getId()));
        repository.delete(movieToDelete);
        Assertions.assertTrue(repository.findById(movieToDelete.getId()).isEmpty());
        Assertions.assertFalse(repository.existsById(movieToDelete.getId()));
    }

    @Test
    void testDeleteMovieById(){
        PriceEntity price = priceRepository.save(new PriceEntity("General", new BigDecimal(2.99)));
        MovieEntity movieToDeleteById = repository.save(
                new MovieEntity(
                        "Night of the Living Dead",
                        "They're coming to get you, Barbara!",
                        "George Romero",
                        1968,
                        87,
                        price
                )
        );
        repository.deleteById(movieToDeleteById.getId());
        Assertions.assertFalse(repository.existsById(movieToDeleteById.getId()));
        Assertions.assertTrue(repository.findById(movieToDeleteById.getId()).isEmpty());
    }

    @Test
    void testCreateRetrieveDeleteListOfMovies() {
        PriceEntity price = priceRepository.save(new PriceEntity("General", new BigDecimal(2.99)));
        List<Integer> movieIds = (List<Integer>) new ArrayList<Integer>();
        List<MovieEntity> movies = (List<MovieEntity>) repository.saveAll(
                new ArrayList<MovieEntity>(){
                    {
                        add(
                                new MovieEntity(
                                        "A Nightmare on Elm Street",
                                        "One, two, Freddy's coming for you!",
                                        "Wes Craven",
                                        1984,
                                        90,
                                        price
                                )
                        );
                        add(
                                new MovieEntity(
                                        "A Nightmare on Elm Street 2: Freddy's Revenge",
                                        "He's back and Freddier than ever!",
                                        "Jack Sholder",
                                        1985,
                                        87,
                                        price
                                )
                        );
                    }
                }
        );

        movies.stream().forEach(m -> movieIds.add(m.getId()));

        Assertions.assertEquals(2, movies.size());
        Assertions.assertTrue(
                movies
                        .stream()
                        .filter(
                                x -> x.getTitle()
                                        .equalsIgnoreCase(
                                                "A Nightmare on Elm Street"
                                        )
                        )
                        .findFirst()
                        .isPresent()
        );
        repository.deleteAll(movies);
        List<MovieEntity> retrievedMovies = (List<MovieEntity>) repository.findAllById(movieIds);
        Assertions.assertTrue(retrievedMovies.isEmpty());
    }
}

