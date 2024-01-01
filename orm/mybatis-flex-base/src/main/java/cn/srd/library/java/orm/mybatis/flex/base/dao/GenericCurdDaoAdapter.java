// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.dao;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.text.Strings;
import cn.srd.library.java.tool.spring.contract.Classes;
import cn.srd.library.java.tool.spring.contract.Springs;
import com.mybatisflex.core.BaseMapper;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.SmartInitializingSingleton;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wjm
 * @since 2023-12-18 23:34
 */
public class GenericCurdDaoAdapter<T extends PO, U extends GenericCurdDao<T>, R extends BaseMapper<T>> implements SmartInitializingSingleton {

    private final Map<String, BaseMapper<T>> genericCurdDaoClassMappingBaseMapperClassMap = Collections.newConcurrentHashMap(256);

    private final Map<Class<U>, BaseMapper<T>> proxyGenericDaoClassMappingBaseMapperClassMap = Collections.newConcurrentHashMap(256);

    @Getter private static GenericCurdDaoAdapter<?, ?, ?> instance = null;

    @PostConstruct
    public void initialize() {
        instance = this;
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    public void afterSingletonsInstantiated() {
        Map<String, Class<U>> genericCurdDaoClassNameMappingGenericCurdDaoClassMap = Classes.scanBySuper(GenericCurdDao.class)
                .stream()
                .map(genericCurdDaoClass -> Collections.ofPair(genericCurdDaoClass.getSimpleName(), genericCurdDaoClass))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, entry -> (Class<U>) entry.getValue()));

        Map<String, Class<R>> baseMapperClassNameMappingBaseMapperClassMap = Classes.scanBySuper(BaseMapper.class)
                .stream()
                .map(baseMapperClass -> Collections.ofPair(baseMapperClass.getSimpleName(), baseMapperClass))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, entry -> (Class<R>) entry.getValue()));

        genericCurdDaoClassMappingBaseMapperClassMap.putAll(baseMapperClassNameMappingBaseMapperClassMap.entrySet()
                .stream()
                .map(entry -> Collections.ofPair(genericCurdDaoClassNameMappingGenericCurdDaoClassMap.get(Strings.getMostSimilar(entry.getKey(), genericCurdDaoClassNameMappingGenericCurdDaoClassMap.keySet())).getName(), Springs.getBean(entry.getValue())))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue))
        );
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    public BaseMapper<T> getBaseMapper(Class proxyGenericDaoClass) {
        return proxyGenericDaoClassMappingBaseMapperClassMap.computeIfAbsent(
                proxyGenericDaoClass,
                ignore -> getBaseMapper(Arrays.stream(proxyGenericDaoClass.getAnnotatedInterfaces()).findAny().orElseThrow().getType().getTypeName())
        );
    }

    public BaseMapper<T> getBaseMapper(String genericDaoClassName) {
        return genericCurdDaoClassMappingBaseMapperClassMap.get(genericDaoClassName);
    }

}