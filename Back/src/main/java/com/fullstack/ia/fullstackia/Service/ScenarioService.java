package com.fullstack.ia.fullstackia.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fullstack.ia.fullstackia.Entity.ScenarioEntity;
import com.fullstack.ia.fullstackia.Repository.ScenarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ScenarioService {

    private final ScenarioRepository scenarioRepository;
    private final AIService aiService;
    private final FileReadingService fileReadingService;

    public String genererScenario(String question) throws JsonProcessingException {
        String prompt = fileReadingService.readInternalFileAsString("prompts/simple_prompt.txt");
        String response = aiService.appelOllama(question,prompt);
        saveGeneratedScenario(response);


        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonNode = mapper.createObjectNode();
        jsonNode.put("scenario", response);

        return mapper.writeValueAsString(jsonNode);

    }

    public void saveGeneratedScenario(String scenarioDescription) {
    // Créer une nouvelle entité et la sauvegarder
    ScenarioEntity scenarioEntity = ScenarioEntity.builder()
            .description(scenarioDescription)
            .build();

        scenarioRepository.save(scenarioEntity);
    }

    public ScenarioEntity getLastInsertedScenario() {
        return scenarioRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new IllegalArgumentException("Aucun scénario trouvé dans la base de données."));
    }

}
