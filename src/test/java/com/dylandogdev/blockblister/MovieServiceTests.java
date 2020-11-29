package com.dylandogdev.blockblister;


import com.dylandogdev.blockblister.entities.MovieEntity;
import com.dylandogdev.blockblister.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.Console;
import java.util.List;

@DataJpaTest
public class MovieServiceTests {

    @Autowired
    MovieRepository repository;

    @Test
    void testCreateRetrieveMovie() {
        MovieEntity testMovie = repository.save(
                new MovieEntity(
                        "Halloween",
                        "The night HE came back!",
                        "David Gordon Green",
                        2018,
                        100
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
        MovieEntity testMovie = repository.save(
                new MovieEntity(
                        "Friday the 13th",
                        "Ki ki ki... ma ma ma...",
                        "Sean S. Cunningham",
                        1980,
                        95
                )
        );
        Integer testId = testMovie.getId();
        testMovie.setTitle("Friday the 13th Part 2");
        MovieEntity savedMovie = repository.save(testMovie);
        Assertions.assertEquals(testId, savedMovie.getId());
        Assertions.assertEquals("Friday the 13th Part 2", savedMovie.getTitle());
    }

//    void testDeleteMovie() {}
}
