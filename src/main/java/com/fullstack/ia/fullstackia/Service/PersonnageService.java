package com.fullstack.ia.fullstackia.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.ia.fullstackia.DTO.PersonnageDTO;
import com.fullstack.ia.fullstackia.Entity.PersonnageEntity;
import com.fullstack.ia.fullstackia.Repository.PersonnageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PersonnageService {

        @Autowired
        private PersonnageRepository personnageRepository;

        @Autowired
        private ObjectMapper objectMapper;

        public PersonnageEntity creerPersonnageDepuisJson(String filePath) throws IOException {
            // Lire le fichier JSON et mapper sur un DTO
            File file = new File(filePath);
            PersonnageDTO personnageDTO = objectMapper.readValue(file, PersonnageDTO.class);

            // Convertir le DTO en entité
            PersonnageEntity personnageEntity = new PersonnageEntity(
                    personnageDTO.nom(),
                    personnageDTO.prenom(),
                    personnageDTO.age(),
                    personnageDTO.sexe(),
                    personnageDTO.role(),
                    personnageDTO.caractere(),
                    personnageDTO.alibi(),
                    personnageDTO.mobile()
            );

            // Sauvegarder l'entité dans la base de données
            return personnageRepository.save(personnageEntity);
        }
}
