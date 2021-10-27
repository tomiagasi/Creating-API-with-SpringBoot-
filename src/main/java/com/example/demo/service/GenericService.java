package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GenericService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findUsingJDBC(String query) {
        return jdbcTemplate.queryForList(query);
    }
}
