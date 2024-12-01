package com.fullstack.ia.fullstackia.Repository;

import com.fullstack.ia.fullstackia.Entity.PersonnagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnagesRepository extends JpaRepository<PersonnagesEntity, Long> {
}
