package com.fullstack.ia.fullstackia.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.ia.fullstackia.Dto.PreuveDto;
import com.fullstack.ia.fullstackia.Entity.PreuveEntity;
import com.fullstack.ia.fullstackia.Enum.PreuveMateriel;
import com.fullstack.ia.fullstackia.Repository.PreuveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PreuveService {

    private final PreuveRepository preuveRepository;
    private final ObjectMapper objectMapper;

    public PreuveEntity creerPreuveJson(String filePath) throws IOException {
        // Lire le fichier Json et le mapper dans le DTO
        File file = new File(filePath);
        PreuveDto preuveDto = objectMapper.readValue(file, PreuveDto.class);

        PreuveEntity preuveEntity = PreuveEntity.builder()
                .preuveMateriel(PreuveMateriel.DOCUMENT)
                .incriminant(preuveDto.incriminant())
                .disculpant(preuveDto.disculpant())
                .build();

        return preuveRepository.save(preuveEntity);


    }

}
