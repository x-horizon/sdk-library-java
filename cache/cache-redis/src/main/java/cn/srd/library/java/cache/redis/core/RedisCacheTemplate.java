package cn.srd.library.java.cache.redis.core;

import cn.srd.library.java.cache.contract.core.CapableExpirationCacheTemplate;
import cn.srd.library.java.tool.constant.core.StringPool;
import cn.srd.library.java.tool.lang.core.StringsUtil;
import cn.srd.library.java.tool.lang.core.time.DurationWrapper;
import cn.srd.library.java.tool.lang.core.time.TimeUtil;

import java.util.concurrent.TimeUnit;

/**
 * Redis Cache Template
 *
 * @author wjm
 * @since 2023-06-05 16:41:28
 */
public interface RedisCacheTemplate extends CapableExpirationCacheTemplate<String> {

    /**
     * 模糊查询某个命名空间的关键字
     */
    String NAMESPACE_KEY_WORD = "*";

    @Override
    default String resolveKey(String key, String extensionKey) {
        if (StringsUtil.endWith(extensionKey, StringPool.COLON)) {
            return extensionKey + key;
        }
        return extensionKey + StringPool.COLON + key;
    }

    /**
     * resolve fuzzy find, example: my-cache:*
     *
     * @param namespace the namespace
     * @return the fuzzy key
     */
    default String resolveFuzzyKey(String namespace) {
        if (StringsUtil.endWith(namespace, StringPool.COLON)) {
            return namespace + NAMESPACE_KEY_WORD;
        }
        return namespace + StringPool.COLON + NAMESPACE_KEY_WORD;
    }

    /**
     * redis expire time or ttl =&gt; {@link DurationWrapper}
     *
     * @param time redis expire time or ttl
     * @return {@link DurationWrapper}
     */
    default DurationWrapper toDurationWrapper(long time) {
        return TimeUtil.toDurationWrapper(TimeUtil.wrapper(time, TimeUnit.MILLISECONDS).toMillisecond());
    }

}
