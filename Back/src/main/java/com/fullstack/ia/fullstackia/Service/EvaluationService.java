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


@Service
@RequiredArgsConstructor
public class EvaluationService {
    private final ScenarioService scenarioService;
    private final TemoignageService temoignageService;
    private final AIService aIService;
    private final EvaluationRepository evaluationRepository;
    private final ScenarioPriveService scenarioPriveService;
    private final ScenarioRepository scenarioRepository;

    public ResponseEntity<EvaluationDTO> evaluerReponse(String userResponse) {

        // récupérer le dernier scénario publique en base
        ScenarioEntity lastScenario = scenarioService.getLastInsertedScenario();


        // récupèrer le scenario privé lié au scénario publique
        ScenarioPriveEntity scenarioPrive = scenarioPriveService.getScenarioPriveByScenarioId(lastScenario.getId());

        // récupérer les témoignages liés au dernier scénario
        List<TemoignageEntity> temoignages = temoignageService.getTemoignagesByScenarioId(lastScenario.getId());

        String prompt = "Voici le contexte de l'enquête :\n" +
                "Scénario publique dévoilé à l'utilisateur : " + lastScenario.getDescription() + "\n" +
                "Témoignages :\n";

        for (TemoignageEntity temoignage : temoignages) {
            prompt += "- " + temoignage.getDescription() + "\n";
        }

        prompt += "\n Scénario privé contenant le coupable : \n" + scenarioPrive.getDescription() + "\n" +
                "Ce scénario est caché à l'utilisateur ";

        prompt += "\n Vous allez également recevoir la réponse de l'utilisateur qui tentera de résoudre le mystère de cette enquete \n" +
                "Évaluez cette réponse en fonction du contexte décrit dans le scénario publique et le meurtrier qui est dans le scénario privé. Indiquez si la réponse de l'utilisateur est correcte, incorrecte ou partiellement correcte, en justifiant votre évaluation.";

        String evaluation = aIService.appelOllama(userResponse, prompt);

        return saveGeneratedEvaluation(evaluation);
    }
    public ResponseEntity<EvaluationDTO> saveGeneratedEvaluation(String description){
        EvaluationEntity evaluationEntity =  EvaluationEntity.builder()
                .description(description)
                .build();
        EvaluationEntity savedEvaluation = evaluationRepository.save(evaluationEntity);

        EvaluationDTO evaluationDTO = new EvaluationDTO(
                savedEvaluation.getId(),
                savedEvaluation.getDescription()
        );

        return ResponseEntity.ok(evaluationDTO);
    }

    public ResponseEntity<EvaluationDTO> getEvaluationByIdScenario(Long scenarioId) {
        // on vérifie si le scenario existe en base de donnée
        if (!scenarioRepository.existsById(scenarioId)) {
            // on retourne un code 404 si le scénario n'existe pas
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        EvaluationEntity evaluationEntity = evaluationRepository.findByScenarioId(scenarioId);

        //on vérifie si on a recup l'evaluation
        if (evaluationEntity == null) {
            // on retourner un code 204 No Content s'il y est pas
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        EvaluationDTO evaluationDTO = new EvaluationDTO(
                evaluationEntity.getId(),
                evaluationEntity.getDescription()
        );

        return ResponseEntity.ok(evaluationDTO);

    }
}
