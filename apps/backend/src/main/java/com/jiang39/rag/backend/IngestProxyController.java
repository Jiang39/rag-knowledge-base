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
        return ingestClient.health();
    }

    @PostMapping(value = "/split", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String split(@RequestBody String body) {
        return ingestClient.postJson("/ingest/split", body);
    }

    @PostMapping(value = "/embed", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String embed(@RequestBody String body) {
        return ingestClient.postJson("/ingest/embed", body);
    }

    @PostMapping(value = "/pipeline", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String pipeline(@RequestBody String body) {
        return ingestClient.postJson("/ingest/pipeline", body);
    }
}


