package com.fullstack.ia.fullstackia.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.ia.fullstackia.Dto.PersonnageDto;
import com.fullstack.ia.fullstackia.Dto.VictimeDto;
import com.fullstack.ia.fullstackia.Entity.PersonnagesEntity;
import com.fullstack.ia.fullstackia.Repository.PersonnagesRepository;
import com.fullstack.ia.fullstackia.Service.PersonnageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping( path = "/characters")
@RequiredArgsConstructor
public class PersonnageController {

    private final PersonnageService personnageService;
    private final ObjectMapper objectMapper;
    private final OllamaController ollama;
    private final PersonnagesRepository personnagesRepository;


    @PostMapping(path = "/create")
    public List<PersonnagesEntity> CreateCharacters() throws IOException {

//          Appel au service pour sauvegarder l'entité
        return personnageService.createPersonnage();

    }

    @PostMapping(path = "delete-all")
    public String DeleteAllCharacters() throws IOException {
        personnagesRepository.deleteAll();
        return "All characters deleted";
    }
}
