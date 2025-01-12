package com.fullstack.ia.fullstackia.Controller;

import com.fullstack.ia.fullstackia.Entity.PersonnageEntity;
import com.fullstack.ia.fullstackia.Entity.VictimeEntity;
import com.fullstack.ia.fullstackia.Service.PersonnageService;
import com.fullstack.ia.fullstackia.Service.VictimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PersonnageController {

    private final PersonnageService personnageService;
    private final VictimeService victimeService;

    @PostMapping("/import-personnage")
    public PersonnageEntity importerPersonnageDepuisJson(@RequestParam String filePath) {
        try {
            return personnageService.creerPersonnageDepuisJson(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'importation du personnage : " + e.getMessage(), e);
        }
    }

    @PostMapping("/import-victime")
    public VictimeEntity importerVictimeDepuisJson(@RequestParam String filePath) {
        try {
            return victimeService.creerVictimeDepuisJson(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'importation de la victime : " + e.getMessage(), e);
        }
    }
}
