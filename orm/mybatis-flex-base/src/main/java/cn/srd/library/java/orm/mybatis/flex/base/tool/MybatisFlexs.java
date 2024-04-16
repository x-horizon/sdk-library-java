// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.tool;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.tool.lang.collection.Collections;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.mybatis.Mappers;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryTable;
import com.mybatisflex.core.table.TableDef;
import com.mybatisflex.core.table.TableInfo;
import com.mybatisflex.core.table.TableInfoFactory;
import com.mybatisflex.core.util.ClassUtil;
import com.mybatisflex.core.util.LambdaUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Optional;

/**
 * @author wjm
 * @since 2023-11-27 22:21
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MybatisFlexs {

    private static final Map<Class<? extends PO>, TableDef> ENTITY_CLASS_MAPPING_TABLE_DEF_MAP = Collections.newConcurrentHashMap();

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

    // public static <P extends PO> Optional<String> getPrimaryKeyFieldName(Class<P> entityClass) {
    //     getPrimaryKeyFieldNames(entityClass);
    //     Assert.of()
    //             .setMessage("")
    //             .setThrowable(LibraryJavaInternalException.class)
    //             .throwsIfTrue(Collections.hasMoreThanOneElement(primaryKeyFieldNames));
    //     return Optional.ofNullable(getTableInfo(entityClass).getPrimaryColumns());
    // }

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

    public static <P extends PO> QueryColumn getQueryColumn(ColumnValueGetter<P> columnValueGetter) {
        return LambdaUtil.getQueryColumn(columnValueGetter);
    }

    public static <P extends PO> QueryTable getQueryTable(Class<P> entityClass) {
        TableInfo tableInfo = getTableInfo(entityClass);
        return new QueryTable(tableInfo.getSchema(), tableInfo.getTableName());
    }

    // TODO wjm mybatis-flex 升级引起的报错，暂未用到以下函数，待移除
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