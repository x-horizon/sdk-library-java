// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.chain2;

import cn.srd.library.java.contract.component.database.base.function.DatabaseFunctional;
import cn.srd.library.java.contract.constant.database.PostgresqlFunctionType;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.POJO;
import cn.srd.library.java.orm.mybatis.flex.base.chain.BaseChainer;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryConditional;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.MybatisFlexs;
import com.mybatisflex.core.query.QueryConditionBuilder;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * the postgresql jsonb function.
 *
 * @author wjm
 * @since 2024-04-18 20:34
 */
@Getter(AccessLevel.PACKAGE)
public class PostgresqlFunctionQueryConditional<C extends BaseChainer, W extends QueryWrapper> extends QueryConditional<C, W> implements DatabaseFunctional {

    private final String functionSQLAppender;

    private PostgresqlFunctionQueryConditional(String functionSQLAppender) {
        super(null, null);
        this.functionSQLAppender = functionSQLAppender;
    }

    private PostgresqlFunctionQueryConditional(C chainer, QueryConditionBuilder<W> nativeQueryConditional, String functionSQLAppender) {
        super(chainer, nativeQueryConditional);
        this.functionSQLAppender = functionSQLAppender;
    }

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static <C extends BaseChainer, W extends QueryWrapper, PJ extends POJO> PostgresqlFunctionQueryConditional<C, W> jsonbArrayElements(ColumnNameGetter<PJ> columnNameGetter) {
        return new PostgresqlFunctionQueryConditional<>(STR."\{PostgresqlFunctionType.JSONB_ARRAY_ELEMENTS.getValue()}({}\{MybatisFlexs.getColumnName(columnNameGetter)})");
    }

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static <C extends BaseChainer, W extends QueryWrapper> PostgresqlFunctionQueryConditional<C, W> jsonbArrayElements(PostgresqlFunctionQueryConditional<C, W> function) {
        return new PostgresqlFunctionQueryConditional<>(STR."\{PostgresqlFunctionType.JSONB_ARRAY_ELEMENTS.getValue()}(\{function.getFunctionSQLAppender()})");
    }

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static <C extends BaseChainer, W extends QueryWrapper, PJ1 extends POJO, PJ2 extends POJO> PostgresqlFunctionQueryConditional<C, W> jsonbExtractPath(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter) {
        return new PostgresqlFunctionQueryConditional<>(STR."\{PostgresqlFunctionType.JSONB_EXTRACT_PATH.getValue()}({}\{MybatisFlexs.getColumnName(columnNameGetter)}, '\{MybatisFlexs.getFieldName(jsonKeyGetter)}')");
    }

}