package com.jiang39.rag.backend;

import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ingest")
public class IngestProxyController {

    @Resource
    private IngestClient ingestClient;

    @GetMapping("/health")
    public String health() {
        // 代理转发到 ingest 的 /health，便于前端只对接后端域名
        return ingestClient.health();
    }

    @PostMapping(value = "/split", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String split(@RequestBody String body) {
        // 代理分块接口
        return ingestClient.postJson("/ingest/split", body);
    }

    @PostMapping(value = "/embed", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String embed(@RequestBody String body) {
        // 代理嵌入接口
        return ingestClient.postJson("/ingest/embed", body);
    }

    @PostMapping(value = "/pipeline", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String pipeline(@RequestBody String body) {
        // 代理流水线接口
        return ingestClient.postJson("/ingest/pipeline", body);
    }
}


