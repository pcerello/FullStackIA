package com.fullstack.ia.fullstackia.Service;

import com.fullstack.ia.fullstackia.Entity.ScenarioEntity;
import com.fullstack.ia.fullstackia.Entity.TemoignageEntity;
import com.fullstack.ia.fullstackia.Repository.ScenarioRepository;
import com.fullstack.ia.fullstackia.Repository.TemoignageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemoignageService {

    private final TemoignageRepository temoignageRepository;
    private final ScenarioRepository scenarioRepository;
    private final AIService aiService;
    private final ScenarioService scenarioService;

    public String genererTemoignages(String question){
        try {
            // récupérer le dernier scénario en base
            ScenarioEntity lastScenario = scenarioService.getLastInsertedScenario();

            if (lastScenario == null) {
                return "Aucun scénario";
            }

            // génèrer le prompt en se basant sur le dernier scnario inséré en bdd
            String prompt = "Voici le scénario : \n" + lastScenario.getDescription() + "\n" +
                    "Générez un témoignage pour chaque suspect, chaque témoignage incluant une preuve associée." +
                    "Tu ne dois pas dépasser les 200 caractères";

            String response =aiService.appelOllama(question,prompt);
            saveGeneratedTemoignages(response, lastScenario.getId());

            return response;

        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur : " + e.getMessage();
        }
    }

    public void saveGeneratedTemoignages(String description, Long scenarioId){
        TemoignageEntity temoignageEntity =  TemoignageEntity.builder()
                .description(description)
                .scenario(scenarioRepository.findById(scenarioId).orElseThrow(() -> new IllegalArgumentException("Scenario non trouvé pour l'ID : " + scenarioId)))
                .build();
        temoignageRepository.save(temoignageEntity);
    }

    public List<TemoignageEntity> getTemoignagesByScenarioId(Long scenarioId) {
        return temoignageRepository.findByScenarioId(scenarioId);
    }

}