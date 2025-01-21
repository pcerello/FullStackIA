package com.fullstack.ia.fullstackia.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AIService {
    private final FileReadingService fileReadingService;
    private final OllamaChatModel chatModel;
    public String appelOllama(String question, String prompt) {
        try {
            List<Message> messages = new ArrayList<>();
            messages.add(new SystemMessage(prompt));
            messages.add(new UserMessage(question));

            Prompt promptToSend = new Prompt(messages);
            Flux<ChatResponse> chatResponses = chatModel.stream(promptToSend);
            String response = Objects.requireNonNull(chatResponses.collectList().block())
                    .stream()
                    .map(chatResponse -> chatResponse.getResult().getOutput().getContent())
                    .collect(Collectors.joining(""));

            return response;

        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du scénario : " + e.getMessage();
        }
    }

}

