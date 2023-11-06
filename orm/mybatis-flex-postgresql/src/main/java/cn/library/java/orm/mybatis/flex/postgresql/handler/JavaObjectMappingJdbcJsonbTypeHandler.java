// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.flex.postgresql.handler;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.library.java.orm.mybatis.contract.postgresql.handler.JdbcJsonbObjectTypeHandler;
import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.tool.convert.jackson.NullableObject;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Classes;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.spring.contract.Annotations;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wjm
 * @since 2023-11-06 19:40
 */
public class JavaObjectMappingJdbcJsonbTypeHandler<T extends NullableObject> extends JdbcJsonbObjectTypeHandler<T> {

    private Map<String, Set<Class<T>>> databaseColumnNameMappingJavaClassMap;

    @Override
    protected Set<Class<T>> getJavaType(String columnName) {
        if (Nil.isNull(databaseColumnNameMappingJavaClassMap)) {
            initDatabaseColumnNameMappingJavaClassMap();
        }
        Set<Class<T>> javaClasses = databaseColumnNameMappingJavaClassMap.get(columnName);
        Assert.of().setMessage("cdscdcdc").throwsIfEmpty(javaClasses);
        return javaClasses;
    }

    @Override
    public Object convertToJdbcComplexObject(T javaObject) {
        return convertObjectToJsonb(javaObject.isNull() ? Map.of() : javaObject);
    }

    @SneakyThrows
    @Override
    public T convertToJavaObject(ResultSet resultSet, String columnName) {
        return convertJsonbStringToObject(resultSet.getString(columnName), getJavaType(columnName));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    private synchronized void initDatabaseColumnNameMappingJavaClassMap() {
        databaseColumnNameMappingJavaClassMap = Annotations.getAnnotationValueMappingAnnotatedClassMap(Table.class, String.class)
                .values()
                .stream()
                .map(Classes::getFields)
                .flatMap(Collection::stream)
                .filter(field -> AnnotationUtil.hasAnnotation(field, Column.class))
                .filter(field -> Comparators.equalsAny(AnnotationUtil.getAnnotationValue(field, Column.class, "typeHandler"), JavaObjectMappingJdbcJsonbTypeHandler.class))
                .map(field -> Collections.ofPair(Annotations.getAnnotationValue(field, Column.class, String.class), (Class<T>) field.getType()))
                .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getKey, Collectors.mapping(AbstractMap.SimpleEntry::getValue, Collectors.toSet())));
    }

}
