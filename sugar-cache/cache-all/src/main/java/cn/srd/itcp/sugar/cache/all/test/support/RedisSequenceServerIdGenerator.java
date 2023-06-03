package cn.srd.itcp.sugar.cache.all.test.support;

import cn.srd.itcp.sugar.cache.all.test.properties.CacheConfigProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * 通过redis key生成serverId
 */
@Lazy
@Configuration(proxyBeanMethods = false)
@ConditionalOnMissingBean(ServerIdGenerator.class)
public class RedisSequenceServerIdGenerator implements ServerIdGenerator {

    protected final CacheConfigProperties properties;

    public RedisSequenceServerIdGenerator(CacheConfigProperties properties) {
        this.properties = properties;
    }

    @Override
    public Object get() {
        // TODO wjm 待完善
        return null;
        // return stringKeyRedisTemplate.opsForValue().increment(properties.getRedis().getServerIdGeneratorKey());
    }

}
