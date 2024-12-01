package com.fullstack.ia.fullstackia.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/huggingface")
public class huggingfaceController {

    @PostMapping(path="/test")

    public static void main(String[] args) {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.example.com")
                .build();

        String jsonBody = "{\"key\":\"value\"}";
        String token = "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";

        // Envoi de la requête POST avec le token dans le header
        Mono<String> response = webClient.post()
                .uri("/data")
                .header("Authorization", token) // Ajout du token ici
                .header("Content-Type", "application/json")
                .bodyValue(jsonBody)
                .retrieve()
                .bodyToMono(String.class);

        // Affichage de la réponse
        response.subscribe(System.out::println);
    }


}
