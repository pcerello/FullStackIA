package com.fullstack.ia.fullstackia.Repository;

import com.fullstack.ia.fullstackia.Entity.PreuveEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreuveRepo extends CrudRepository<PreuveEntity, Long> {
}
