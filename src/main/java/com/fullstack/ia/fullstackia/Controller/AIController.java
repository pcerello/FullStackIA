package com.fullstack.ia.fullstackia.Controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.ia.fullstackia.Entity.PersonnageEntity;
import com.fullstack.ia.fullstackia.Entity.ScenarioEntity;
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
        try {
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

            System.out.println("Contenu brut du message : " + message);

            // Nettoyer le message si nécessaire
            String cleanedMessage = message.trim();
            if (cleanedMessage.startsWith("<start_of_turn>") && cleanedMessage.endsWith("<end_of_turn>")) {
                cleanedMessage = cleanedMessage.substring("<start_of_turn>".length(), cleanedMessage.length() - "<end_of_turn>".length()).trim();
            }

            // Analyse du JSON généré par l'IA
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(cleanedMessage);

            // Extraire et traiter le scénario
            JsonNode scenarioNode = rootNode.get("scenario");
            if (scenarioNode != null) {
                scenarioService.creerScenarioDepuisJson(scenarioNode.toString());
            }

            // Extraire et traiter les personnages
            /*JsonNode personnagesNode = rootNode.get("personnages");
            if (personnagesNode != null && personnagesNode.isArray()) {
                for (JsonNode personnageNode : personnagesNode) {
                    personnageService.creerPersonnageDepuisJson(personnageNode.toString());
                }
            }*/

            // Extraire et traiter la victime
            /*JsonNode victimeNode = rootNode.get("victime");
            if (victimeNode != null) {
                victimeService.creerVictimeDepuisJson(victimeNode.toString());
            }*/

            // Extraire et traiter les témoignages
            /*JsonNode temoignagesNode = rootNode.get("temoignages");
            if (temoignagesNode != null && temoignagesNode.isArray()) {
                for (JsonNode temoignageNode : temoignagesNode) {
                    temoignageService.creerTemoignageDepuisJson(temoignageNode.toString());
                }
            }*/

            // Extraire et traiter les preuves
            /*JsonNode preuvesNode = rootNode.get("preuves");
            if (preuvesNode != null && preuvesNode.isArray()) {
                for (JsonNode preuveNode : preuvesNode) {
                    preuveService.creerPreuveDepuisJson(preuveNode.toString());
                }
            }*/

            return "Données générées et insérées avec succès.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Erreur lors de l'analyse de la réponse JSON : " + e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur inattendue : " + e.getMessage();
        }
    }

    @PostMapping("/generateScenario")
    public String generateScenario(@RequestParam String question) {
        try {
            // Lire le prompt simplifié depuis un fichier ou directement dans le code
            String prompt = fileReadingService.readInternalFileAsString("prompts/simple_prompt.txt");

            // Créer la liste des messages pour la requête
            List<Message> messages = new ArrayList<>();
            messages.add(new SystemMessage(prompt));
            messages.add(new UserMessage(question));

            // Envoyer la requête à l'IA
            Prompt promptToSend = new Prompt(messages);
            Flux<ChatResponse> chatResponses = chatModel.stream(promptToSend);
            String response = Objects.requireNonNull(chatResponses.collectList().block())
                    .stream()
                    .map(chatResponse -> chatResponse.getResult().getOutput().getContent())
                    .collect(Collectors.joining(""));

            // Sauvegarder le scénario généré (si applicable)
            scenarioService.saveGeneratedScenario(response);

            return "Scénario généré et sauvegardé : " + response;

        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du scénario : " + e.getMessage();
        }
    }

    @PostMapping(path = "/genererTemoignages")
    public String genererTemoignagesPourScenario(@RequestParam String question) {
        try {
            // Récupérer le dernier scénario en base
            ScenarioEntity lastScenario = scenarioService.getLastInsertedScenario();

            if (lastScenario == null) {
                return "Aucun scénario";
            }

            // Générer le prompt pour les témoignages et preuves
            String prompt = "Voici le scénario : \n" + lastScenario.getDescription() + "\n" +
                    "Générez un témoignage pour chaque suspect, chaque témoignage incluant une preuve associée." +
                    "Tu ne dois pas dépasser les 200 caractères";

            List<Message> messages = new ArrayList<>();
            messages.add(new SystemMessage("<start_of_turn>" + prompt + "<end_of_turn>"));
            messages.add(new UserMessage(question));

            Prompt promptToSend = new Prompt(messages);
            //System.out.println("le prompt envoye a l'ia :" + promptToSend);
            Flux<ChatResponse> chatResponses = chatModel.stream(promptToSend);
            String message = Objects.requireNonNull(chatResponses.collectList().block())
                    .stream()
                    .map(chatResponse -> chatResponse.getResult().getOutput().getContent())
                    .collect(Collectors.joining(""));
            //System.out.println("message : " + message);

            // Appel au service pour sauvegarder dans la base
            temoignageService.saveGeneratedTemoignages(message, lastScenario.getId());

            return "Témoignages générés et insérés avec succès." + message;

        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur : " + e.getMessage();
        }
    }

    @PostMapping(path = "/evaluateResponse")
    public String evaluateUserResponse(@RequestParam String userResponse) {
        try {
            // Récupérer le dernier scénario en base
            ScenarioEntity lastScenario = scenarioService.getLastInsertedScenario();
            if (lastScenario == null) {
                return "Aucun scénario disponible.";
            }

            // Récupérer les témoignages associés au dernier scénario
            List<TemoignageEntity> temoignages = temoignageService.getTemoignagesByScenarioId(lastScenario.getId());
            if (temoignages.isEmpty()) {
                return "Aucun témoignage disponible pour le scénario.";
            }

            String prompt = "Voici le contexte de l'enquête :\n" +
                    "Scénario : " + lastScenario.getDescription() + "\n" +
                    "Témoignages :\n";

            for (TemoignageEntity temoignage : temoignages) {
                prompt += "- " + temoignage.getDescription() + "\n";
            }

            prompt +="\n Vous allez recevoir la réponse de l'utilisateur qui tentera de résoudre le mystère de cette enquete \n"+
                    "Évaluez cette réponse en fonction du contexte et indiquez si elle est correcte, incorrecte ou partiellement correcte, en justifiant votre évaluation.";

            List<Message> messages = new ArrayList<>();
            messages.add(new SystemMessage("<start_of_turn>" + prompt + "<end_of_turn>"));
            messages.add(new UserMessage("<start_of_turn>" + userResponse + "<end_of_turn>"));

            Prompt promptToSendToAI = new Prompt(messages);

            // Appeler l'IA
            Flux<ChatResponse> chatResponses = chatModel.stream(promptToSendToAI)
                    .doOnError(error -> System.err.println("Erreur dans le flux : " + error.getMessage()));

            String evaluation = Objects.requireNonNull(chatResponses.collectList().block())
                    .stream()
                    .map(response -> response.getResult().getOutput().getContent())
                    .collect(Collectors.joining(""));

            // Retourner l'évaluation
            return "Évaluation de la réponse :\n" + evaluation;

        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur inattendue : " + e.getMessage();
        }
    }


}
