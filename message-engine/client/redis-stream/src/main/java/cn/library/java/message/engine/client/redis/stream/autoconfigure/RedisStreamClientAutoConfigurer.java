package cn.library.java.message.engine.client.redis.stream.autoconfigure;

import cn.library.java.message.engine.client.redis.stream.model.property.RedisStreamClientProperty;
import cn.library.java.message.engine.client.redis.stream.strategy.RedisStreamClientConfigStrategy;
import cn.library.java.message.engine.client.redis.stream.strategy.RedisStreamClientFlowStrategy;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Message Client Redis
 *
 * @author wjm
 * @since 2024-05-24 16:56
 */
@AutoConfiguration
@EnableConfigurationProperties(RedisStreamClientProperty.class)
public class RedisStreamClientAutoConfigurer {

    @Bean
    public RedisStreamClientConfigStrategy redisStreamConfigStrategy() {
        return new RedisStreamClientConfigStrategy();
    }

    @Bean
    public RedisStreamClientFlowStrategy redisStreamFlowStrategy() {
        return new RedisStreamClientFlowStrategy();
    }

}