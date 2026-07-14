package com.farhan.backend.controller;

import com.farhan.backend.dto.PromptRequest;
import com.farhan.backend.dto.QueryResultResponse;
import com.farhan.backend.service.AiService;
import com.farhan.backend.service.DatabaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sql")
public class SqlController {

    private final AiService aiService;
    private final DatabaseService databaseService;

    public SqlController(AiService aiService,
                         DatabaseService databaseService) {
        this.aiService = aiService;
        this.databaseService = databaseService;
    }

    @PostMapping("/generate")
    public QueryResultResponse generate(@RequestBody PromptRequest request) {

        String sql = aiService.generateResponse(request.getPrompt());

        List<Map<String, Object>> result =
                databaseService.executeQuery(sql);

        return new QueryResultResponse(sql, result);
    }
}