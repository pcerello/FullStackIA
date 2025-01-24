package com.fullstack.ia.fullstackia.Service;

import com.fullstack.ia.fullstackia.DTO.TemoignageDTO;
import com.fullstack.ia.fullstackia.Entity.ScenarioEntity;
import com.fullstack.ia.fullstackia.Entity.TemoignageEntity;
import com.fullstack.ia.fullstackia.Repository.ScenarioRepository;
import com.fullstack.ia.fullstackia.Repository.TemoignageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class TemoignageService {

    private final TemoignageRepository temoignageRepository;
    private final ScenarioRepository scenarioRepository;
    private final AIService aiService;
    private final ScenarioService scenarioService;

    public ResponseEntity<TemoignageDTO> genererTemoignages(String question) {

        // récupérer le dernier scénario en base
        ScenarioEntity lastScenario = scenarioService.getLastInsertedScenario();

        // génèrer le prompt en se basant sur le dernier scnario inséré en bdd
        String prompt = "Voici le scénario : \n" + lastScenario.getDescription() + "\n" +
                "Générez un témoignage pour chaque suspect, chaque témoignage incluant une preuve associée." +
                "Tu ne dois pas dépasser les 200 caractères";

        String response =aiService.appelOllama(question,prompt);

        return saveGeneratedTemoignages(response, lastScenario.getId());
    }

    public ResponseEntity<TemoignageDTO> saveGeneratedTemoignages(String description, Long scenarioId){
        TemoignageEntity temoignageEntity =  TemoignageEntity.builder()
                .description(description)
                .scenario(scenarioRepository.findById(scenarioId).orElseThrow(() -> new IllegalArgumentException("Scenario non trouvé pour l'ID : " + scenarioId)))
                .build();
        TemoignageEntity savedTemoignage = temoignageRepository.save(temoignageEntity);

        TemoignageDTO temoignageDTO = new TemoignageDTO(
                savedTemoignage.getId(),
                savedTemoignage.getDescription()
        );

        return ResponseEntity.ok(temoignageDTO);
    }

    public List<TemoignageEntity> getTemoignagesByScenarioId(Long scenarioId) {
        return temoignageRepository.findByScenarioId(scenarioId);
    }

    public ResponseEntity<List<TemoignageDTO>> getTempoignagesByIdScenario(Long scenarioId) {
        // vérifier s'il y a un scénario avec cet id
        if (!scenarioRepository.existsById(scenarioId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Récupérer les entités Temoignage pour le scénario
        List<TemoignageEntity> temoignageEntities = temoignageRepository.findByScenarioId(scenarioId);

        // Vérifier s'il y a des témoignages
        if (temoignageEntities.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        };

        // Convertir les entités en DTO
        List<TemoignageDTO> temoignageDTOS = temoignageEntities.stream()
                .map(temoignage -> new TemoignageDTO(
                        temoignage.getId(),
                        temoignage.getDescription()

                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(temoignageDTOS);
    }
}