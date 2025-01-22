package com.fullstack.ia.fullstackia.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fullstack.ia.fullstackia.DTO.TemoignageDTO;
import com.fullstack.ia.fullstackia.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AIController {

    private final TemoignageService temoignageService;
    private final ScenarioService scenarioService;
    private final ScenarioPriveService scenarioPriveService;
    private final EvaluationService evaluationService;
    private final FileReadingService fileReadingService;

    @PostMapping("/genererScenario")
    public String genererScenario(@RequestParam String question) throws JsonProcessingException {
        String scenarioPublique = scenarioService.genererScenario(question);
        scenarioPriveService.genererScenarioPrive(scenarioPublique,"");// je met la question à vide car on l'a déjà fourni la question à Ollama pour générer le scénario publique, on en a pas besoin pour le scénario privé
        return scenarioPublique;
    }

    @PostMapping(path = "/genererTemoignages")
    public String genererTemoignagesPourScenario(@RequestParam String question) throws JsonProcessingException{
        return temoignageService.genererTemoignages(question);
    }

    @PostMapping(path = "/evaluationReponseUtilisateur")
    public String evaluationReponseUtilisateur(@RequestParam String userResponse) {
        return evaluationService.evaluerReponse(userResponse);
    }

    @GetMapping("/temoignages/{scenarioId}")
    public ResponseEntity<List<TemoignageDTO>> getTemoignagesByScenario(@PathVariable Long scenarioId) {
        List<TemoignageDTO> temoignages = temoignageService.getTempoignagesByIdScenarioById(scenarioId);
        return ResponseEntity.ok(temoignages);
    }

}