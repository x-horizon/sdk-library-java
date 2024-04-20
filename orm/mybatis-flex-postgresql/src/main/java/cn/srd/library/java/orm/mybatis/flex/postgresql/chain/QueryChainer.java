// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.chain;

import cn.srd.library.java.contract.component.database.postgresql.PostgresqlJsonbSQL;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.base.POJO;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.MybatisFlexs;
import cn.srd.library.java.orm.mybatis.flex.postgresql.function.PostgresqlFunctionQueryCondition;
import cn.srd.library.java.tool.id.snowflake.SnowflakeIds;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.text.Strings;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.RawQueryCondition;
import com.mybatisflex.core.query.RawQueryTable;

import java.util.List;

/**
 * @param <P> the entity extends {@link PO}
 * @author wjm
 * @since 2024-04-19 00:05
 */
@CanIgnoreReturnValue
@SuppressWarnings(SuppressWarningConstant.UNUSED)
public class QueryChainer<P extends PO> extends cn.srd.library.java.orm.mybatis.flex.base.chain.QueryChainer<P> {

    protected QueryChainer(BaseMapper<P> nativeBaseMapper, QueryChain<P> nativeQueryChainer) {
        super(nativeBaseMapper, nativeQueryChainer);
    }

    public static <P extends PO> QueryChainer<P> of(BaseMapper<P> baseMapper) {
        return new QueryChainer<>(baseMapper, QueryChain.of(baseMapper));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <U extends PO> QueryChainer<P> select(ColumnNameGetter<U>... columnNameGetters) {
        getNativeQueryChainer().select(columnNameGetters);
        return this;
    }

    public <U extends PO> QueryChainer<P> selectSelfAll() {
        getNativeQueryChainer().select();
        return this;
    }

    public final <U extends PO> QueryChainer<P> innerJoinJsonbListVirtualTable(ColumnNameGetter<U> columnNameGetter) {
        String columnName = MybatisFlexs.getColumnName(columnNameGetter);
        getNativeQueryChainer()
                .innerJoin(new RawQueryTable(Strings.format("JSONB_ARRAY_ELEMENTS({})", columnName)))
                .as(Strings.format("{}_{}", columnName, SnowflakeIds.get()))
                .on("1=1");
        return this;
    }

    @SafeVarargs
    public final <U extends PO, T extends POJO> QueryChainer<P> innerJoinJsonbListVirtualTable(ColumnNameGetter<U> columnNameGetter, ColumnNameGetter<? extends T>... jsonbInternalKeyNameGetters) {
        List<String> jsonbInternalKeyNames = Converts.toList(jsonbInternalKeyNameGetters, jsonbInternalKeyNameGetter -> Strings.format("'{}'", MybatisFlexs.getFieldName(jsonbInternalKeyNameGetter)));
        getNativeQueryChainer()
                .innerJoin(new RawQueryTable(Strings.format("JSONB_ARRAY_ELEMENTS(JSONB_EXTRACT_PATH({}, {}))", MybatisFlexs.getColumnName(columnNameGetter), Strings.joinWithComma(jsonbInternalKeyNames))))
                .as(Strings.format("{}_{}", MybatisFlexs.getFieldName(Collections.getLast(jsonbInternalKeyNameGetters).orElseThrow()), SnowflakeIds.get()))
                .on("1=1");
        return this;
    }

    public <U extends PO, T extends Number> QueryChainer<P> lkkk(ColumnNameGetter<U> columnNameGetter) {
        getNativeQueryChainer().and(new RawQueryCondition(PostgresqlJsonbSQL.getEmptyListEqual(MybatisFlexs.getColumnName(columnNameGetter))));
        return this;
    }

    public <U extends PO, T extends Number> QueryChainer<P> andListJsonbEmptyListFunction(ColumnNameGetter<U> columnNameGetter) {
        getNativeQueryChainer().and(PostgresqlFunctionQueryCondition.listJsonbEmptyList(columnNameGetter));
        return this;
    }

    public <U extends PO, T extends Number> QueryChainer<P> andListJsonbListNumberEqualFunction(ColumnNameGetter<P> columnNameGetter, T value) {
        getNativeQueryChainer().and(PostgresqlFunctionQueryCondition.listJsonbListNumberEqual(columnNameGetter, value));
        return this;
    }

    public <U extends PO, T extends Number> QueryChainer<P> andListJsonbListNumberInFunction(ColumnNameGetter<P> columnNameGetter, List<T> values) {
        getNativeQueryChainer().and(PostgresqlFunctionQueryCondition.listJsonbListNumberIn(columnNameGetter, values));
        return this;
    }

    public <U extends PO> QueryChainer<P> andListJsonbListStringEqualFunction(ColumnNameGetter<P> columnNameGetter, String value) {
        getNativeQueryChainer().and(PostgresqlFunctionQueryCondition.listJsonbListStringEqual(columnNameGetter, value));
        return this;
    }

    public <U extends PO> QueryChainer<P> andListJsonbListStringInFunction(ColumnNameGetter<P> columnNameGetter, List<String> values) {
        getNativeQueryChainer().and(PostgresqlFunctionQueryCondition.listJsonbListStringIn(columnNameGetter, values));
        return this;
    }

    public <U extends PO> QueryChainer<P> andListJsonbListStringLikeFunction(ColumnNameGetter<P> columnNameGetter, String value) {
        getNativeQueryChainer().and(PostgresqlFunctionQueryCondition.listJsonbListStringLike(columnNameGetter, value));
        return this;
    }

    public <U extends PO, T extends Number> QueryChainer<P> andListJsonbListObjectKeyIdEqualFunction(ColumnNameGetter<P> columnNameGetter, T value) {
        getNativeQueryChainer().and(PostgresqlFunctionQueryCondition.listJsonbListObjectKeyIdEqual(columnNameGetter, value));
        return this;
    }

    public <U extends PO, T extends Number> QueryChainer<P> andListJsonbListObjectKeyIdInFunction(ColumnNameGetter<P> columnNameGetter, List<T> values) {
        getNativeQueryChainer().and(PostgresqlFunctionQueryCondition.listJsonbListObjectKeyIdIn(columnNameGetter, values));
        return this;
    }

    public <U extends PO, T extends Number> QueryChainer<P> andListJsonbListObjectKeyTypeEqualFunction(ColumnNameGetter<P> columnNameGetter, T value) {
        getNativeQueryChainer().and(PostgresqlFunctionQueryCondition.listJsonbListObjectKeyTypeEqual(columnNameGetter, value));
        return this;
    }

    public <U extends PO, T extends Number> QueryChainer<P> andListJsonbListObjectKeyTypeInFunction(ColumnNameGetter<P> columnNameGetter, List<T> values) {
        getNativeQueryChainer().and(PostgresqlFunctionQueryCondition.listJsonbListObjectKeyTypeIn(columnNameGetter, values));
        return this;
    }

}