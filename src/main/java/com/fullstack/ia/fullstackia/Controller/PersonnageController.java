package com.fullstack.ia.fullstackia.Controller;

import com.fullstack.ia.fullstackia.Entity.PersonnageEntity;
import com.fullstack.ia.fullstackia.Service.PersonnageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PersonnageController {

    private final PersonnageService personnageService;

    @PostMapping("/import-personnage")
    public PersonnageEntity importerPersonnageDepuisJson(@RequestParam String filePath) {
        try {
            return personnageService.creerPersonnageDepuisJson(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'importation du personnage : " + e.getMessage(), e);
        }
    }
}
