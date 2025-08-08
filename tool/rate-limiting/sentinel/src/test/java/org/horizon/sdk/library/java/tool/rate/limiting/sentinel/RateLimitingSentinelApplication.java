package org.horizon.sdk.library.java.tool.rate.limiting.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wjm
 * @since 2025-08-09 23:11
 */

@SpringBootApplication
public class RateLimitingSentinelApplication {

    static void main(String... args) {
        SpringApplication.run(RateLimitingSentinelApplication.class, args);
    }

}