package cn.srd.itcp.sugar.cache.all.support.strategy;

import cn.srd.itcp.sugar.cache.all.support.manager.CacheDataManager;
import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;
import cn.srd.itcp.sugar.tool.core.object.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

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
    public Object get(CacheDataManager dataManager, String namespace, String key, int findCacheComponentTypeNameIndex) {
        CacheTemplate<String> cacheTemplate = dataManager.getTemplate(dataManager.getCacheComponentTypeNames().get(findCacheComponentTypeNameIndex));
        String finalKey = cacheTemplate.resolveKey(key, namespace);
        Object value = cacheTemplate.get(finalKey);
        if (Objects.isNotNull(value)) {
            for (int writeIndex = findCacheComponentTypeNameIndex - 1; writeIndex >= 0; writeIndex--) {
                cacheTemplate = dataManager.getTemplate(dataManager.getCacheComponentTypeNames().get(writeIndex));
                cacheTemplate.set(finalKey, value);
            }
        }
        return value;
    }

    @Override
    public <V> Map<String, V> getMapByNamespace(CacheDataManager dataManager, String namespace, int findCacheComponentTypeNameIndex) {
        CacheTemplate<String> cacheTemplate = dataManager.getTemplate(dataManager.getCacheComponentTypeNames().get(findCacheComponentTypeNameIndex));
        Map<String, V> values = cacheTemplate.getMapByNamespace(namespace);
        if (Objects.isNotEmpty(values)) {
            for (int writeIndex = findCacheComponentTypeNameIndex - 1; writeIndex >= 0; writeIndex--) {
                cacheTemplate = dataManager.getTemplate(dataManager.getCacheComponentTypeNames().get(writeIndex));
                for (Map.Entry<String, V> value : values.entrySet()) {
                    cacheTemplate.set(value.getKey(), value.getValue());
                }
            }
        }
        return values;
    }

}


