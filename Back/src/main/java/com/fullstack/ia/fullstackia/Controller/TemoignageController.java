package com.fullstack.ia.fullstackia.Controller;

import com.fullstack.ia.fullstackia.Entity.TemoignageEntity;
import com.fullstack.ia.fullstackia.Service.TemoignageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TemoignageController {

    private final TemoignageService temoignageService;

    @PostMapping("/import-temoignage")
    public void importerTemoignageDepuisJson(@RequestParam String filePath) {
        try {
            temoignageService.creerTemoignageDepuisJson(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'importation du temoignage : " + e.getMessage(), e);
        }
    }
}