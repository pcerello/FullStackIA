package com.fullstack.ia.fullstackia.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.ia.fullstackia.DTO.TemoignageDTO;
import com.fullstack.ia.fullstackia.Entity.PersonnageEntity;
import com.fullstack.ia.fullstackia.Entity.TemoignageEntity;
import com.fullstack.ia.fullstackia.Repository.PersonnageRepository;
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
    private final PersonnageRepository temoinRepository;
    private final ScenarioRepository scenarioRepository;

    public TemoignageEntity creerTemoignageDepuisJson(String filePath) throws IOException {
        // Lire le fichier JSON et mapper sur un DTO
        File file = new File(filePath);
        TemoignageDTO TemoignageDTO = objectMapper.readValue(file, TemoignageDTO.class);

        PersonnageEntity temoin = temoinRepository.findById(TemoignageDTO.getTemoinId())
                .orElseThrow(() -> new IllegalArgumentException("Témoin non trouvé pour l'ID : " + TemoignageDTO.getTemoinId()));

        PersonnageEntity suspect = temoinRepository.findById(TemoignageDTO.getSuspectId())
                .orElseThrow(() -> new IllegalArgumentException("Suspect non trouvé pour l'ID : " + TemoignageDTO.getSuspectId()));

        // Convertir le DTO en entité
        TemoignageEntity temoignageEntity = TemoignageEntity.builder()

                .description(TemoignageDTO.description())
                .temoin(temoin)
                .suspect(suspect)
                .build();

        // Sauvegarder l'entité dans la base de données
        return temoignageRepository.save(temoignageEntity);
    }

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