package com.jiang39.rag.backend;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IngestClient {

    @Value("${ingest.base-url:http://localhost:8001}")
    private String baseUrl;

    @Resource
    private RestTemplate restTemplate;

    public String health() {
        return restTemplate.getForObject(baseUrl + "/health", String.class);
    }

    public String postJson(String path, String jsonBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
        return restTemplate.postForObject(baseUrl + path, entity, String.class);
    }
}


