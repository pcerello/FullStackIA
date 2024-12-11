package com.fullstack.ia.fullstackia.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.ia.fullstackia.DTO.PersonnageDTO;
import com.fullstack.ia.fullstackia.DTO.VictimeDTO;
import com.fullstack.ia.fullstackia.Entity.VictimeEntity;
import com.fullstack.ia.fullstackia.Repository.VictimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class VictimeService {

    private VictimeRepository victimeRepository;
    private ObjectMapper objectMapper;

    public VictimeEntity creerVictimeDepuisJson(String filePath) throws IOException {
        // Lire le fichier JSON et mapper sur un DTO
        File file = new File(filePath);
        VictimeDTO victimeDTO = objectMapper.readValue(file, VictimeDTO.class);
        PersonnageDTO personnageDTO = victimeDTO.personnageDTO();


        // Convertir le DTO en entité
        VictimeEntity victimeEntity = new VictimeEntity(
                personnageDTO.nom(),
                personnageDTO.prenom(),
                personnageDTO.age(),
                personnageDTO.sexe(),
                personnageDTO.role(),
                personnageDTO.caractere(),
                personnageDTO.alibi(),
                personnageDTO.mobile(),
                victimeDTO.arme(),
                victimeDTO.lieu(),
                victimeDTO.description()
        );

        // Sauvegarder l'entité dans la base de données
        return victimeRepository.save(victimeEntity);
    }

}
