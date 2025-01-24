package com.fullstack.ia.fullstackia.Service;

import com.fullstack.ia.fullstackia.DTO.ScenarioDTO;
import com.fullstack.ia.fullstackia.Entity.ScenarioEntity;
import com.fullstack.ia.fullstackia.Repository.ScenarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ScenarioService {

    private final ScenarioRepository scenarioRepository;
    private final AIService aiService;
    private final FileReadingService fileReadingService;

    public ResponseEntity<ScenarioDTO> genererScenario(String question) {
        String prompt = fileReadingService.readInternalFileAsString("prompts/simple_prompt.txt");
        String response = aiService.appelOllama(question,prompt);

        return saveGeneratedScenario(response);

    }

    public ResponseEntity<ScenarioDTO> saveGeneratedScenario(String scenarioDescription) {
    // Créer une nouvelle entité et la sauvegarder
    ScenarioEntity scenarioEntity = ScenarioEntity.builder()
            .description(scenarioDescription)
            .build();

        ScenarioEntity savedScenario = scenarioRepository.save(scenarioEntity);

        // Convertir l'entité sauvegardée en DTO
        ScenarioDTO scenarioDTO = new ScenarioDTO(
                savedScenario.getId(),
                savedScenario.getDescription()
        );

        // retourner une réponse avec le DTO
        return ResponseEntity.ok(scenarioDTO);
    }

    public ScenarioEntity getLastInsertedScenario() {
        return scenarioRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new IllegalArgumentException("Aucun scénario trouvé dans la base de données."));
    }

    public ResponseEntity<ScenarioDTO> getScenarioById(Long scenarioId) {
        Optional<ScenarioEntity> scenarioEntityOptional = scenarioRepository.findById(scenarioId);

        if (scenarioEntityOptional.isEmpty()) {
            // Retourner un code 404 si le scénario n'existe pas
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Si le scénario existe, le convertir en DTO
        ScenarioEntity scenarioEntity = scenarioEntityOptional.get();
        ScenarioDTO scenarioDTO = new ScenarioDTO(
                scenarioEntity.getId(),
                scenarioEntity.getDescription()
        );

        // Retourner le DTO avec un statut 200 OK
        return ResponseEntity.ok(scenarioDTO);
    }

    public ResponseEntity<List<ScenarioDTO>> getLastInsertedScenarios() {
        // on récupére les derniers scenarios insérés en base de donnée avec une limite de 5
        List<ScenarioEntity> lastScenarios = scenarioRepository.findTop5ByOrderByIdDesc();

        List<ScenarioDTO> scenarioDTOs = lastScenarios.stream()
               .map(scenario -> new ScenarioDTO(
                        scenario.getId(),
                        scenario.getDescription()
                ))
               .collect(Collectors.toList());

        return ResponseEntity.ok(scenarioDTOs);
    }
}
