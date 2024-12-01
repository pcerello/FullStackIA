package com.fullstack.ia.fullstackia.Controller;

import com.fullstack.ia.fullstackia.Entity.TemoignageEntity;
import com.fullstack.ia.fullstackia.Entity.VictimeEntity;
import com.fullstack.ia.fullstackia.Service.TemoignageService;
import com.fullstack.ia.fullstackia.Service.VictimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VictimeController {

    private final VictimeService victimeService;

    @PostMapping("/import-victime")
    public VictimeEntity importerVictimeDepuisJson(@RequestParam String filePath) {
        try {
            return victimeService.creerVictimeDepuisJson(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'importation de la victime : " + e.getMessage(), e);
        }
    }

}
