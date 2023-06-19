package cn.srd.itcp.sugar.cache.all.support.manager;

import cn.srd.itcp.sugar.cache.contract.core.CacheTemplate;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * the cache data manager
 *
 * @author wjm
 * @since 2023-06-06 16:14:13
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheDataManager {

    /**
     * all cache type names in a {@link Cache#getNamespace()}
     */
    private final List<String> cacheComponentTypeNames = new ArrayList<>();

    /**
     * the cache type name mapping {@link CacheComponentType} map
     */
    private final Map<String, CacheComponentType> cacheComponentTypeMap = new HashMap<>();

    /**
     * the cache type name mapping {@link CacheTemplate} map
     */
    private final Map<String, CacheTemplate<String>> cacheTemplateMap = new HashMap<>();

    /**
     * build {@link CacheDataManager}
     * <pre>
     * for same cache type, it will use the cache type name and duplicate count and increase from 1,
     * for example: the cache type is {@link CacheComponentType#MAP}, {@link CacheComponentType#CAFFEINE}, {@link CacheComponentType#CAFFEINE}, {@link CacheComponentType#MAP}, {@link CacheComponentType#CAFFEINE}, {@link CacheComponentType#MAP}, {@link CacheComponentType#REDIS},
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
     * @param cacheComponentTypes see {@link CacheComponentType}
     * @return {@link CacheDataManager} instance
     */
    public static CacheDataManager build(List<CacheComponentType> cacheComponentTypes) {
        CacheDataManager cacheDataManager = new CacheDataManager();
        Map<String, List<Integer>> duplicateCacheComponentTypeMap = CollectionsUtil.toMap(
                CollectionsUtil.groupBy(cacheComponentTypes, CacheComponentType::name).entrySet(),
                Map.Entry::getKey,
                entry -> {
                    List<Integer> allocatedCounts = new ArrayList<>();
                    for (int index = 1; index <= entry.getValue().size(); index++) {
                        allocatedCounts.add(index);
                    }
                    return allocatedCounts;
                }
        );
        for (CacheComponentType cacheComponentType : cacheComponentTypes) {
            List<Integer> allocatedCounts = duplicateCacheComponentTypeMap.get(cacheComponentType.name());
            String cacheComponentTypeName = cacheComponentType.name() + CollectionsUtil.getFirst(allocatedCounts);
            allocatedCounts.remove(0);
            duplicateCacheComponentTypeMap.put(cacheComponentType.name(), allocatedCounts);
            cacheDataManager.cacheComponentTypeNames.add(cacheComponentTypeName);
            cacheDataManager.cacheComponentTypeMap.put(cacheComponentTypeName, cacheComponentType);
            cacheDataManager.cacheTemplateMap.put(cacheComponentTypeName, CacheComponentType.CACHE_TEMPLATE_SUPPLIER.get(cacheComponentType).get());
        }
        return cacheDataManager;
    }

    /**
     * get {@link CacheTemplate} by {@link CacheComponentType} name
     *
     * @param cacheComponentTypeName {@link CacheComponentType} name
     * @return {@link CacheTemplate} instance
     */
    public CacheTemplate<String> getTemplate(String cacheComponentTypeName) {
        return cacheTemplateMap.get(cacheComponentTypeName);
    }

}


