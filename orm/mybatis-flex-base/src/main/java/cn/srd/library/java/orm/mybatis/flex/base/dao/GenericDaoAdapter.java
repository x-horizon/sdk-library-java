// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.dao;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.text.Strings;
import cn.srd.library.java.tool.spring.contract.Classes;
import cn.srd.library.java.tool.spring.contract.Springs;
import com.mybatisflex.core.BaseMapper;
import org.springframework.beans.factory.SmartInitializingSingleton;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wjm
 * @since 2023-12-18 23:34
 */
public class GenericDaoAdapter implements SmartInitializingSingleton {

    private static final Map<GenericCurdDao<? extends PO>, BaseMapper<? extends PO>> GENERIC_DAO_MAPPING_BASE_MAPPER_MAP = Collections.newConcurrentHashMap(256);

    @Override
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public void afterSingletonsInstantiated() {
        Map<String, BaseMapper<? extends PO>> baseMapperClassNameMappingBaseMapperMap = Classes.scanBySuper(BaseMapper.class)
                .stream()
                .map(baseMapperClass -> Collections.ofPair(Classes.getClassSimpleName(baseMapperClass), Springs.getBean(baseMapperClass)))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
        Map<String, GenericCurdDao<? extends PO>> genericDaoClassNameMappingGenericCurdDaoMap = Classes.scanBySuper(GenericCurdDao.class)
                .stream()
                .map(genericDaoClass -> Collections.ofPair(Classes.getClassSimpleName(genericDaoClass), Springs.getBean(genericDaoClass)))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
        List<String> baseMapperClassNames = Converts.toMapKeys(baseMapperClassNameMappingBaseMapperMap);
        List<String> genericDaoClassNames = Converts.toMapKeys(genericDaoClassNameMappingGenericCurdDaoMap);
        GENERIC_DAO_MAPPING_BASE_MAPPER_MAP.putAll(genericDaoClassNames
                .stream()
                .map(genericDaoClassName -> {
                    String mostSimilarBaseMapperClassName = Strings.getMostSimilar(genericDaoClassName, baseMapperClassNames);
                    Collections.remove(baseMapperClassNames, mostSimilarBaseMapperClassName);
                    return Collections.ofPair(genericDaoClassName, mostSimilarBaseMapperClassName);
                })
                .map(entry -> Collections.ofPair(genericDaoClassNameMappingGenericCurdDaoMap.get(entry.getKey()), baseMapperClassNameMappingBaseMapperMap.get(entry.getValue())))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue))
        );
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public static <T extends PO> BaseMapper<T> getBaseMapper(GenericCurdDao<T> genericDao) {
        return (BaseMapper<T>) GENERIC_DAO_MAPPING_BASE_MAPPER_MAP.get(genericDao);
    }

}