package com.farhan.backend.controller;

import com.farhan.backend.dto.PromptRequest;
import com.farhan.backend.service.AiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sql")
public class SqlController {

    private final AiService aiService;

    public SqlController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/generate")
    public String generate(@RequestBody PromptRequest request) {

        return aiService.generateResponse(request.getPrompt());

    }
}