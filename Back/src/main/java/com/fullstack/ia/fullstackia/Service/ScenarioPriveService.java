package com.fullstack.ia.fullstackia.Service;

import com.fullstack.ia.fullstackia.Entity.ScenarioEntity;
import com.fullstack.ia.fullstackia.Entity.ScenarioPriveEntity;
import com.fullstack.ia.fullstackia.Repository.ScenarioPriveRepository;
import com.fullstack.ia.fullstackia.Repository.ScenarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScenarioPriveService {
    private final ScenarioPriveRepository scenarioPriveRepository;
    private final AIService aIService ;
    private final ScenarioRepository scenarioRepository;
    private final ScenarioService scenarioService;

    public void genererScenarioPrive(String scenarioPublique, String question){
        ScenarioEntity lastScenario = scenarioService.getLastInsertedScenario();

        String prompt = "Ceci est le scenario publique que tu m'a généré juste avant et qui sera exposé à l'utilisateur : \n" + scenarioPublique + 
                "\n Ce scénario publique possède des suspects. Parmis ces suspects il y a un meurtrier \n" +
                "Je veux que tu me dise quel est ce meurtrier. Ce dernier (le meurtrier) ne sera dévoilé à l'utilisateur qu'à la fin de son enquete";
        
        String meurtrier = aIService.appelOllama(question,prompt);
        
        saveGeneratedScenarioPrive(meurtrier,lastScenario.getId());
    }

    public void saveGeneratedScenarioPrive(String meurtrier,Long scenarioId){
        ScenarioPriveEntity scenarioPrive = ScenarioPriveEntity.builder()
                .scenario(scenarioRepository.findById(scenarioId).orElseThrow(() -> new IllegalArgumentException("Scenario non trouvé pour l'ID : " + scenarioId)))
                .description(meurtrier)
                .build();
        scenarioPriveRepository.save(scenarioPrive);
    }

    public ScenarioPriveEntity getScenarioPriveByScenarioId(Long scenarioId){
        return scenarioPriveRepository.findByScenarioId(scenarioId);
    }
}
