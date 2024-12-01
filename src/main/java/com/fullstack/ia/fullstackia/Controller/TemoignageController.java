package com.fullstack.ia.fullstackia.Controller;

import com.fullstack.ia.fullstackia.Entity.PreuveEntity;
import com.fullstack.ia.fullstackia.Entity.TemoignageEntity;
import com.fullstack.ia.fullstackia.Service.PreuveService;
import com.fullstack.ia.fullstackia.Service.TemoignageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TemoignageController {

    private final TemoignageService temoignageService;
    private final PreuveService preuveService;

    @PostMapping(path="/import-temoignage")
    public TemoignageEntity importTemoignageJson(@RequestParam String filePath) {

        try{
            return temoignageService.creerTemoignangeJson(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'importation du temoignage : " + e.getMessage(), e);
        }
    }

    @PostMapping(path="/import-preuve")
    public PreuveEntity importPreuveJson(@RequestParam String filePath) {

        try{
            return preuveService.creerPreuveJson(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'importation du temoignage : " + e.getMessage(), e);
        }
    }

}
