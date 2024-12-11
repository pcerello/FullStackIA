package com.fullstack.ia.fullstackia.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.ia.fullstackia.DTO.PersonnageDTO;
import com.fullstack.ia.fullstackia.DTO.ScenarioDTO;
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
public class ScenarioService {

    private ScenarioRepository scenarioRepository;
    private ObjectMapper objectMapper;
    public ScenarioEntity creerScenarioDepuisJson(String filePath) throws IOException {
        // Lire le fichier JSON et mapper sur un DTO
        File file = new File(filePath);

        ScenarioDTO scenarioDTO = objectMapper.readValue(file, ScenarioDTO.class);

        // Convertir le DTO en entité
        ScenarioEntity scenarioEntity = new ScenarioEntity(
                scenarioDTO.description()
        );

        // Sauvegarder l'entité dans la base de données
        return scenarioRepository.save(scenarioEntity);
    }

}
