package cn.srd.itcp.sugar.cache.all.support.manager;

import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.NullValueUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.support.NullValue;

import java.util.List;

/**
 * cache implement {@link CacheTemplate}, support multilevel cache operation
 *
 * @author wjm
 * @since 2023-06-07 16:48:52
 */
@Slf4j
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Cache implements CacheTemplate<String> {

    /**
     * the cache namespace, one namespace represents one {@link Cache} instance;
     */
    private String namespace;

    /**
     * the actual cache data manager
     */
    private CacheDataManager dataManager;

    /**
     * allow or not to set a {@link NullValue} in cache
     */
    private boolean allowNullValueInCache;

    @Override
    public <V> void set(String key, V value) {
        Object doValue = NullValueUtil.convertNullToNullValueIfNeed(value, allowNullValueInCache);
        if (Objects.isNotNull(value)) {
            List<String> cacheTypeNames = dataManager.getCacheTypeNames();
            for (int index = cacheTypeNames.size() - 1; index >= 0; index--) {
                CacheTemplate<String> cacheTemplate = dataManager.getTemplate(cacheTypeNames.get(index));
                cacheTemplate.set(cacheTemplate.resolveKey(key, namespace), doValue);
            }
        }
    }

    @Override
    public Object get(String key) {
        Object value = null;
        List<String> cacheTypeNames = dataManager.getCacheTypeNames();
        int findIndex = cacheTypeNames.size();
        int cacheTypeSize = cacheTypeNames.size();
        for (int index = 0; index < cacheTypeSize; index++) {
            CacheTemplate<String> cacheTemplate = dataManager.getTemplate(cacheTypeNames.get(index));
            value = cacheTemplate.get(cacheTemplate.resolveKey(key, namespace));
            if (Objects.isNotNull(value)) {
                findIndex = index;
                break;
            }
        }
        if (Objects.isNotNull(value)) {
            for (int index = findIndex - 1; index >= 0; index--) {
                CacheTemplate<String> cacheTemplate = dataManager.getTemplate(cacheTypeNames.get(index));
                cacheTemplate.set(cacheTemplate.resolveKey(key, namespace), value);
            }
        }
        return value;
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
