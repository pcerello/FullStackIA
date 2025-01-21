package com.fullstack.ia.fullstackia.Repository;

import com.fullstack.ia.fullstackia.Entity.ScenarioPriveEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioPriveRepository extends CrudRepository<ScenarioPriveEntity, Long> {
}
