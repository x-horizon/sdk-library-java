package cn.srd.itcp.sugar.cache.all.support.manager;

import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.NullValueUtil;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import lombok.Data;
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
    private final boolean allowNullValueInCache;

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
        int cacheTypeSize = cacheTypeNames.size();
        if (cacheTypeSize == 1) {
            CacheTemplate<String> cacheTemplate = dataManager.getTemplate(CollectionsUtil.getFirst(cacheTypeNames));
            return cacheTemplate.get(cacheTemplate.resolveKey(key, namespace));
        }

        for (int getIndex = 0; getIndex < cacheTypeSize; getIndex++) {
            if (getIndex == 0) {
                CacheTemplate<String> cacheTemplate = dataManager.getTemplate(CollectionsUtil.getFirst(cacheTypeNames));
                value = cacheTemplate.get(cacheTemplate.resolveKey(key, namespace));
                if (Objects.isNotNull(value)) {
                    return value;
                }
            }
            synchronized (dataManager) {
                CacheTemplate<String> cacheTemplate = dataManager.getTemplate(cacheTypeNames.get(getIndex));
                value = cacheTemplate.get(cacheTemplate.resolveKey(key, namespace));
                if (Objects.isNotNull(value)) {
                    for (int setIndex = getIndex - 1; setIndex >= 0; setIndex--) {
                        cacheTemplate = dataManager.getTemplate(cacheTypeNames.get(setIndex));
                        cacheTemplate.set(cacheTemplate.resolveKey(key, namespace), value);
                    }
                    break;
                }
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
