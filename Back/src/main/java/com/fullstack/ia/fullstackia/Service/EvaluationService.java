package com.fullstack.ia.fullstackia.Service;

import com.fullstack.ia.fullstackia.DTO.EvaluationDTO;
import com.fullstack.ia.fullstackia.Entity.EvaluationEntity;
import com.fullstack.ia.fullstackia.Entity.ScenarioEntity;
import com.fullstack.ia.fullstackia.Entity.ScenarioPriveEntity;
import com.fullstack.ia.fullstackia.Entity.TemoignageEntity;
import com.fullstack.ia.fullstackia.Repository.EvaluationRepository;
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
public class EvaluationService {
    private final ScenarioService scenarioService;
    private final TemoignageService temoignageService;
    private final AIService aIService;
    private final EvaluationRepository evaluationRepository;
    private final ScenarioPriveService scenarioPriveService;
    private final ScenarioRepository scenarioRepository;

    public ResponseEntity<EvaluationDTO> evaluerReponse(String userResponse, Long scenarioId) {

        Optional<ScenarioEntity> optionalScenario = scenarioRepository.findById(scenarioId);

        if (optionalScenario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ScenarioEntity scenario = optionalScenario.get();


        // récupèrer le scenario privé lié au scénario publique
        ScenarioPriveEntity scenarioPrive = scenarioPriveService.getScenarioPriveByScenarioId(scenario.getId());

        // récupérer les témoignages liés au dernier scénario
        List<TemoignageEntity> temoignages = temoignageService.getTemoignagesByScenarioId(scenario.getId());

        String prompt = "Voici le contexte de l'enquête :\n" +
                "Scénario publique dévoilé à l'utilisateur : " + scenario.getDescription() + "\n" +
                "Témoignages :\n";

        for (TemoignageEntity temoignage : temoignages) {
            prompt += "- " + temoignage.getDescription() + "\n";
        }

        prompt += "\n Scénario privé contenant le coupable : \n" + scenarioPrive.getDescription() + "\n" +
                "Ce scénario est caché à l'utilisateur ";

        prompt += "\n Vous allez également recevoir la réponse de l'utilisateur qui tentera de résoudre le mystère de cette enquete \n" +
                "Évaluez cette réponse en fonction du contexte décrit dans le scénario publique et le meurtrier qui est dans le scénario privé. Indiquez si la réponse de l'utilisateur est correcte, incorrecte ou partiellement correcte, en justifiant votre évaluation.";

        String evaluation = aIService.appelOllama(userResponse, prompt);

        return saveGeneratedEvaluation(evaluation,scenarioId);
    }
    public ResponseEntity<EvaluationDTO> saveGeneratedEvaluation(String description, Long scenarioId){
        EvaluationEntity evaluationEntity =  EvaluationEntity.builder()
                .description(description)
                .scenario(scenarioRepository.findById(scenarioId).orElseThrow(() -> new IllegalArgumentException("Scenario non trouvé pour l'ID : " + scenarioId)))
                .build();
        EvaluationEntity savedEvaluation = evaluationRepository.save(evaluationEntity);

        EvaluationDTO evaluationDTO = new EvaluationDTO(
                savedEvaluation.getId(),
                savedEvaluation.getDescription()
        );

        return ResponseEntity.ok(evaluationDTO);
    }

    public ResponseEntity<List<EvaluationDTO>> getEvaluationByIdScenario(Long scenarioId) {
        // on vérifie si le scenario existe en base de donnée
        if (!scenarioRepository.existsById(scenarioId)) {
            // on retourne un code 404 si le scénario n'existe pas
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<EvaluationEntity> evaluationEntities = evaluationRepository.findByScenarioId(scenarioId);

        //on vérifie si on a recup l'evaluation
        if (evaluationEntities.isEmpty()) {
            // on retourner un code 204 No Content s'il y est pas
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        List<EvaluationDTO> evaluationDTOS =  evaluationEntities.stream()
                .map(evaluation -> new EvaluationDTO(
                    evaluation.getId(),
                    evaluation.getDescription()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(evaluationDTOS);
    }
}
