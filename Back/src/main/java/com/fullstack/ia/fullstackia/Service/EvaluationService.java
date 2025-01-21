package com.fullstack.ia.fullstackia.Service;

import com.fullstack.ia.fullstackia.Entity.EvaluationEntity;
import com.fullstack.ia.fullstackia.Entity.ScenarioEntity;
import com.fullstack.ia.fullstackia.Entity.ScenarioPriveEntity;
import com.fullstack.ia.fullstackia.Entity.TemoignageEntity;
import com.fullstack.ia.fullstackia.Repository.EvaluationRepository;
import lombok.RequiredArgsConstructor;
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

    public String evaluerReponse(String userResponse){
        try {
            // récupérer le dernier scénario publique en base
            ScenarioEntity lastScenario = scenarioService.getLastInsertedScenario();
            if (lastScenario == null) {
                return "Aucun scénario disponible.";
            }

            // récupèrer le scenario privé lié au scénario publique
            ScenarioPriveEntity scenarioPrive = scenarioPriveService.getScenarioPriveByScenarioId(lastScenario.getId());

            // récupérer les témoignages liés au dernier scénario
            List<TemoignageEntity> temoignages = temoignageService.getTemoignagesByScenarioId(lastScenario.getId());
            /*if (temoignages.isEmpty()) {
                return "Aucun témoignage disponible pour le scénario.";
            }*/

            String prompt = "Voici le contexte de l'enquête :\n" +
                    "Scénario publique dévoilé à l'utilisateur : " + lastScenario.getDescription() + "\n" +
                    "Témoignages :\n";

            for (TemoignageEntity temoignage : temoignages) {
                prompt += "- " + temoignage.getDescription() + "\n";
            }

            prompt += "\n Scénario privé contenant le coupable : \n" + scenarioPrive.getDescription() + "\n" +
                    "Ce scénario est caché à l'utilisateur ";

            prompt +="\n Vous allez également recevoir la réponse de l'utilisateur qui tentera de résoudre le mystère de cette enquete \n"+
                    "Évaluez cette réponse en fonction du contexte décrit dans le scénario publique et le meurtrier qui est dans le scénario privé. Indiquez si la réponse de l'utilisateur est correcte, incorrecte ou partiellement correcte, en justifiant votre évaluation.";

            String evaluation = aIService.appelOllama(userResponse,prompt);
            saveGeneratedEvaluation(evaluation);
            // Retourner l'évaluation
            return evaluation;

        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur inattendue : " + e.getMessage();
        }
    }

    public void saveGeneratedEvaluation(String description){
        EvaluationEntity evaluationEntity =  EvaluationEntity.builder()
                .description(description)
                .build();
        evaluationRepository.save(evaluationEntity);
    }
}
