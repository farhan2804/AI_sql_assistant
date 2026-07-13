package com.farhan.backend.dto;

public class SqlResponse {

    private String sql;

    public SqlResponse() {
    }

    public SqlResponse(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}