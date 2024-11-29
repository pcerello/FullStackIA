package com.fullstack.ia.fullstackia.Repository;

import com.fullstack.ia.fullstackia.Entity.TemoiniageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemoniageRepo extends CrudRepository<TemoiniageEntity, Long> {
}
