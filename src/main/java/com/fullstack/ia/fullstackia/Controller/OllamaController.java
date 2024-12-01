package com.fullstack.ia.fullstackia.Controller;
import com.fullstack.ia.fullstackia.Service.FileReadingService;
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

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/model")
@RequiredArgsConstructor
public class OllamaController {

    private final FileReadingService fileReadingService;
    private final OllamaChatModel chatModel;

    // Ask Fonction
    public String mainIaAskingFunction(String fileSrc) {

        List<Message> messages = new ArrayList<>();

        String srcSetting = "/prompts/pre-settings.txt";
        String promptAsk = fileReadingService.readInternalFileAsString(fileSrc) ;
        messages.add(new SystemMessage("<start_of_turn>" + promptAsk + "<end_of_turn>")) ;

        String promptSetting = fileReadingService.readInternalFileAsString(srcSetting) ;
        messages.add(new UserMessage("<start_of_turn>" + promptSetting + "<end_of_turn>")) ;

        Prompt promptToSend = new Prompt(messages);
        Flux<ChatResponse> chatResponses = chatModel.stream(promptToSend);

        return Objects.requireNonNull(chatResponses.collectList().block()).stream()
                .map(response -> response.getResult().getOutput().getContent())
                .collect(Collectors.joining("")) ;

    }


    @PostMapping(path = "/create-scenario")
    public String askCreateScenario() {

        return mainIaAskingFunction("/prompts/promptCreateScenario.txt");

    }


    @PostMapping(path = "/create-characters")
    public String askCreateCharacters() {

        String unformatJson =  mainIaAskingFunction("/prompts/promptCreateCharacteres.txt");

        return unformatJson;

//        // Recuperer uniquement ce qui est dans la liste
//        int startList = unformatJson.indexOf("[");
//        int endList = unformatJson.indexOf("]");
//        String jsonArrayContent  = unformatJson.substring(startList + 1, endList).trim();
//        String[]  jsonElements = jsonArrayContent.split(",");
//        List<String> jsonContent = Arrays.asList(jsonElements);
//
//        return jsonContent.stream().collect(Collectors.joining(""));



//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/recordJson/characters.json"))) {
//            writer.write(jsonContent);
//            return "Le json a bien été enregistré";
//
//        } catch (IOException e) {
////            throw new RuntimeException(e);
//            return "Problème lors de l'enregistrement du Json";
//        }

    }


}