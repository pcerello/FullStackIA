package com.fullstack.ia.fullstackia.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.ia.fullstackia.DTO.PersonnageDTO;
import com.fullstack.ia.fullstackia.Entity.PersonnageEntity;
import com.fullstack.ia.fullstackia.Entity.PreuveEntity;
import com.fullstack.ia.fullstackia.Entity.ScenarioEntity;
import com.fullstack.ia.fullstackia.Enum.Role;
import com.fullstack.ia.fullstackia.Repository.PersonnageRepository;
import com.fullstack.ia.fullstackia.Repository.ScenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class PersonnageService {

    @Autowired
    private PersonnageRepository personnageRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ScenarioRepository scenarioRepository;


    public PersonnageEntity creerPersonnageDepuisJson(String filePath) throws IOException {
        // Lire le fichier JSON et mapper sur un DTO
        File file = new File(filePath);
        PersonnageDTO personnageDTO = objectMapper.readValue(file, PersonnageDTO.class);

            /*
            ScenarioEntity scenario = scenarioRepository.findById(personnageDTO.getScenarioId())
                    .orElseThrow(() -> new IllegalArgumentException("Scénario non trouvé pour l'ID : " + personnageDTO.getScenarioId()));
             */
        Long scenarioId = personnageDTO.getScenario().getId();
        if (scenarioId == null) {
            throw new IllegalArgumentException("L'ID du scénario est manquant dans le fichier JSON.");
        }

        // Récupérer le scénario correspondant
        ScenarioEntity scenario = scenarioRepository.findById(scenarioId)
                .orElseThrow(() -> new IllegalArgumentException("Scénario non trouvé pour l'ID : " + scenarioId));


        // Convertir le DTO en entité
        PersonnageEntity personnageEntity = PersonnageEntity.builder()
                .nom(personnageDTO.nom())
                .prenom(personnageDTO.prenom())
                .age(personnageDTO.age())
                .sexe(personnageDTO.sexe())
                .role(personnageDTO.role())
                .caractere(personnageDTO.caractere())
                .alibi(personnageDTO.alibi())
                .mobile(personnageDTO.mobile())
                .scenario(scenario)
                .build();

        // Sauvegarder l'entité dans la base de données
        return personnageRepository.save(personnageEntity);
    }

    public List<PersonnageEntity> creerPersonnagesDepuisJson(String filePath) throws IOException {
        // Lire le fichier JSON et mapper sur une liste de DTO
        File file = new File(filePath);
        List<PersonnageDTO> personnagesDTO = objectMapper.readValue(file,
                objectMapper.getTypeFactory().constructCollectionType(List.class, PersonnageDTO.class));

        // Parcourir les DTO pour les convertir en entités et les sauvegarder
        return personnagesDTO.stream().map(personnageDTO -> {
            Long scenarioId = personnageDTO.getScenario().getId();
            if (scenarioId == null) {
                throw new IllegalArgumentException("L'ID du scénario est manquant dans le fichier JSON.");
            }

            // Récupérer le scénario correspondant
            ScenarioEntity scenario = scenarioRepository.findById(scenarioId)
                    .orElseThrow(() -> new IllegalArgumentException("Scénario non trouvé pour l'ID : " + scenarioId));

            // Convertir le DTO en entité
            PersonnageEntity personnageEntity = PersonnageEntity.builder()
                    .nom(personnageDTO.nom())
                    .prenom(personnageDTO.prenom())
                    .age(personnageDTO.age())
                    .sexe(personnageDTO.sexe())
                    .role(personnageDTO.role())
                    .caractere(personnageDTO.caractere())
                    .alibi(personnageDTO.alibi())
                    .mobile(personnageDTO.mobile())
                    .scenario(scenario)
                    .build();

            // Sauvegarder et retourner l'entité
            return personnageRepository.save(personnageEntity);
        }).toList();
    }
}