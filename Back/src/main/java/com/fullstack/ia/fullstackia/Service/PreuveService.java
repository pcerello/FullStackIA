package com.fullstack.ia.fullstackia.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.ia.fullstackia.DTO.PreuveDTO;
import com.fullstack.ia.fullstackia.Entity.PreuveEntity;
import com.fullstack.ia.fullstackia.Entity.TemoignageEntity;
import com.fullstack.ia.fullstackia.Enum.PreuveMateriel;
import com.fullstack.ia.fullstackia.Repository.PreuveRepository;
import com.fullstack.ia.fullstackia.Repository.TemoignageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PreuveService {

    private final PreuveRepository preuveRepository;
    private final ObjectMapper objectMapper;

    private final TemoignageRepository temoignageRepository;

    public PreuveEntity creerPreuveDepuisJson(String filePath) throws IOException {
        // Lire le fichier Json et le mapper dans le DTO
        File file = new File(filePath);
        PreuveDTO PreuveDTO = objectMapper.readValue(file, PreuveDTO.class);

        TemoignageEntity temoignage = temoignageRepository.findById(PreuveDTO.getTemoignageId())
                .orElseThrow(() -> new IllegalArgumentException("Témoignage non trouvé pour l'ID : " + PreuveDTO.getTemoignageId()));


        PreuveEntity preuveEntity = PreuveEntity.builder()
                .type(PreuveDTO.type())
                .description(PreuveDTO.description())
                .temoignage(temoignage)
                .build();

        return preuveRepository.save(preuveEntity);
    }

}