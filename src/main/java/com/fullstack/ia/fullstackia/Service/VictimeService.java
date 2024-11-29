package com.fullstack.ia.fullstackia.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.ia.fullstackia.Dto.VictimeDTO;
import com.fullstack.ia.fullstackia.Entity.VictimeEntity;
import com.fullstack.ia.fullstackia.Repository.VictimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class VictimeService {
    @Autowired
    private VictimeRepository victimeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public VictimeEntity creerVictimeDepuisJson(String filePath) throws IOException {
        // Lire le fichier JSON et mapper sur un DTO
        File file = new File(filePath);
        VictimeDTO victimeDTO = objectMapper.readValue(file, VictimeDTO.class);

        // Convertir le DTO en entité
        VictimeEntity victimeEntity = new VictimeEntity(
                victimeDTO.arme(),
                victimeDTO.lieu(),
                victimeDTO.description()
        );

        // Sauvegarder l'entité dans la base de données
        return victimeRepository.save(victimeEntity);
    }

}
