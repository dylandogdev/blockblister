package com.dylandogdev.blockblister.repository;

import com.dylandogdev.blockblister.entities.ConditionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends CrudRepository<ConditionEntity, Integer> {
}
