package com.fullstack.ia.fullstackia.Controller;

import com.fullstack.ia.fullstackia.Entity.PersonnageEntity;
import com.fullstack.ia.fullstackia.Entity.PreuveEntity;
import com.fullstack.ia.fullstackia.Service.PersonnageService;
import com.fullstack.ia.fullstackia.Service.PreuveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class PreuveController {

    private final PreuveService preuveService;

    @PostMapping("/import-preuve")
    public void importerPreuveDepuisJson(@RequestParam String filePath) {
        try {
            preuveService.creerPreuveDepuisJson(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'importation de la preuve : " + e.getMessage(), e);
        }
    }
}