// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.contract.base.cache;

import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Types;
import cn.srd.library.java.tool.spring.contract.Annotations;
import cn.srd.library.java.tool.spring.contract.Classes;
import org.apache.ibatis.type.TypeHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wjm
 * @since 2023-11-07 16:50
 */
public abstract class ColumnMappingRelationCache {

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    private final Map<String, Set<Class>> columnNameMappingJavaTypesMap =
            Classes.scanByBasePackagePath()
                    .stream()
                    .map(Classes::getFields)
                    .flatMap(Collection::stream)
                    .filter(field -> Annotations.hasAnnotation(field, getTypeHandlerLocatedAnnotation()))
                    .filter(field -> Classes.isAssignable(Annotations.getAnnotationValue(field, getTypeHandlerLocatedAnnotation(), getTypeHandlerLocatedAnnotationFieldName()), getJdbcAbstractTypeHandler()))
                    .map(field -> Collections.ofPair(Annotations.getAnnotationValue(field, getTypeHandlerLocatedAnnotation(), String.class), (Class) getTypeHandlerAnnotatedField(field)))
                    .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getKey, Collectors.mapping(AbstractMap.SimpleEntry::getValue, Collectors.toSet())));

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    protected abstract Class<? extends TypeHandler> getJdbcAbstractTypeHandler();

    protected abstract Class<? extends Annotation> getTypeHandlerLocatedAnnotation();

    protected Class<?> getTypeHandlerAnnotatedField(Field annotatedField) {
        return Types.hasGeneric(annotatedField) ? Types.getEmbedGenericTypeClass(annotatedField.getDeclaringClass(), annotatedField.getName()) : annotatedField.getType();
    }

    protected String getTypeHandlerLocatedAnnotationFieldName() {
        return "typeHandler";
    }

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    public Set<Class> getMappingJavaTypes(String columnName) {
        Set<Class> javaClasses = columnNameMappingJavaTypesMap.get(columnName);
        Assert.of().setMessage("{}could not find column mapping java class by column name [{}], please check!", ModuleView.ORM_MYBATIS_SYSTEM, columnName).throwsIfEmpty(javaClasses);
        return javaClasses;
    }

}
