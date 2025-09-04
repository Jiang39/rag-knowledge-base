package com.jiang39.rag.backend;

import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ingest 接口代理控制器。
 * 前端仅调用本控制器，由后端统一转发到 Python ingest 服务，便于权限/审计/限流与演进。
 */
@RestController
@RequestMapping("/api/ingest")
public class IngestProxyController {

    @Resource
    private IngestClient ingestClient;

    /**
     * 代理转发 /health。
     */
    @GetMapping("/health")
    public String health() {
        // 便于前端只对接后端域名
        return ingestClient.health();
    }

    /**
     * 代理分块接口。
     */
    @PostMapping(value = "/split", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String split(@RequestBody String body) {
        return ingestClient.postJson("/ingest/split", body);
    }

    /**
     * 代理嵌入接口。
     */
    @PostMapping(value = "/embed", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String embed(@RequestBody String body) {
        return ingestClient.postJson("/ingest/embed", body);
    }

    /**
     * 代理流水线接口。
     */
    @PostMapping(value = "/pipeline", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String pipeline(@RequestBody String body) {
        return ingestClient.postJson("/ingest/pipeline", body);
    }
}


