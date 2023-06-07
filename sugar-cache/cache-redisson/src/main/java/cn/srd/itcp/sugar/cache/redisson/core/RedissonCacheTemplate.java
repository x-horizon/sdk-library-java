package cn.srd.itcp.sugar.cache.redisson.core;

import cn.srd.itcp.sugar.cache.contract.core.CapableExpirationCacheTemplate;
import cn.srd.itcp.sugar.tool.core.time.DurationWrapper;
import cn.srd.itcp.sugar.tool.core.time.TimeUtil;

import java.util.concurrent.TimeUnit;

/**
 * Redisson Cache Template
 *
 * @author wjm
 * @since 2023-06-05 16:41:28
 */
public interface RedissonCacheTemplate extends CapableExpirationCacheTemplate<String> {

    /**
     * 模糊查询某个命名空间的关键字
     */
    String NAMESPACE_KEY_WORD = ":*";

    /**
     * redisson expire time or ttl =&gt; {@link DurationWrapper}
     *
     * @param time redisson expire time or ttl
     * @return {@link DurationWrapper}
     */
    default DurationWrapper toDurationWrapper(long time) {
        return TimeUtil.toDurationWrapper(TimeUtil.wrapper(time, TimeUnit.MILLISECONDS).toMillisecond());
    }

}
