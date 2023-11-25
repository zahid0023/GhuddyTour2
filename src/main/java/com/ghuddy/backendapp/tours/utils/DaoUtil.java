package com.ghuddy.backendapp.tours.utils;

import lombok.experimental.UtilityClass;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@UtilityClass
public class DaoUtil {
    public static List<Map<String, Object>> executeQuery(String query, JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.queryForList(query);
    }
}
