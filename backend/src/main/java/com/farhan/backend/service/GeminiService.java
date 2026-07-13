package com.farhan.backend.service;
import com.farhan.backend.dto.gemini.GeminiResponse;
import com.farhan.backend.config.GeminiConfig;
import com.farhan.backend.dto.gemini.Content;
import com.farhan.backend.dto.gemini.GeminiRequest;
import com.farhan.backend.dto.gemini.Part;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class GeminiService {

    private final GeminiConfig geminiConfig;
    private final RestClient restClient;

    public GeminiService(GeminiConfig geminiConfig,
                         RestClient restClient) {
        this.geminiConfig = geminiConfig;
        this.restClient = restClient;
    }

    public GeminiRequest buildRequest(String prompt) {

        Part part = new Part(prompt);

        Content content = new Content(List.of(part));

        return new GeminiRequest(List.of(content));
    }
    public GeminiResponse callGemini(GeminiRequest request) {

    return restClient.post()
            .uri(geminiConfig.getApiUrl())
            .header("x-goog-api-key", geminiConfig.getApiKey())
            .header("Content-Type", "application/json")
            .body(request)
            .retrieve()
            .body(GeminiResponse.class);
}

    public String getApiKey() {
        return geminiConfig.getApiKey();
    }
}