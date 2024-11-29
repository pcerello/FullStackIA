package com.fullstack.ia.fullstackia.Repository;

import com.fullstack.ia.fullstackia.Entity.Personnages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnagesRepository extends JpaRepository<Personnages, Long> {
}
