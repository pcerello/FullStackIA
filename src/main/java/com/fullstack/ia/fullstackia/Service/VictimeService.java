package com.fullstack.ia.fullstackia.Service;

import com.fullstack.ia.fullstackia.Dto.VictimeDTO;
import com.fullstack.ia.fullstackia.Entity.VictimeEntity;
import com.fullstack.ia.fullstackia.Repository.VictimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VictimeService {
    @Autowired
    private VictimeRepository victimeRepository;

    public VictimeDTO creerVictime(VictimeDTO victimeDTO) {
        // Convertir le DTO en entité
        VictimeEntity victime = new VictimeEntity(
                victimeDTO.arme(),
                victimeDTO.lieu(),
                victimeDTO.description()
        );
        VictimeEntity savedVictime = victimeRepository.save(victime);
        return VictimeDTO.builder()
                .arme(savedVictime.getArme())
                .lieu(savedVictime.getLieu())
                .description(savedVictime.getDescription())
                .build();
    }

    public List<VictimeDTO> obtenirToutesLesVictimes() {
        return victimeRepository.findAll().stream()
                .map(victime -> VictimeDTO.builder()
                        .arme(victime.getArme())
                        .lieu(victime.getLieu())
                        .description(victime.getDescription())
                        .build()
                )
                .collect(Collectors.toList());
    }

}
