package com.fullstack.ia.fullstackia.Repository;

import com.fullstack.ia.fullstackia.Entity.ScenarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioRepository extends CrudRepository<ScenarioEntity, Long> {
}
