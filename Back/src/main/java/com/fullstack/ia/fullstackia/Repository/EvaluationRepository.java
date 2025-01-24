package com.fullstack.ia.fullstackia.Repository;

import com.fullstack.ia.fullstackia.Entity.EvaluationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends CrudRepository<EvaluationEntity, Long>{
    EvaluationEntity findByScenarioId(Long scenarioId);
}
