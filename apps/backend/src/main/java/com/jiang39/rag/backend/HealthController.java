package com.jiang39.rag.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查控制器。
 * 提供最小化的后端可用性探测接口，供前端/监控调用。
 */
@RestController
public class HealthController {
    /**
     * 健康检查端点。
     * @return 固定字符串 "OK" 表示应用存活
     */
    @GetMapping("/api/health")
    public String health() {
        return "OK";
    }
}
