package com.fullstack.ia.fullstackia.Repository;

import com.fullstack.ia.fullstackia.Entity.EvaluationEntity;
import com.fullstack.ia.fullstackia.Entity.TemoignageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends CrudRepository<EvaluationEntity, Long>{
    //EvaluationEntity findByScenarioId(Long scenarioId);
    @Query("SELECT e FROM EvaluationEntity e WHERE e.scenario.id = :scenarioId")
    List<EvaluationEntity> findByScenarioId(@Param("scenarioId") Long scenarioId);
}
