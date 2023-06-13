package cn.srd.itcp.sugar.cache.all.support.strategy;

import cn.srd.itcp.sugar.cache.all.support.manager.CacheDataManager;
import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;
import cn.srd.itcp.sugar.tool.core.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * the local cache mode strategy implement
 *
 * @author wjm
 * @since 2023-06-12 20:49:21
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheModeLocalStrategy implements CacheModeStrategy {

    /**
     * the singleton instance
     */
    private static final CacheModeLocalStrategy INSTANCE = new CacheModeLocalStrategy();

    /**
     * get singleton instance
     *
     * @return singleton instance
     */
    public static CacheModeLocalStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public Object get(CacheDataManager dataManager, String namespace, String key, int findCacheTypeNameIndex) {
        CacheTemplate<String> cacheTemplate = dataManager.getTemplate(dataManager.getCacheTypeNames().get(findCacheTypeNameIndex));
        String finalKey = cacheTemplate.resolveKey(key, namespace);
        Object value = cacheTemplate.get(finalKey);
        if (Objects.isNotNull(value)) {
            for (int writeIndex = findCacheTypeNameIndex - 1; writeIndex >= 0; writeIndex--) {
                cacheTemplate = dataManager.getTemplate(dataManager.getCacheTypeNames().get(writeIndex));
                cacheTemplate.set(finalKey, value);
            }
        }
        return value;
    }

}


