package com.farhan.backend.controller;

import com.farhan.backend.dto.PromptRequest;
import com.farhan.backend.dto.SqlResponse;
import com.farhan.backend.service.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sql")
public class SqlController {

    @Autowired
    private SqlService sqlService;

    @PostMapping("/generate")
    public SqlResponse generateSql(@RequestBody PromptRequest request) {

        String sql = sqlService.generateSql(request.getPrompt());

        return new SqlResponse(sql);
    }
}