package com.fullstack.ia.fullstackia.Repository;

import com.fullstack.ia.fullstackia.Entity.TemoignageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemoignageRepo extends CrudRepository<TemoignageEntity, Long> {
}
