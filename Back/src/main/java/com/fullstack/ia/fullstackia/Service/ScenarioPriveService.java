package com.fullstack.ia.fullstackia.Service;

import com.fullstack.ia.fullstackia.Entity.ScenarioPriveEntity;
import com.fullstack.ia.fullstackia.Repository.ScenarioPriveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScenarioPriveService {
    private final ScenarioPriveRepository scenarioPriveRepository;

    public void saveGeneratedScenarioPrive(String meurtrier){
        ScenarioPriveEntity scenarioPrive = ScenarioPriveEntity.builder()
                .description(meurtrier)
                .build();
        scenarioPriveRepository.save(scenarioPrive);
    }
}
