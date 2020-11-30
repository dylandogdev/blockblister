package com.dylandogdev.blockblister.repository;

import com.dylandogdev.blockblister.entities.MediaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends CrudRepository<MediaEntity, Integer> {
}
