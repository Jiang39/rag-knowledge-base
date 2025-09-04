package com.jiang39.rag.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DbSmokeTest {

    static PostgreSQLContainer<?> pg = new PostgreSQLContainer<>("pgvector/pgvector:pg16")
            .withDatabaseName("ragdb")
            .withUsername("rag")
            .withPassword("ragpass");

    static {
        pg.start();
    }

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry r) {
        r.add("spring.datasource.url", () -> pg.getJdbcUrl());
        r.add("spring.datasource.username", () -> pg.getUsername());
        r.add("spring.datasource.password", () -> pg.getPassword());
    }

    @Test
    void containerShouldStart() {
        assertThat(pg.isRunning()).isTrue();
    }
}


