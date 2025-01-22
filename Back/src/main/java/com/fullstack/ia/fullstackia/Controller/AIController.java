package com.fullstack.ia.fullstackia.Controller;
import com.fullstack.ia.fullstackia.DTO.*;
import com.fullstack.ia.fullstackia.Service.*;
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

    @PostMapping("/test")
    public QuestionDTOClass test(@RequestBody QuestionDTOClass questionDTO) {
        System.out.println("je suis la");
        System.out.println("test de : " + questionDTO);
        return questionDTO;
    }

    @PostMapping("/genererScenario")
    public ResponseEntity<ScenarioDTO> genererScenario(@RequestBody QuestionDTO questionDTO) {


        //String scenarioPublique = scenarioService.genererScenario(question);
        System.out.println(questionDTO.getVariable1());
        //ResponseEntity<ScenarioDTO> scenarioPublique = scenarioService.genererScenario(questionDTO.getVariable1());
        //System.out.println("public scenario generated"+scenarioPublique.getBody());
        //scenarioPriveService.genererScenarioPrive(String.valueOf(scenarioPublique.getBody()),questionDTO.getVariable1());
        //System.out.println("private scenario generated");
        return null;
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