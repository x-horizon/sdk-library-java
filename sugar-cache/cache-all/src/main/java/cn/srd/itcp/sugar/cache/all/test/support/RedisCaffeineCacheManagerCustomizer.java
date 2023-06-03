package cn.srd.itcp.sugar.cache.all.test.support;

/**
 * 修改 {@link RedisCaffeineCacheManager} 的回调
 */
@FunctionalInterface
public interface RedisCaffeineCacheManagerCustomizer {

    /**
     * 修改 {@link RedisCaffeineCacheManager}
     *
     * @param cacheManager cacheManager
     */
    void customize(RedisCaffeineCacheManager cacheManager);

}
