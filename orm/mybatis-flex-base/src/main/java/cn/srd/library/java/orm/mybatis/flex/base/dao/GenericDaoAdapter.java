// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.dao;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.convert.Converts;
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

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    private static final Map<Class<? extends GenericDao>, Class<? extends BaseMapper>> BASE_MAPPER_MAPPING_GENERIC_DAO_MAP = Collections.newConcurrentHashMap(256);

    @Override
    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    public void afterSingletonsInstantiated() {
        Map<String, BaseMapper> baseMapperClassNameMappingBaseMapperClassMap = Classes.scanBySuper(BaseMapper.class)
                .stream()
                .map(baseMapperClass -> {
                    BaseMapper baseMapper = Springs.getBean(baseMapperClass);
                    return Collections.ofPair(Classes.getClassSimpleName(baseMapperClass), baseMapper);
                })
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
        List<String> baseMapperClassNames = Converts.toMapKeys(baseMapperClassNameMappingBaseMapperClassMap);

        Map<String, Class<? extends GenericDao>> genericDaoClassNameMappingGenericDaoClassMap = Classes.scanBySuper(GenericDao.class)
                .stream()
                .map(genericDaoClass -> Collections.ofPair(Classes.getClassSimpleName(genericDaoClass), genericDaoClass))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));

        // genericDaoClassNameMappingGenericDaoClassMap.forEach((genericDaoClassName, genericDaoClass) -> BASE_MAPPER_MAPPING_GENERIC_DAO_MAP.put(genericDaoClassNameMappingGenericDaoClassMap.get(genericDaoClassName), baseMapperClassNameMappingBaseMapperClassMap.get(Strings.getMostSimilar(genericDaoClassName, baseMapperClassNames))));
    }

    // public static BaseMapper toBaseMapper(Class<? extends GenericDao<? extends PO>> genericDaoClass) {
    //     return MybatisFlexs.BASE_MAPPER_MAPPING_GENERIC_DAO_MAP.get(genericDaoClass);
    // }

}