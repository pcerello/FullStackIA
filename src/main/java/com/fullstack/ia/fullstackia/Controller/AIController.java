package com.fullstack.ia.fullstackia.Controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AIController {
    private final FileReadingService fileReadingService;
    private final OllamaChatModel chatModel;
    private final PersonnageService personnageService;
    private final VictimeService victimeService;
    private final TemoignageService temoignageService;
    private final PreuveService preuveService;
    private final ScenarioService scenarioService;

    @PostMapping(path = "/generateHistory")
    public String askDoraAQuestion(@RequestParam String question) {
        /*try {*/
            String prompt = fileReadingService.readInternalFileAsString("prompts/prompt.txt");

            List<Message> messages = new ArrayList<>();
            messages.add(new SystemMessage("<start_of_turn>" + prompt + "<end_of_turn>"));
            messages.add(new UserMessage("<start_of_turn>" + question + "<end_of_turn>"));

            Prompt promptToSend = new Prompt(messages);
            Flux<ChatResponse> chatResponses = chatModel.stream(promptToSend);
            String message = Objects.requireNonNull(chatResponses.collectList().block())
                    .stream()
                    .map(response -> response.getResult().getOutput().getContent())
                    .collect(Collectors.joining(""));

            // Analyse du JSON généré par l'IA
            /*ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(message);

            // Extraire et traiter le scénario
            JsonNode scenarioNode = rootNode.get("scenario");
            if (scenarioNode != null) {
                scenarioService.creerScenarioDepuisJson(scenarioNode.toString());
            }

            // Extraire et traiter les personnages
            JsonNode personnagesNode = rootNode.get("personnages");
            if (personnagesNode != null && personnagesNode.isArray()) {
                for (JsonNode personnageNode : personnagesNode) {
                    personnageService.creerPersonnageDepuisJson(personnageNode.toString());
                }
            }

            // Extraire et traiter la victime
            JsonNode victimeNode = rootNode.get("victime");
            if (victimeNode != null) {
                victimeService.creerVictimeDepuisJson(victimeNode.toString());
            }

            // Extraire et traiter les témoignages
            JsonNode temoignagesNode = rootNode.get("temoignages");
            if (temoignagesNode != null && temoignagesNode.isArray()) {
                for (JsonNode temoignageNode : temoignagesNode) {
                    temoignageService.creerTemoignageDepuisJson(temoignageNode.toString());
                }
            }

            // Extraire et traiter les preuves
            JsonNode preuvesNode = rootNode.get("preuves");
            if (preuvesNode != null && preuvesNode.isArray()) {
                for (JsonNode preuveNode : preuvesNode) {
                    preuveService.creerPreuveDepuisJson(preuveNode.toString());
                }
            }*/

            return "Données générées et insérées avec succès." + message;
        /*} catch (IOException e) {
            e.printStackTrace();
            return "Erreur lors de l'analyse de la réponse JSON : " + e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur inattendue : " + e.getMessage();
        }*/
    }

}
