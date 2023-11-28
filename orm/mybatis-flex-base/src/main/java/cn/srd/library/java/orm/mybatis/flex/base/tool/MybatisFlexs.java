// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.tool;

import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.tool.lang.collection.Collections;
import com.mybatisflex.core.table.TableInfo;
import com.mybatisflex.core.table.TableInfoFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * @author wjm
 * @since 2023-11-27 22:21
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MybatisFlexs {

    public static <T extends PO> TableInfo getTableInfo(T entity) {
        return getTableInfo(entity.getClass());
    }

    public static <T extends PO> TableInfo getTableInfo(Class<T> entityClass) {
        return TableInfoFactory.ofEntityClass(entityClass);
    }

    public static <T extends PO> Optional<String> getTableName(T entity) {
        return getTableName(entity.getClass());
    }

    public static <T extends PO> Optional<String> getTableName(Class<T> entity) {
        return Optional.ofNullable(getTableInfo(entity).getTableName());
    }

    public static <T extends PO> Optional<String> getVersionFieldName(T entity) {
        return getVersionFieldName(entity.getClass());
    }

    public static <T extends PO> Optional<String> getVersionFieldName(Class<T> entityClass) {
        return Optional.ofNullable(getTableInfo(entityClass).getVersionColumn());
    }

    // public static <T extends PO> Optional<String> getPrimaryKeyFieldName(Class<T> entityClass) {
    //     getPrimaryKeyFieldNames(entityClass);
    //     Assert.of()
    //             .setMessage("")
    //             .setThrowable(LibraryJavaInternalException.class)
    //             .throwsIfTrue(Collections.hasMoreThanOneElement(primaryKeyFieldNames));
    //     return Optional.ofNullable(getTableInfo(entityClass).getPrimaryColumns());
    // }

    public static <T extends PO> Optional<String[]> getPrimaryKeyFieldNames(T entity) {
        return getPrimaryKeyFieldNames(entity.getClass());
    }

    public static <T extends PO> Optional<String[]> getPrimaryKeyFieldNames(Class<T> entityClass) {
        return Optional.ofNullable(getTableInfo(entityClass).getPrimaryColumns());
    }

    public static <T extends PO> Object getPrimaryKeyValue(T entity) {
        return Collections.getFirst(getPrimaryKeyValues(entity)).orElseThrow();
    }

    public static <T extends PO> Object[] getPrimaryKeyValues(T entity) {
        return getTableInfo(entity.getClass()).buildPkSqlArgs(entity);
    }

}
