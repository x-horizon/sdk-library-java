package org.horizon.sdk.library.java.cache.all.manager;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.cache.all.model.enums.CacheType;
import org.horizon.sdk.library.java.cache.contract.CacheTemplate;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * the cache data manager
 *
 * @author wjm
 * @since 2023-06-06 16:14
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheDataManager {

    /**
     * all cache type names in a {@link Cache#getNamespace()}
     */
    private final List<String> cacheTypeNames = new ArrayList<>();

    /**
     * the cache type name mapping {@link CacheType} map
     */
    private final Map<String, CacheType> cacheTypeMap = new HashMap<>();

    /**
     * the cache type name mapping {@link CacheTemplate} map
     */
    private final Map<String, CacheTemplate<String>> cacheTemplateMap = new HashMap<>();

    /**
     * build {@link CacheDataManager}
     * <pre>
     * for same cache type, it will use the cache type name and duplicate count and increase from 1,
     * for example: the cache type is {@link CacheType#MAP}, {@link CacheType#CAFFEINE}, {@link CacheType#CAFFEINE}, {@link CacheType#MAP}, {@link CacheType#CAFFEINE}, {@link CacheType#MAP}, {@link CacheType#REDIS},
     * it will generate following cache type names:
     * MAP1
     * CAFFEINE1
     * CAFFEINE2
     * MAP2
     * CAFFEINE3
     * MAP3
     * REDIS1
     * </pre>
     *
     * @param cacheTypes see {@link CacheType}
     * @return {@link CacheDataManager} instance
     */
    public static CacheDataManager build(List<CacheType> cacheTypes) {
        CacheDataManager cacheDataManager = new CacheDataManager();
        Map<String, List<Integer>> duplicateCacheTypeMap = Converts.toHashMap(
                Collections.groupBy(cacheTypes, CacheType::name).entrySet(),
                Map.Entry::getKey,
                entry -> {
                    List<Integer> allocatedCounts = new ArrayList<>();
                    for (int index = 1; index <= entry.getValue().size(); index++) {
                        allocatedCounts.add(index);
                    }
                    return allocatedCounts;
                }
        );
        for (CacheType cacheType : cacheTypes) {
            List<Integer> allocatedCounts = duplicateCacheTypeMap.get(cacheType.name());
            String cacheTypeName = cacheType.name() + Collections.getFirst(allocatedCounts);
            allocatedCounts.remove(0);
            duplicateCacheTypeMap.put(cacheType.name(), allocatedCounts);
            cacheDataManager.cacheTypeNames.add(cacheTypeName);
            cacheDataManager.cacheTypeMap.put(cacheTypeName, cacheType);
            cacheDataManager.cacheTemplateMap.put(cacheTypeName, CacheType.CACHE_TEMPLATE_SUPPLIER.get(cacheType).get());
        }
        return cacheDataManager;
    }

    /**
     * get {@link CacheTemplate} by {@link CacheType} name
     *
     * @param cacheTypeName {@link CacheType} name
     * @return {@link CacheTemplate} instance
     */
    public CacheTemplate<String> getTemplate(String cacheTypeName) {
        return cacheTemplateMap.get(cacheTypeName);
    }

}