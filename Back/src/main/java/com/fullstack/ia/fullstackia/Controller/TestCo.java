package com.fullstack.ia.fullstackia.Controller;

import com.fullstack.ia.fullstackia.DTO.QuestionDTO;
import com.fullstack.ia.fullstackia.DTO.ScenarioDTO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class TestCo {

    @PostMapping("/test")
    public QuestionDTO genererScenario(@RequestBody QuestionDTO questionDTO) {
        System.out.println(questionDTO);
        return questionDTO ;
    }

}
