// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.function;

import cn.srd.library.java.contract.component.database.postgresql.PostgresqlJsonbSQL;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.MybatisFlexs;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.RawQueryCondition;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wjm
 * @since 2024-04-19 11:01
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostgresqlFunctionQueryCondition {

    public static <P extends PO> QueryCondition listJsonbEmptyList(ColumnNameGetter<P> columnNameGetter) {
        return new RawQueryCondition(PostgresqlJsonbSQL.getEmptyListEqual(MybatisFlexs.getColumnName(columnNameGetter)));
    }

    public static <P extends PO, T extends Number> QueryCondition listJsonbListNumberEqual(ColumnNameGetter<P> columnNameGetter, T value) {
        return new RawQueryCondition(PostgresqlJsonbSQL.getListNumberEqual(MybatisFlexs.getColumnName(columnNameGetter), value));
    }

    public static <P extends PO, T extends Number> QueryCondition listJsonbListNumberIn(ColumnNameGetter<P> columnNameGetter, List<T> values) {
        return new RawQueryCondition(PostgresqlJsonbSQL.getListNumberIn(MybatisFlexs.getColumnName(columnNameGetter), values));
    }

    public static <P extends PO> QueryCondition listJsonbListStringEqual(ColumnNameGetter<P> columnNameGetter, String value) {
        return new RawQueryCondition(PostgresqlJsonbSQL.getListStringEqual(MybatisFlexs.getColumnName(columnNameGetter), value));
    }

    public static <P extends PO> QueryCondition listJsonbListStringIn(ColumnNameGetter<P> columnNameGetter, List<String> values) {
        return new RawQueryCondition(PostgresqlJsonbSQL.getListStringIn(MybatisFlexs.getColumnName(columnNameGetter), values));
    }

    public static <P extends PO> QueryCondition listJsonbListStringLike(ColumnNameGetter<P> columnNameGetter, String value) {
        return new RawQueryCondition(PostgresqlJsonbSQL.getListStringLike(MybatisFlexs.getColumnName(columnNameGetter), value));
    }

    public static <P extends PO, T extends Number> QueryCondition listJsonbListObjectKeyIdEqual(ColumnNameGetter<P> columnNameGetter, T value) {
        return new RawQueryCondition(PostgresqlJsonbSQL.getListObjectKeyIdEqual(MybatisFlexs.getColumnName(columnNameGetter), value));
    }

    public static <P extends PO, T extends Number> QueryCondition listJsonbListObjectKeyIdIn(ColumnNameGetter<P> columnNameGetter, List<T> values) {
        return new RawQueryCondition(PostgresqlJsonbSQL.getListObjectKeyIdIn(MybatisFlexs.getColumnName(columnNameGetter), values));
    }

    public static <P extends PO, T extends Number> QueryCondition listJsonbListObjectKeyTypeEqual(ColumnNameGetter<P> columnNameGetter, T value) {
        return new RawQueryCondition(PostgresqlJsonbSQL.getListObjectKeyTypeEqual(MybatisFlexs.getColumnName(columnNameGetter), value));
    }

    public static <P extends PO, T extends Number> QueryCondition listJsonbListObjectKeyTypeIn(ColumnNameGetter<P> columnNameGetter, List<T> values) {
        return new RawQueryCondition(PostgresqlJsonbSQL.getListObjectKeyTypeIn(MybatisFlexs.getColumnName(columnNameGetter), values));
    }

}