package cn.srd.library.java.cache.redis.autoconfigue;

import cn.srd.library.java.contract.properties.CacheRedisProperties;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Cache Redis TODO wjm 未解决存对象入 redis 后乱码的问题：使用 JsonJacksonCodec，序列化相关注解重写 serializeWithType，序列化成功，但 redis 依然乱码，且反序列化从 redis 取数据失败
 *
 * @author wjm
 * @since 2023-06-13 09:24:19
 */
@AutoConfigureBefore(RedissonAutoConfiguration.class)
@EnableConfigurationProperties(CacheRedisProperties.class)
public class RedisCacheAutoConfigurer {

    // private static final String REDIS_PROTOCOL_PREFIX = "redis://";
    //
    // private static final int DEFAULT_TIMEOUT = 10000;
    //
    // @Bean
    // public RedissonClient redisson() {
    //     RedisProperties redisProperties = Springs.getBean(RedisCacheProperties.class).getBaseInfo();
    //     Config config = new Config();
    //     config.setCodec(new JsonJacksonCodec());
    //     config.useSingleServer()
    //             .setAddress(REDIS_PROTOCOL_PREFIX + redisProperties.getHost() + ":" + redisProperties.getPort())
    //             .setConnectTimeout(Nil.isNull(redisProperties.getTimeout()) ? DEFAULT_TIMEOUT : ((int) redisProperties.getTimeout().toMillis()))
    //             .setDatabase(redisProperties.getDatabase())
    //             .setUsername(redisProperties.getUsername())
    //             .setPassword(redisProperties.getPassword());
    //     return Redisson.create(config);
    // }

}
