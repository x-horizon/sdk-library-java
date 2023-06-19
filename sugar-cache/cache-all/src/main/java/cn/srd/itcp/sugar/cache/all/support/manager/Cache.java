package cn.srd.itcp.sugar.cache.all.support.manager;

import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.NullValueUtil;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.itcp.sugar.tool.core.object.Objects;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.support.NullValue;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * cache implement {@link CacheTemplate}, support multilevel cache operation
 *
 * @author wjm
 * @since 2023-06-07 16:48:52
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
    private final boolean allowNullValue;

    @Override
    public <V> void set(String key, V value) {
        Object finalValue = NullValueUtil.convertNullToNullValueIfNeed(value, allowNullValue);
        if (Objects.isNotNull(finalValue)) {
            List<String> cacheComponentTypeNames = dataManager.getCacheComponentTypeNames();
            for (int index = cacheComponentTypeNames.size() - 1; index >= 0; index--) {
                CacheTemplate<String> cacheTemplate = dataManager.getTemplate(cacheComponentTypeNames.get(index));
                cacheTemplate.set(cacheTemplate.resolveKey(key, namespace), finalValue);
            }
        }
    }

    @Override
    public Object get(String key) {
        List<String> cacheComponentTypeNames = dataManager.getCacheComponentTypeNames();
        CacheTemplate<String> cacheTemplate = dataManager.getTemplate(CollectionsUtil.getFirst(cacheComponentTypeNames));
        Object value = cacheTemplate.get(cacheTemplate.resolveKey(key, namespace));
        if (cacheComponentTypeNames.size() == 1 || Objects.isNotNull(value)) {
            return value;
        }
        synchronized (dataManager) {
            for (int findCacheComponentTypeNameIndex = 1; findCacheComponentTypeNameIndex < cacheComponentTypeNames.size(); findCacheComponentTypeNameIndex++) {
                value = dataManager.getCacheComponentTypeMap()
                        .get(cacheComponentTypeNames.get(findCacheComponentTypeNameIndex))
                        .getStrategy()
                        .get(dataManager, namespace, key, findCacheComponentTypeNameIndex);
            }
        }
        return value;
    }

    @Override
    public <V> Map<String, V> getMapByNamespace(String namespace) {
        List<String> cacheComponentTypeNames = dataManager.getCacheComponentTypeNames();
        CacheTemplate<String> cacheTemplate = dataManager.getTemplate(CollectionsUtil.getFirst(cacheComponentTypeNames));
        Map<String, V> values = cacheTemplate.getMapByNamespace(namespace);
        if (cacheComponentTypeNames.size() == 1 || Objects.isNotEmpty(values)) {
            return filterNullValueIfNeed(values);
        }
        synchronized (dataManager) {
            for (int findCacheComponentTypeNameIndex = 1; findCacheComponentTypeNameIndex < cacheComponentTypeNames.size(); findCacheComponentTypeNameIndex++) {
                values = dataManager.getCacheComponentTypeMap()
                        .get(cacheComponentTypeNames.get(findCacheComponentTypeNameIndex))
                        .getStrategy()
                        .getMapByNamespace(dataManager, namespace, findCacheComponentTypeNameIndex);
            }
        }
        return filterNullValueIfNeed(values);
    }

    @Override
    public void delete(String key) {
        List<String> cacheComponentTypeNames = dataManager.getCacheComponentTypeNames();
        for (int index = cacheComponentTypeNames.size() - 1; index >= 0; index--) {
            CacheTemplate<String> cacheTemplate = dataManager.getTemplate(cacheComponentTypeNames.get(index));
            cacheTemplate.delete(cacheTemplate.resolveKey(key, namespace));
        }
    }

    @Override
    public long deleteAll(String namespace) {
        List<String> cacheComponentTypeNames = dataManager.getCacheComponentTypeNames();
        for (int index = cacheComponentTypeNames.size() - 1; index >= 0; index--) {
            CacheTemplate<String> cacheTemplate = dataManager.getTemplate(cacheComponentTypeNames.get(index));
            cacheTemplate.deleteAll(namespace);
        }
        // not implement affected number, ignore the return value
        return -1;
    }

    /**
     * remove the value is {@link NullValue} in map if allow null value
     *
     * @param values the cache value
     * @param <V>    the cache value type
     * @return after remove the value is {@link NullValue} in map if allow null value
     */
    public <V> Map<String, V> filterNullValueIfNeed(Map<String, V> values) {
        if (allowNullValue) {
            return values.entrySet().stream().filter(entry -> NullValueUtil.isNotNullValue(entry.getValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        return values;
    }

}
