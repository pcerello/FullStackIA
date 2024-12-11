package com.fullstack.ia.fullstackia.Repository;

import com.fullstack.ia.fullstackia.Entity.PersonnageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnageRepository extends JpaRepository<PersonnageEntity, Long> {
}
