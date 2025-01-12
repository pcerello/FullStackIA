package com.fullstack.ia.fullstackia.Controller;
import com.fullstack.ia.fullstackia.Entity.PersonnageEntity;
import com.fullstack.ia.fullstackia.Entity.TemoignageEntity;
import com.fullstack.ia.fullstackia.Entity.VictimeEntity;
import com.fullstack.ia.fullstackia.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/model")
@RequiredArgsConstructor
public class OllamaController {

    private final FileReadingService fileReadingService;
    private final OllamaChatModel chatModel;
    private final PersonnageService personnageService;
    private final VictimeService victimeService;
    private final TemoignageService temoignageService;
    private final PreuveService preuveService;
    private final ScenarioService scenarioService;


    @PostMapping(path = "/generateHistory")
    public String askDoraAQuestion(@RequestParam String question) {

        String prompt = fileReadingService.readInternalFileAsString("prompts/prompt.txt") ;

        List<Message> messages = new ArrayList<>();
        messages.add(new SystemMessage("<start_of_turn>" + prompt + "<end_of_turn>")) ;
        messages.add(new UserMessage("<start_of_turn>" + question + "<end_of_turn>")) ;

        Prompt promptToSend = new Prompt(messages);
        Flux<ChatResponse> chatResponses = chatModel.stream(promptToSend);
        String message = Objects.requireNonNull(chatResponses.collectList().block()).stream()
                .map(response -> response.getResult().getOutput().getContent())
                .collect(Collectors.joining("")) ;

        return message ;
    }

    //Méthode qui permet de remplir la BDD
    @PostMapping("/insertion")
    public void insertionDonneeDepuisJson(@RequestParam String filePath) {
        try {
             personnageService.creerPersonnageDepuisJson(filePath);
             victimeService.creerVictimeDepuisJson(filePath);
             temoignageService.creerTemoignageDepuisJson(filePath);
             preuveService.creerPreuveDepuisJson(filePath);
             scenarioService.creerScenarioDepuisJson(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'importation du personnage : " + e.getMessage(), e);
        }
    }






}