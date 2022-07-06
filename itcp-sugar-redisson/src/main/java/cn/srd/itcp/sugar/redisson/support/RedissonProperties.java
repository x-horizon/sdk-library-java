package cn.srd.itcp.sugar.redisson.support;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.redis.redisson")
public class RedissonProperties {

    private Integer database;
    private String host;
    private String password;
    private Integer port;
    private Integer timeout;

}
