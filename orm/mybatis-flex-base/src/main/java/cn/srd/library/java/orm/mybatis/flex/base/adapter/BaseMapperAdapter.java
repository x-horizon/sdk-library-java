// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.adapter;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.mybatis.flex.base.dao.GenericCurdDao;
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
public class BaseMapperAdapter<P extends PO, D extends GenericCurdDao<P>, B extends BaseMapper<P>> implements SmartInitializingSingleton {

    private final Map<String, BaseMapper<P>> genericCurdDaoClassNameMappingBaseMapperClassMap = Collections.newConcurrentHashMap(256);

    private final Map<Class<D>, BaseMapper<P>> proxyGenericDaoClassMappingBaseMapperClassMap = Collections.newConcurrentHashMap(256);

    @Getter private static BaseMapperAdapter<?, ?, ?> instance = null;

    @PostConstruct
    public void initialize() {
        instance = this;
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @Override
    public void afterSingletonsInstantiated() {
        Map<String, Class<D>> genericCurdDaoClassNameMappingGenericCurdDaoClassMap = Classes.scanBySuper(GenericCurdDao.class)
                .stream()
                .map(genericCurdDaoClass -> Collections.ofPair(genericCurdDaoClass.getSimpleName(), genericCurdDaoClass))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, entry -> (Class<D>) entry.getValue()));

        Map<String, Class<B>> baseMapperClassNameMappingBaseMapperClassMap = Classes.scanBySuper(BaseMapper.class)
                .stream()
                .map(baseMapperClass -> Collections.ofPair(baseMapperClass.getSimpleName(), baseMapperClass))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, entry -> (Class<B>) entry.getValue()));

        genericCurdDaoClassNameMappingBaseMapperClassMap.putAll(baseMapperClassNameMappingBaseMapperClassMap.entrySet()
                .stream()
                .map(entry -> Collections.ofPair(genericCurdDaoClassNameMappingGenericCurdDaoClassMap.get(Strings.getMostSimilar(entry.getKey(), genericCurdDaoClassNameMappingGenericCurdDaoClassMap.keySet())).getName(), Springs.getBean(entry.getValue())))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue))
        );
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    public BaseMapper<P> getBaseMapper(Class proxyGenericDaoClass) {
        return proxyGenericDaoClassMappingBaseMapperClassMap.computeIfAbsent(
                proxyGenericDaoClass,
                ignore -> getBaseMapper(Arrays.stream(proxyGenericDaoClass.getAnnotatedInterfaces()).findAny().orElseThrow().getType().getTypeName())
        );
    }

    public BaseMapper<P> getBaseMapper(String genericDaoClassName) {
        return genericCurdDaoClassNameMappingBaseMapperClassMap.get(genericDaoClassName);
    }

}