package cn.sugar.redisson.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedissonConfig {

    private Integer database;
    private String host;
    private String password;
    private Integer port;
    private Integer timeout;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + getHost() + ":" + getPort())
                .setPassword(getPassword())
                .setDatabase(getDatabase())
                .setConnectTimeout(getTimeout())
                .setConnectionPoolSize(100);
        return Redisson.create(config);
    }

}