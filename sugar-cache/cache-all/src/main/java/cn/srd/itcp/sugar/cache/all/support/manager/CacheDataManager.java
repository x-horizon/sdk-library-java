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
 * @author wjm
 * @since 2023-06-06 16:14:13
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheDataManager {

    private final List<String> cacheTypeNames = new ArrayList<>();

    private final Map<String, CacheTemplate<String>> cacheTemplateMap = new HashMap<>();

    public static CacheDataManager build(List<CacheType> cacheTypes) {
        CacheDataManager cacheDataManager = new CacheDataManager();
        Map<String, List<Integer>> duplicateCacheTypeMap = CollectionsUtil.toMap(
                CollectionsUtil.groupBy(cacheTypes, CacheType::name).entrySet(),
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
            String cacheTypeName = cacheType.name() + CollectionsUtil.getFirst(allocatedCounts);
            allocatedCounts.remove(0);
            duplicateCacheTypeMap.put(cacheType.name(), allocatedCounts);
            cacheDataManager.cacheTypeNames.add(cacheTypeName);
            cacheDataManager.cacheTemplateMap.put(cacheTypeName, CacheType.CACHE_TEMPLATE_SUPPLIER.get(cacheType).get());
        }
        return cacheDataManager;
    }

    public CacheTemplate<String> getTemplate(String cacheTypeName) {
        return cacheTemplateMap.get(cacheTypeName);
    }

}


