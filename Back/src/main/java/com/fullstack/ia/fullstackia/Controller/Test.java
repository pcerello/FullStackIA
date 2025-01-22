package com.fullstack.ia.fullstackia.Controller;

import com.fullstack.ia.fullstackia.DTO.ScenarioDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @PostMapping(path="/test")
    public String test(@RequestBody ScenarioDTO dto) {
        System.out.println(dto);
        return dto.description();
    }


}
