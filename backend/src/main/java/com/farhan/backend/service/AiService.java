package com.farhan.backend.service;

import com.farhan.backend.dto.groq.GroqResponse;
import org.springframework.http.MediaType;
import com.farhan.backend.config.GroqConfig;
// import org.springframework.http.MediaType;
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
        message.put("content",
                """
                        You are an expert PostgreSQL SQL generator.

                        Rules:
                        1. Return ONLY SQL.
                        2. Do not explain.
                        3. Do not use markdown.
                        4. Do not use ```sql.
                        5. Return a single PostgreSQL query.

                        User Request:
                        """ + prompt);

        Map<String, Object> request = new HashMap<>();
        request.put("model", "llama-3.3-70b-versatile");
        request.put("messages", List.of(message));
        request.put("temperature", 0);

        GroqResponse response = restClient.post()
                .uri(groqConfig.getApiUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + groqConfig.getApiKey())
                .body(request)
                .retrieve()
                .body(GroqResponse.class);

        if (response == null
                || response.getChoices() == null
                || response.getChoices().isEmpty()) {

            throw new RuntimeException("No response received from Groq.");
        }

        String sql = response.getChoices()
                .get(0)
                .getMessage()
                .getContent();

        return cleanSql(sql);
    }

    private String cleanSql(String response) {

        return response
                .replace("```sql", "")
                .replace("```", "")
                .replace("Here is your SQL:", "")
                .trim();
    }
}