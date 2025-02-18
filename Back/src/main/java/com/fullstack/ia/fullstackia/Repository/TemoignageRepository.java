package com.fullstack.ia.fullstackia.Repository;

import com.fullstack.ia.fullstackia.Entity.TemoignageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemoignageRepository extends CrudRepository<TemoignageEntity, Long> {
    @Query("SELECT t FROM TemoignageEntity t WHERE t.scenario.id = :scenarioId")
    List<TemoignageEntity> findByScenarioId(@Param("scenarioId") Long scenarioId);
}
