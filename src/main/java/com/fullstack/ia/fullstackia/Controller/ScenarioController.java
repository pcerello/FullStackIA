package com.fullstack.ia.fullstackia.Controller;

import com.fullstack.ia.fullstackia.Entity.ScenarioEntity;
import com.fullstack.ia.fullstackia.Service.PreuveService;
import com.fullstack.ia.fullstackia.Service.ScenarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScenarioController {
    private final ScenarioService scenarioService;

    @PostMapping("/import-scenario")
    public void importerScenarioDepuisJson(@RequestParam String filePath) {
        try {
            scenarioService.creerScenarioDepuisJson(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'importation du scenario : " + e.getMessage(), e);
        }
    }
}