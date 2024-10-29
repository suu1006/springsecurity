package com.example.javamvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionListener implements ApplicationListener<ApplicationReadyEvent> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseConnectionListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            jdbcTemplate.execute("SELECT 1"); // 연결 테스트
            System.out.println("=============== 데이터베이스 연결 성공 ===============");
        } catch (Exception e) {
            System.out.println("=============== 데이터베이스 연결 실패 ===============");
            e.printStackTrace();
        }
    }
}
