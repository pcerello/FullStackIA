package com.fullstack.ia.fullstackia.Controller;
import com.fullstack.ia.fullstackia.DTO.*;
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

    @PostMapping("/genererScenario")
    public ResponseEntity<ScenarioDTO> genererScenario(@RequestBody QuestionDTO questionDTO) {
        ResponseEntity<ScenarioDTO> scenarioPublique = scenarioService.genererScenario(questionDTO.question());
        scenarioPriveService.genererScenarioPrive(String.valueOf(scenarioPublique.getBody()), ""); //je met la quetion à vide pour le scénario caché car cette question est déjà fournie pour la génération du scénario publique
        return scenarioPublique;
    }

    @PostMapping(path = "/genererTemoignages")
    public ResponseEntity<TemoignageDTO> genererTemoignagesPourScenario(@RequestBody QuestionDTO questionDTO) {
        return temoignageService.genererTemoignages(questionDTO.question());
    }

    @PostMapping(path = "/evaluationReponseUtilisateur")
    public ResponseEntity<EvaluationDTO> evaluationReponseUtilisateur(@RequestBody QuestionDTO userResponse) {
        return evaluationService.evaluerReponse(userResponse.question());
    }

    // récupèrer tous les témoignages liés à un scenario
    @GetMapping("/scenario/{scenarioId}/ListeTemoignages")
    public ResponseEntity<List<TemoignageDTO>> getTemoignagesByScenario(@PathVariable Long scenarioId) {
        return temoignageService.getTempoignagesByIdScenario(scenarioId);
    }

    // récupèrer un scénario par son id
    @GetMapping("/scenario/{scenarioId}")
    public ResponseEntity<ScenarioDTO> getScenarioById(@PathVariable Long scenarioId) {
        return scenarioService.getScenarioById(scenarioId);
    }

    //récupèrer l'historique de nos scenarios
    @GetMapping("/historiqueScenarios")
    public ResponseEntity<List<ScenarioDTO>> getLastInsertedScenarios(){
        return scenarioService.getLastInsertedScenarios();
    }

    // récupérer l'evaluation liée à un scenario
    @GetMapping("/scenario/{scenarioId}/evaluation")
    public ResponseEntity<EvaluationDTO> getEvaluationByScenario(@PathVariable Long scenarioId) {
        return evaluationService.getEvaluationByIdScenario(scenarioId);
    }

}