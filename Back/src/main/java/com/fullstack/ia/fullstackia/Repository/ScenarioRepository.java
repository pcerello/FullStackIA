package com.fullstack.ia.fullstackia.Repository;

import com.fullstack.ia.fullstackia.Entity.ScenarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScenarioRepository extends CrudRepository<ScenarioEntity, Long> {
    Optional<ScenarioEntity> findTopByOrderByIdDesc();

    List<ScenarioEntity> findTop5ByOrderByIdDesc();
}
