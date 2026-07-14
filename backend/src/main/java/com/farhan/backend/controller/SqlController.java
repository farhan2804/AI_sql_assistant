package com.farhan.backend.controller;

import com.farhan.backend.dto.PromptRequest;
import com.farhan.backend.dto.SqlResponse;
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
    public SqlResponse generate(@RequestBody PromptRequest request) {

        String sql = aiService.generateResponse(request.getPrompt());

        return new SqlResponse(sql);
    }
}