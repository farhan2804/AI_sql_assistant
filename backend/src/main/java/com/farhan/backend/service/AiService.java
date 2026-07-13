package com.farhan.backend.service;

import com.farhan.backend.config.GroqConfig;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AiService {

    private final GroqConfig groqConfig;
    private final RestClient restClient;

    public AiService(GroqConfig groqConfig, RestClient restClient) {
        this.groqConfig = groqConfig;
        this.restClient = restClient;
    }

    public String generateResponse(String prompt) {

        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        Map<String, Object> request = new HashMap<>();
        request.put("model", "llama-3.3-70b-versatile");
        request.put("messages", List.of(message));
        request.put("temperature", 0);

        return restClient.post()
                .uri(groqConfig.getApiUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + groqConfig.getApiKey())
                .body(request)
                .retrieve()
                .body(String.class);
    }
}