package org.horizon.library.java.tool.thread.pool.contract;

import org.dromara.dynamictp.core.spring.EnableDynamicTp;
import org.dromara.dynamictp.core.support.DynamicTp;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Dynamic Thread Pool
 *
 * @author wjm
 * @since 2024-09-23 16:51
 */
@AutoConfiguration
@EnableDynamicTp
public class DynamicThreadPoolAutoConfigurer {

    @DynamicTp("threadPoolTaskExecutor")
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }

}