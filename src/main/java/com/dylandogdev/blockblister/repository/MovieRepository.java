package com.dylandogdev.blockblister.repository;

import com.dylandogdev.blockblister.entities.MovieEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<MovieEntity, Integer> {
}
