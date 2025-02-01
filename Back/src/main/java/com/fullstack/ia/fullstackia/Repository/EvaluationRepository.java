package com.fullstack.ia.fullstackia.Repository;

import com.fullstack.ia.fullstackia.Entity.EvaluationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends CrudRepository<EvaluationEntity, Long>{
    @Query("SELECT e FROM EvaluationEntity e WHERE e.scenario.id = :scenarioId")
    List<EvaluationEntity> findByScenarioId(@Param("scenarioId") Long scenarioId);
}
