package com.farhan.backend.controller;

import com.farhan.backend.dto.PromptRequest;
// import com.farhan.backend.dto.SqlResponse;
import com.farhan.backend.dto.gemini.GeminiRequest;
import com.farhan.backend.service.GeminiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sql")
public class SqlController {

    private final GeminiService geminiService;

    public SqlController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/generate")
    public GeminiRequest generateSql(@RequestBody PromptRequest request) {

        return geminiService.buildRequest(request.getPrompt());
    }
}