package com.dylandogdev.blockblister.repository;

import com.dylandogdev.blockblister.entities.PriceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends CrudRepository<PriceEntity, Integer> {
}
