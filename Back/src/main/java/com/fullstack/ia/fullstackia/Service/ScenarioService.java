package com.fullstack.ia.fullstackia.Service;

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

    public String genererScenario(String question){
        String prompt = fileReadingService.readInternalFileAsString("prompts/simple_prompt.txt");
        String response = aiService.appelOllama(question,prompt);
        saveGeneratedScenario(response);

        return "Scénario généré et sauvegardé : " + response;
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
