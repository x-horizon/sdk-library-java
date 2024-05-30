package cn.srd.library.java.cache.all.manager;

import cn.srd.library.java.cache.contract.CacheTemplate;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.spring.contract.NullValues;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.support.NullValue;

import java.util.List;
import java.util.Map;

/**
 * cache implement {@link CacheTemplate}, support multilevel cache operation
 *
 * @author wjm
 * @since 2023-06-07 16:48
 */
@Slf4j
@Data
@SuperBuilder(toBuilder = true)
public class Cache implements CacheTemplate<String> {

    /**
     * the cache namespace, one namespace represents one {@link Cache} instance;
     */
    private final String namespace;

    /**
     * the actual cache data manager
     */
    private final CacheDataManager dataManager;

    /**
     * allow or not to set a {@link NullValue} in cache
     */
    private final boolean allowEmptyValue;

    @Override
    public <V> void set(String key, V value) {
        Object finalValue = NullValues.convertNullToNullValueIfNeed(value, allowEmptyValue);
        if (Nil.isNotNull(finalValue)) {
            List<String> cacheTypeNames = dataManager.getCacheTypeNames();
            for (int index = cacheTypeNames.size() - 1; index >= 0; index--) {
                CacheTemplate<String> cacheTemplate = dataManager.getTemplate(cacheTypeNames.get(index));
                cacheTemplate.set(cacheTemplate.resolveKey(key, namespace), finalValue);
            }
        }
    }

    @Override
    public Object get(String key) {
        List<String> cacheTypeNames = dataManager.getCacheTypeNames();
        CacheTemplate<String> cacheTemplate = dataManager.getTemplate(Collections.getFirst(cacheTypeNames).orElseThrow());
        Object value = cacheTemplate.get(cacheTemplate.resolveKey(key, namespace));
        if (cacheTypeNames.size() == 1 || Nil.isNotNull(value)) {
            return value;
        }
        synchronized (dataManager) {
            for (int findCacheTypeNameIndex = 1; findCacheTypeNameIndex < cacheTypeNames.size(); findCacheTypeNameIndex++) {
                value = dataManager.getCacheTypeMap()
                        .get(cacheTypeNames.get(findCacheTypeNameIndex))
                        .getStrategy()
                        .get(dataManager, namespace, key, findCacheTypeNameIndex);
            }
        }
        return value;
    }

    @Override
    public <V> Map<String, V> getMapByNamespace(String namespace) {
        List<String> cacheTypeNames = dataManager.getCacheTypeNames();
        CacheTemplate<String> cacheTemplate = dataManager.getTemplate(Collections.getFirst(cacheTypeNames).orElseThrow());
        Map<String, V> values = cacheTemplate.getMapByNamespace(namespace);
        if (cacheTypeNames.size() == 1 || Nil.isNotEmpty(values)) {
            return values;
        }
        synchronized (dataManager) {
            for (int findCacheTypeNameIndex = 1; findCacheTypeNameIndex < cacheTypeNames.size(); findCacheTypeNameIndex++) {
                values = dataManager.getCacheTypeMap()
                        .get(cacheTypeNames.get(findCacheTypeNameIndex))
                        .getStrategy()
                        .getMapByNamespace(dataManager, namespace, findCacheTypeNameIndex);
            }
        }
        return values;
    }

    @Override
    public <V> Map<String, V> getMapByNamespaceWithoutNullValue(String namespace) {
        List<String> cacheTypeNames = dataManager.getCacheTypeNames();
        CacheTemplate<String> cacheTemplate = dataManager.getTemplate(Collections.getFirst(cacheTypeNames).orElseThrow());
        Map<String, V> values = cacheTemplate.getMapByNamespaceWithoutNullValue(namespace);
        if (cacheTypeNames.size() == 1 || Nil.isNotEmpty(values)) {
            return values;
        }
        synchronized (dataManager) {
            for (int findCacheTypeNameIndex = 1; findCacheTypeNameIndex < cacheTypeNames.size(); findCacheTypeNameIndex++) {
                values = dataManager.getCacheTypeMap()
                        .get(cacheTypeNames.get(findCacheTypeNameIndex))
                        .getStrategy()
                        .getMapByNamespaceWithoutNullValue(dataManager, namespace, findCacheTypeNameIndex);
            }
        }
        return values;
    }

    @Override
    public void delete(String key) {
        List<String> cacheTypeNames = dataManager.getCacheTypeNames();
        for (int index = cacheTypeNames.size() - 1; index >= 0; index--) {
            CacheTemplate<String> cacheTemplate = dataManager.getTemplate(cacheTypeNames.get(index));
            cacheTemplate.delete(cacheTemplate.resolveKey(key, namespace));
        }
    }

    @Override
    public long deleteAll(String namespace) {
        List<String> cacheTypeNames = dataManager.getCacheTypeNames();
        for (int index = cacheTypeNames.size() - 1; index >= 0; index--) {
            CacheTemplate<String> cacheTemplate = dataManager.getTemplate(cacheTypeNames.get(index));
            cacheTemplate.deleteAll(namespace);
        }
        // not implement affected number, ignore the return value
        return -1;
    }

}