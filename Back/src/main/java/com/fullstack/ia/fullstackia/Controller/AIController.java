package com.fullstack.ia.fullstackia.Controller;
import com.fullstack.ia.fullstackia.DTO.EvaluationDTO;
import com.fullstack.ia.fullstackia.DTO.QuestionDTO;
import com.fullstack.ia.fullstackia.DTO.ScenarioDTO;
import com.fullstack.ia.fullstackia.DTO.TemoignageDTO;
import com.fullstack.ia.fullstackia.Service.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<ScenarioDTO> genererScenario(@RequestBody QuestionDTO questionDTO) {
        //String scenarioPublique = scenarioService.genererScenario(question);
        System.out.println(questionDTO.variable1());
        ResponseEntity<ScenarioDTO> scenarioPublique = scenarioService.genererScenario(questionDTO.variable1());
        scenarioPriveService.genererScenarioPrive(String.valueOf(scenarioPublique.getBody()),questionDTO.variable1());
        return scenarioPublique;
    }

    @PostMapping(path = "/genererTemoignages")
    public ResponseEntity<TemoignageDTO> genererTemoignagesPourScenario(@RequestParam String question){
        return temoignageService.genererTemoignages(question);
    }

    @PostMapping(path = "/evaluationReponseUtilisateur")
    public ResponseEntity<EvaluationDTO> evaluationReponseUtilisateur(@RequestParam String userResponse) {
        return evaluationService.evaluerReponse(userResponse);
    }

    @GetMapping("/temoignages/{scenarioId}")
    public ResponseEntity<List<TemoignageDTO>> getTemoignagesByScenario(@PathVariable Long scenarioId) {
        return temoignageService.getTempoignagesByIdScenarioById(scenarioId);
    }

}