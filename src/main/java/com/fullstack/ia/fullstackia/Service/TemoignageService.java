package com.fullstack.ia.fullstackia.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.ia.fullstackia.Dto.TemoignageDto;
import com.fullstack.ia.fullstackia.Entity.TemoignageEntity;
import com.fullstack.ia.fullstackia.Repository.TemoignageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class TemoignageService {

    private final TemoignageRepository temoignageRepository;
    private final ObjectMapper objectMapper;

    public TemoignageEntity creerTemoignangeJson(String filePath) throws IOException {
        // Lire le fichier JSON et mapper sur un DTO
        File file = new File(filePath);
        TemoignageDto temoignageDto = objectMapper.readValue(file, TemoignageDto.class);

        // Convertir le DTO en entité
        TemoignageEntity temoignageEntity = TemoignageEntity.builder()
                .incriminant(temoignageDto.incriminant())
                .disculpant(temoignageDto.disculpant())
                .type(temoignageDto.type())
                .isTrue(temoignageDto.isTrue())
                .build();

        // Sauvegarder l'entité dans la base de données
        return temoignageRepository.save(temoignageEntity);
    }

}
