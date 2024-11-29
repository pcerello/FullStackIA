package com.fullstack.ia.fullstackia.Repository;

import com.fullstack.ia.fullstackia.Entity.MurderStoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MurderStoryRepository extends CrudRepository<MurderStoryEntity, Long> {
}
