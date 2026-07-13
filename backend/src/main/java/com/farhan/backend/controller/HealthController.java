package com.farhan.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/api/health")
    public Map<String, String> health() {

        Map<String, String> response = new HashMap<>();

        response.put("status", "UP");
        response.put("message", "AI SQL Assistant Backend is running successfully");

        return response;
    }
}