package org.horizon.sdk.library.java.cache.all.strategy;

import org.horizon.sdk.library.java.cache.all.manager.CacheDataManager;
import org.horizon.sdk.library.java.cache.contract.CacheTemplate;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.text.Strings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * the local cache type strategy implement
 *
 * @author wjm
 * @since 2023-06-12 20:49
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheTypeLocalStrategy implements CacheTypeStrategy {

    /**
     * the singleton instance
     */
    private static final CacheTypeLocalStrategy INSTANCE = new CacheTypeLocalStrategy();

    /**
     * get singleton instance
     *
     * @return singleton instance
     */
    public static CacheTypeLocalStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public Object get(CacheDataManager dataManager, String namespace, String key, int findCacheTypeNameIndex) {
        CacheTemplate<String> cacheTemplate = dataManager.getTemplate(dataManager.getCacheTypeNames().get(findCacheTypeNameIndex));
        Object value = cacheTemplate.get(cacheTemplate.resolveKey(key, namespace));
        if (Nil.isNotNull(value)) {
            for (int writeIndex = findCacheTypeNameIndex - 1; writeIndex >= 0; writeIndex--) {
                cacheTemplate = dataManager.getTemplate(dataManager.getCacheTypeNames().get(writeIndex));
                cacheTemplate.set(cacheTemplate.resolveKey(key, namespace), value);
            }
        }
        return value;
    }

    @Override
    public <V> Map<String, V> getMapByNamespace(CacheDataManager dataManager, String namespace, int findCacheTypeNameIndex) {
        CacheTemplate<String> cacheTemplate = dataManager.getTemplate(dataManager.getCacheTypeNames().get(findCacheTypeNameIndex));
        Map<String, V> values = cacheTemplate.getMapByNamespace(namespace);
        fillCache(dataManager, findCacheTypeNameIndex, values);
        return values;
    }

    @Override
    public <V> Map<String, V> getMapByNamespaceWithoutNullValue(CacheDataManager dataManager, String namespace, int findCacheTypeNameIndex) {
        CacheTemplate<String> cacheTemplate = dataManager.getTemplate(dataManager.getCacheTypeNames().get(findCacheTypeNameIndex));
        Map<String, V> values = cacheTemplate.getMapByNamespaceWithoutNullValue(namespace);
        fillCache(dataManager, findCacheTypeNameIndex, values);
        return values;
    }

    /**
     * fill cache
     *
     * @param dataManager            see {@link CacheDataManager}
     * @param findCacheTypeNameIndex current find cache type name index
     * @param values                 the data source
     * @param <V>                    the cache value type
     */
    public <V> void fillCache(CacheDataManager dataManager, int findCacheTypeNameIndex, Map<String, V> values) {
        if (Nil.isNotEmpty(values)) {
            for (int writeIndex = findCacheTypeNameIndex - 1; writeIndex >= 0; writeIndex--) {
                CacheTemplate<String> cacheTemplate = dataManager.getTemplate(dataManager.getCacheTypeNames().get(writeIndex));
                for (Map.Entry<String, V> value : values.entrySet()) {
                    cacheTemplate.set(resolveKey(value.getKey()), value.getValue());
                }
            }
        }
    }

    /**
     * resolve the key in local cache, example: "my-cache:test:1" =&gt; "1"
     *
     * @param key the cache key
     * @return the cache key after resolve
     */
    public String resolveKey(String key) {
        return Strings.containsAny(key, SymbolConstant.COLON) ? Strings.subAfter(key, SymbolConstant.COLON) : key;
    }

}