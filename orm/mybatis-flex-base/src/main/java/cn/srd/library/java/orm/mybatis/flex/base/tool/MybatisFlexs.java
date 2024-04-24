// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.tool;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
import cn.srd.library.java.tool.spring.contract.Classes;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.mybatis.Mappers;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryTable;
import com.mybatisflex.core.table.TableInfo;
import com.mybatisflex.core.table.TableInfoFactory;
import com.mybatisflex.core.util.ClassUtil;
import com.mybatisflex.core.util.LambdaUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

/**
 * @author wjm
 * @since 2023-11-27 22:21
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MybatisFlexs {

    private static final Map<String, String> CLASS_NAME_AND_FIELD_NAME_MAPPING_DATABASE_COLUMN_NAME_MAP = Collections.newConcurrentHashMap(256);

    public static <P> Class<P> getUsefulClass(Class<P> input) {
        return ClassUtil.getUsefulClass(input);
    }

    public static <P extends PO> TableInfo getTableInfo(P entity) {
        return getTableInfo(entity.getClass());
    }

    public static <P extends PO> TableInfo getTableInfo(Class<P> entityClass) {
        return TableInfoFactory.ofEntityClass(entityClass);
    }

    public static <P extends PO> Optional<String> getTableName(P entity) {
        return getTableName(entity.getClass());
    }

    public static <P extends PO> Optional<String> getTableName(Class<P> entity) {
        return Optional.ofNullable(getTableInfo(entity).getTableName());
    }

    public static <P extends PO> Optional<String> getVersionFieldName(P entity) {
        return getVersionFieldName(entity.getClass());
    }

    public static <P extends PO> Optional<String> getVersionFieldName(Class<P> entityClass) {
        return Optional.ofNullable(getTableInfo(entityClass).getVersionColumn());
    }

    public static <P extends PO> Optional<String[]> getPrimaryKeyFieldNames(P entity) {
        return getPrimaryKeyFieldNames(entity.getClass());
    }

    public static <P extends PO> Optional<String[]> getPrimaryKeyFieldNames(Class<P> entityClass) {
        return Optional.ofNullable(getTableInfo(entityClass).getPrimaryColumns());
    }

    public static <P extends PO> Object getPrimaryKeyValue(P entity) {
        return Collections.getFirst(getPrimaryKeyValues(entity)).orElseThrow();
    }

    public static <P extends PO> Object[] getPrimaryKeyValues(P entity) {
        return getTableInfo(entity.getClass()).buildPkSqlArgs(entity);
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public static <P extends PO> BaseMapper<P> getBaseMapper(P entity) {
        return (BaseMapper<P>) getBaseMapper(getUsefulClass(entity.getClass()));
    }

    public static <P extends PO> BaseMapper<P> getBaseMapper(Class<P> entityClass) {
        return Mappers.ofEntityClass(entityClass);
    }

    public static <P extends PO> QueryTable getQueryTable(Class<P> entityClass) {
        TableInfo tableInfo = getTableInfo(entityClass);
        return new QueryTable(tableInfo.getSchema(), tableInfo.getTableName());
    }

    public static <T> QueryColumn getQueryColumn(ColumnNameGetter<T> columnNameGetter) {
        try {
            return LambdaUtil.getQueryColumn(columnNameGetter);
        } catch (Exception ignore) {
        }
        return null;
    }

    public static <T> String getColumnName(ColumnNameGetter<T> columnNameGetter) {
        QueryColumn queryColumn = LambdaUtil.getQueryColumn(columnNameGetter);
        if (Nil.isNull(queryColumn)) {
            return getFieldName(columnNameGetter);
        }
        return queryColumn.getName();
    }

    // TODO wjm 后续优化 JsonProperty 为可插拔
    public static <T> String getFieldName(ColumnNameGetter<T> columnNameGetter) {
        String fieldName = LambdaUtil.getFieldName(columnNameGetter);
        Class<?> classOfField = LambdaUtil.getImplClass(columnNameGetter);
        String classNameAndFieldName = Strings.format("{}-{}", classOfField.getName(), fieldName);
        return CLASS_NAME_AND_FIELD_NAME_MAPPING_DATABASE_COLUMN_NAME_MAP.computeIfAbsent(classNameAndFieldName, ignore -> {
            Field field = Classes.getFieldDeep(classOfField, fieldName);
            Assert.of().setMessage("{}could not find the field by name [{}] and class [{}], please check!", ModuleView.ORM_MYBATIS_SYSTEM, fieldName, classOfField.getName())
                    .setThrowable(LibraryJavaInternalException.class)
                    .throwsIfNull(field);
            JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
            if (Nil.isNull(jsonProperty)) {
                return fieldName;
            }
            return Strings.underlineCase(jsonProperty.value());
        });
    }

    public static <T> Class<?> getClassOfColumn(ColumnNameGetter<T> columnNameGetter) {
        return LambdaUtil.getImplClass(columnNameGetter);
    }

    // TODO wjm mybatis-flex 升级引起的报错，暂未用到以下函数，待移除
    // private static final Map<Class<? extends PO>, TableDef> ENTITY_CLASS_MAPPING_TABLE_DEF_MAP = Collections.newConcurrentHashMap(256);
    // public static QueryTable getQueryTable(TableDef table) {
    //     return new QueryTable(table);
    // }
    //
    // public static <P extends PO> TableDef getTableDef(Class<P> entityClass) {
    //     return ENTITY_CLASS_MAPPING_TABLE_DEF_MAP.computeIfAbsent(entityClass, ignore -> {
    //         TableInfo tableInfo = getTableInfo(entityClass);
    //         return new TableDef(tableInfo.getSchema(), tableInfo.getTableName());
    //     });
    // }

}