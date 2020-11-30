package com.dylandogdev.blockblister.repository;

import com.dylandogdev.blockblister.entities.GenreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<GenreEntity, Integer> {
}
