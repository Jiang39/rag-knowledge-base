package com.jiang39.rag.backend;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * ingest 服务客户端。
 * 负责向 Python ingest 服务转发 HTTP 请求（health/split/embed/pipeline）。
 * 通过配置项 ingest.base-url 指定服务地址。
 */
@Component
public class IngestClient {

    /** ingest 服务基础地址，例如 http://localhost:8001 */
    @Value("${ingest.base-url:http://localhost:8001}")
    private String baseUrl;

    /** Spring 注入的 RestTemplate，用于发起 HTTP 请求 */
    @Resource
    private RestTemplate restTemplate;

    /**
     * 调用 ingest /health。
     * @return JSON 字符串
     */
    public String health() {
        return restTemplate.getForObject(baseUrl + "/health", String.class);
    }

    /**
     * 通用的 POST JSON 请求。
     * @param path 相对路径（例如 "/ingest/split"）
     * @param jsonBody JSON 字符串
     * @return 响应体字符串
     */
    public String postJson(String path, String jsonBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
        return restTemplate.postForObject(baseUrl + path, entity, String.class);
    }
}


