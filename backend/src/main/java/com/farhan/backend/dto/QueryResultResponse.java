package com.farhan.backend.dto;

import java.util.List;
import java.util.Map;

public class QueryResultResponse {

    private String sql;
    private List<Map<String, Object>> data;

    public QueryResultResponse(String sql, List<Map<String, Object>> data) {
        this.sql = sql;
        this.data = data;
    }

    public String getSql() {
        return sql;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }
}