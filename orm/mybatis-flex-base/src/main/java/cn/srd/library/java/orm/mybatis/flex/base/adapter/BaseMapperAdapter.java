// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.adapter;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.mybatis.flex.base.dao.GenericCurdDao;
import cn.srd.library.java.tool.lang.annotation.Annotations;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Types;
import cn.srd.library.java.tool.lang.text.Strings;
import cn.srd.library.java.tool.spring.contract.Classes;
import cn.srd.library.java.tool.spring.contract.Springs;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.BaseMapper;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.SmartInitializingSingleton;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wjm
 * @since 2023-12-18 23:34
 */
public class BaseMapperAdapter<P extends PO, D extends GenericCurdDao<P>, B extends BaseMapper<P>> implements SmartInitializingSingleton {

    private final Map<String, Class<P>> daoClassNameMappingPOClassMap = Collections.newConcurrentHashMap(256);

    private final Map<String, String> daoClassNameMappingTableNameMap = Collections.newConcurrentHashMap(256);

    private final Map<String, BaseMapper<P>> daoClassNameMappingBaseMapperMap = Collections.newConcurrentHashMap(256);

    private final Map<BaseMapper<P>, String> baseMapperMappingDaoClassNameMap = Collections.newConcurrentHashMap(256);

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
        daoClassNameMappingBaseMapperMap.putAll(baseMapperClassNameMappingBaseMapperClassMap.entrySet()
                .stream()
                .map(entry -> Collections.ofPair(genericCurdDaoClassNameMappingGenericCurdDaoClassMap.get(Strings.getMostSimilar(entry.getKey(), genericCurdDaoClassNameMappingGenericCurdDaoClassMap.keySet())).getName(), Springs.getBean(entry.getValue())))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue))
        );

        baseMapperMappingDaoClassNameMap.putAll(Collections.reverse(daoClassNameMappingBaseMapperMap));

        Set<String> daoClassNames = daoClassNameMappingBaseMapperMap.keySet();
        daoClassNameMappingTableNameMap.putAll(daoClassNames.stream()
                .map(daoClassName -> {
                    Class<?> poClass = Types.getClassGenericType(Classes.ofName(daoClassName));
                    Assert.of().setMessage("{}could not find the po class by dao class: [{}], please check if your dao is marked with po generic!", ModuleView.ORM_MYBATIS_SYSTEM, daoClassName)
                            .setThrowable(LibraryJavaInternalException.class)
                            .throwsIfNull(poClass);
                    String tableName = Annotations.getAnnotationValue(poClass, Table.class);
                    Assert.of().setMessage("{}could not find the table name by po class [{}], please check the annotation [{}] is marked!", ModuleView.ORM_MYBATIS_SYSTEM, poClass.getName(), Table.class.getName())
                            .setThrowable(LibraryJavaInternalException.class)
                            .throwsIfNull(tableName);
                    return Collections.ofPair(daoClassName, tableName);
                })
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue))
        );

        daoClassNameMappingPOClassMap.putAll(daoClassNames.stream()
                .map(daoClassName -> {
                    Class<?> poClass = Types.getClassGenericType(Classes.ofName(daoClassName));
                    Assert.of().setMessage("{}could not find the po class by dao class: [{}], please check if your dao is marked with po generic!", ModuleView.ORM_MYBATIS_SYSTEM, daoClassName)
                            .setThrowable(LibraryJavaInternalException.class)
                            .throwsIfNull(poClass);
                    return Collections.ofPair(daoClassName, poClass);
                })
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, entry -> (Class<P>) entry.getValue()))
        );
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    public BaseMapper<P> getBaseMapper(Class proxyGenericDaoClass) {
        return proxyGenericDaoClassMappingBaseMapperClassMap.computeIfAbsent(
                proxyGenericDaoClass,
                ignore -> getBaseMapper(Arrays.stream(proxyGenericDaoClass.getAnnotatedInterfaces()).findAny().orElseThrow().getType().getTypeName())
        );
    }

    public BaseMapper<P> getBaseMapper(String daoClassName) {
        BaseMapper<P> baseMapper = daoClassNameMappingBaseMapperMap.get(daoClassName);
        Assert.of().setMessage("{}could not find the base mapper by dao class name: [{}], please check!", ModuleView.ORM_MYBATIS_SYSTEM, daoClassName)
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(baseMapper);
        return baseMapper;
    }

    public String getTableName(Class<?> daoClass) {
        String daoClassName = getDaoClassName(daoClass);
        String tableName = daoClassNameMappingTableNameMap.get(daoClassName);
        Assert.of().setMessage("{}could not find the table name by dao name: [{}], please check!", ModuleView.ORM_MYBATIS_SYSTEM, daoClassName)
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(tableName);
        return tableName;
    }

    public Class<P> getPOClass(Class<?> daoClass) {
        String daoClassName = getDaoClassName(daoClass);
        Class<P> poClass = daoClassNameMappingPOClassMap.get(daoClassName);
        Assert.of().setMessage("{}could not find the table name by dao name: [{}], please check!", ModuleView.ORM_MYBATIS_SYSTEM, daoClassName)
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(poClass);
        return poClass;
    }

    public String getDaoClassName(Class<?> daoClass) {
        BaseMapper<P> baseMapper = proxyGenericDaoClassMappingBaseMapperClassMap.get(daoClass);
        Assert.of().setMessage("{}could not find the base mapper by dao class: [{}], please check!", ModuleView.ORM_MYBATIS_SYSTEM, daoClass.getName())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(baseMapper);
        String daoClassName = baseMapperMappingDaoClassNameMap.get(baseMapper);
        Assert.of().setMessage("{}could not find the dao class name by base mapper: [{}], please check!", ModuleView.ORM_MYBATIS_SYSTEM, baseMapper.getClass().getName())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(daoClassName);
        return daoClassName;
    }

}