package com.fullstack.ia.fullstackia.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final FileReadingService fileReadingService;

    @PostMapping("/genererScenario")
    public String genererScenario(@RequestParam String question)  throws JsonProcessingException{
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

    @PostMapping(path="testJson")
    public JsonNode testJson() throws JsonProcessingException {
        String substring = fileReadingService.readInternalFileAsString("prompts/test.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonObject = mapper.readTree(substring);
        return jsonObject;
    }
}