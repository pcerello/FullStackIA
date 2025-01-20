package com.fullstack.ia.fullstackia.Controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/")
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
    public String askCreateCharacters() throws IOException {

        String fileContent = mainIaAskingFunction("/prompts/promptCreateCharacteres.txt");

        int start = fileContent.indexOf("[");
        int end = fileContent.indexOf("]");

//        if (start < fileContent.length() && end <= fileContent.length() && start < end) {
        String substring = fileContent.substring(start, end + 1);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(substring);
        String name = jsonNode.get("name").asText();
        int age = jsonNode.get("age").asInt();
        return "Name : " + name + " Age : " + age ;
//        } else {
//            return "Les indices sont invalides.";
//        }

    }

}