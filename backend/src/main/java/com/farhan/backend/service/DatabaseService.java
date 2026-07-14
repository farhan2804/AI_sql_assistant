package com.farhan.backend.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DatabaseService {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> executeQuery(String sql) {

        sql = sql.replace(";", "").trim();

        System.out.println("Executing SQL: [" + sql + "]");

        if (!sql.toLowerCase().startsWith("select")) {
            throw new RuntimeException("Only SELECT queries are allowed.");
        }

        try {
            return jdbcTemplate.queryForList(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}