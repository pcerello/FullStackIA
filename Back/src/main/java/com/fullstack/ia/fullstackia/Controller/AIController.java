package com.fullstack.ia.fullstackia.Controller;
import com.fullstack.ia.fullstackia.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AIController {

    private final TemoignageService temoignageService;
    private final ScenarioService scenarioService;
    private final ScenarioPriveService scenarioPriveService;
    private final EvaluationService evaluationService;

    @PostMapping("/genererScenario")
    public String genererScenario(@RequestParam String question) {
        return scenarioService.genererScenario(question);
    }

    @PostMapping(path = "/genererTemoignages")
    public String genererTemoignagesPourScenario(@RequestParam String question) {
        return temoignageService.genererTemoignages(question);
    }

    @PostMapping(path = "/evaluationReponseUtilisateur")
    public String evaluationReponseUtilisateur(@RequestParam String userResponse) {
        return evaluationService.evaluerReponse(userResponse);
    }
}