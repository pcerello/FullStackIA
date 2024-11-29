package com.fullstack.ia.fullstackia.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ollama")
public class OllamaController {

    // URL d'Ollama
    @Value("${ollama.url}")
    private String ollamaUrl;

    // Modèle à utiliser
    @Value("${spring.ai.ollama.chat.options.model}")
    private String model;

    // Création d'un test simple pour interroger Ollama
    @PostMapping("/generate-scenario")
    public String generateScenario(@RequestParam String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        // Corps de la requête avec le prompt
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", model);
        requestBody.put("prompt", prompt);

        // En-têtes HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        // Envoi de la requête
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(ollamaUrl, entity, Map.class);

        // Récupération de la réponse et retour au client
        Map responseBody = response.getBody();
        return responseBody != null ? (String) responseBody.get("response") : "Erreur dans la génération.";
    }
}
