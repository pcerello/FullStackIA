package com.fullstack.ia.fullstackia.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.ia.fullstackia.DTO.TemoignageDTO;
import com.fullstack.ia.fullstackia.Entity.TemoignageEntity;
import com.fullstack.ia.fullstackia.Repository.ScenarioRepository;
import com.fullstack.ia.fullstackia.Repository.TemoignageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TemoignageService {

    private final TemoignageRepository temoignageRepository;
    private final ObjectMapper objectMapper;
    private final ScenarioRepository scenarioRepository;

    public void saveGeneratedTemoignages(String description, Long scenarioId){
        TemoignageEntity temoignageEntity =  TemoignageEntity.builder()
                .description(description)
                .scenario(scenarioRepository.findById(scenarioId).orElseThrow(() -> new IllegalArgumentException("Scenario non trouvé pour l'ID : " + scenarioId)))
                .build();
        temoignageRepository.save(temoignageEntity);
    }

    public List<TemoignageEntity> getTemoignagesByScenarioId(Long scenarioId) {
        return temoignageRepository.findByScenarioId(scenarioId);
    }

}