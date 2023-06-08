package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.NullValueUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author wjm
 * @since 2023-06-07 16:48:52
 */
@Slf4j
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Cache implements CacheTemplate<String> {

    private String namespace;

    private List<CacheType> cacheTypes;

    private boolean enablePreventCachePenetrate;

    @Override
    public <V> void set(String key, V value) {
        Object doValue = NullValueUtil.convertNullToNullValueIfNeed(value, enablePreventCachePenetrate);
        if (Objects.isNotNull(value)) {
            for (int i = cacheTypes.size() - 1; i >= 0; i--) {
                CacheTemplate<String> cacheTemplate = cacheTypes.get(i).getTemplate();
                cacheTemplate.set(key, doValue);
            }
        }
    }

    @Override
    public Object get(String key) {
        Object value = null;
        int findIndex = cacheTypes.size();
        for (int i = 0; i < cacheTypes.size(); i++) {
            CacheTemplate<String> cacheTemplate = cacheTypes.get(i).getTemplate();
            value = cacheTemplate.get(cacheTemplate.resolveKey(key, namespace));
            if (Objects.isNotNull(value)) {
                findIndex = i;
                break;
            }
        }
        value = NullValueUtil.convertNullToNullValueIfNeed(value, enablePreventCachePenetrate);
        if (Objects.isNotNull(value)) {
            for (int i = findIndex - 1; i >= 0; i--) {
                CacheTemplate<String> cacheTemplate = cacheTypes.get(i).getTemplate();
                cacheTemplate.set(cacheTemplate.resolveKey(key, namespace), value);
            }
        }
        return value;
    }

    @Override
    public void delete(String key) {
        for (int i = cacheTypes.size() - 1; i >= 0; i--) {
            CacheTemplate<String> cacheTemplate = cacheTypes.get(i).getTemplate();
            cacheTemplate.delete(key);
        }
    }

}
