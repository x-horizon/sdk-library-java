package org.horizon.sdk.library.java.tool.rate.limiting.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

/**
 * @author wjm
 * @since 2025-08-09 00:47
 */
@Service
public class DemoService {

    @SneakyThrows
    @SentinelResource(value = "rate_info", blockHandler = "exceptionHandler")
    public void rate() {
        Thread.sleep(3000);
    }

    public void exceptionHandler() {
        throw new RuntimeException();
    }

}