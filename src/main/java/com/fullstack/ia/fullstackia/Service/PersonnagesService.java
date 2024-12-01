package com.fullstack.ia.fullstackia.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.ia.fullstackia.Dto.PersonnagesDto;
import com.fullstack.ia.fullstackia.Enum.Role;
import com.fullstack.ia.fullstackia.Enum.Sexe;
import com.fullstack.ia.fullstackia.Entity.PersonnagesEntity;
import com.fullstack.ia.fullstackia.Repository.PersonnagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PersonnagesService{

        private final PersonnagesRepository personnagesRepository;
    private final ObjectMapper objectMapper;

    public PersonnagesEntity creerPersonnage(String filePath) throws IOException {

            // String filePath Contient le lien pour acceder au fichier
            File file = new File(filePath);
            PersonnagesDto personnagesDto = objectMapper.readValue(file, PersonnagesDto.class);

            PersonnagesEntity personnages = PersonnagesEntity.builder()
                    .nom(personnagesDto.nom())
                    .prenom(personnagesDto.prenom())
                    .age(personnagesDto.age())
                    .sexe(personnagesDto.sexe())
                    .role(personnagesDto.role())
                    .mobile(personnagesDto.mobile())
                    .alibi(personnagesDto.alibi())
                    .caractere(personnagesDto.caractere())
                    .build();

            return personnagesRepository.save(personnages);
        }
}
