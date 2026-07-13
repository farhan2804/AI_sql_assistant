package com.farhan.backend.service;

import org.springframework.stereotype.Service;

@Service
public class SqlService {

    public String generateSql(String prompt) {

        // Dummy response for now
        return "SELECT * FROM employees;";
    }
}