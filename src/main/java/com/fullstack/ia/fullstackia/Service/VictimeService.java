package com.fullstack.ia.fullstackia.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.ia.fullstackia.DTO.PersonnageDTO;
import com.fullstack.ia.fullstackia.DTO.VictimeDTO;
import com.fullstack.ia.fullstackia.Entity.ScenarioEntity;
import com.fullstack.ia.fullstackia.Entity.VictimeEntity;
import com.fullstack.ia.fullstackia.Repository.ScenarioRepository;
import com.fullstack.ia.fullstackia.Repository.VictimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class VictimeService {

    private final VictimeRepository victimeRepository;
    private final ScenarioRepository scenarioRepository;
    private final ObjectMapper objectMapper;

    public VictimeEntity creerVictimeDepuisJson(String filePath) throws IOException {
        // Lire le fichier JSON et mapper sur un DTO
        File file = new File(filePath);
        VictimeDTO victimeDTO = objectMapper.readValue(file, VictimeDTO.class);
        PersonnageDTO personnageDTO = victimeDTO.personnageDTO();

        Long scenarioId = personnageDTO.scenario().getId(); // Assurez-vous que le DTO inclut un champ ou une méthode scenarioId
        ScenarioEntity scenario = scenarioRepository.findById(scenarioId)
                .orElseThrow(() -> new IllegalArgumentException("Scénario non trouvé pour l'ID : " + scenarioId));

        VictimeEntity victimeEntity = new VictimeEntity(
                personnageDTO.nom(),
                personnageDTO.prenom(),
                personnageDTO.age(),
                personnageDTO.sexe(),
                personnageDTO.role(),
                personnageDTO.caractere(),
                personnageDTO.alibi(),
                personnageDTO.mobile(),
                scenario,
                victimeDTO.arme(),
                victimeDTO.lieu(),
                victimeDTO.description()
        );

        // Sauvegarder l'entité dans la base de données
        return victimeRepository.save(victimeEntity);
    }

}