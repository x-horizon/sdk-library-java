// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.chain2;

import cn.srd.library.java.contract.constant.collection.CollectionConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.base.POJO;
import cn.srd.library.java.orm.mybatis.flex.base.chain.BaseChainer;
import cn.srd.library.java.orm.mybatis.flex.base.chain.BaseQueryChainer;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryChain;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.MybatisFlexs;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import cn.srd.library.java.tool.lang.text.Strings;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mybatisflex.core.constant.SqlConnector;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author wjm
 * @since 2024-04-23 19:48
 */
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PRIVATE)
@CanIgnoreReturnValue
public class JsonbQueryChainer<PJ extends POJO, P extends PO> extends BaseQueryChainer<PJ> {

    private final NormalQueryChainer<P, PJ> normalQueryChainer;

    private final QueryChain<PJ> nativeQueryChain;

    private final String tableName;

    private final Class<P> poClass;

    private String jsonbFunctionSQL;

    private String theLastJsonKeyName;

    private ColumnNameGetter<PJ> theLastJsonKeyNameGetter;

    public JsonbQueryChainer(NormalQueryChainer<P, PJ> normalQueryChainer, String tableName, Class<P> poClass) {
        this.normalQueryChainer = normalQueryChainer;
        this.nativeQueryChain = Reflects.getFieldValue(normalQueryChainer, "nativeQueryChain");
        this.tableName = tableName;
        this.poClass = poClass;
    }

    public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryCaster<PJ, P> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter) {
        return and(columnNameGetter, jsonKeyGetter);
    }

    public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryCaster<PJ, P> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter) {
        return new JsonbQueryCaster<>(connectJsonbDirectQuerySQL(columnNameGetter, jsonKeyGetter), SqlConnector.AND, this.nativeQueryChain, this);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO> JsonbQueryCaster<PJ, P> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2) {
        return new JsonbQueryCaster<>(connectJsonbDirectQuerySQL(columnNameGetter, jsonKeyGetter1, jsonKeyGetter2), SqlConnector.AND, this.nativeQueryChain, this);
    }

    public <C extends BaseChainer, W extends QueryWrapper> JsonbQueryCaster<PJ, P> andExistSubquery(PostgresqlFunctionQueryConditional<C, W> function) {
        return new JsonbQueryCaster<>(connectJsonbFunctionQuerySQL(function), SqlConnector.AND, this.nativeQueryChain, this);
    }

    public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryCaster<PJ, P> or(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter) {
        return new JsonbQueryCaster<>(connectJsonbDirectQuerySQL(columnNameGetter, jsonKeyGetter), SqlConnector.OR, this.nativeQueryChain, this);
    }

    public String toSQL() {
        return this.nativeQueryChain.toSQL();
    }

    private String connectJsonbDirectQuerySQL(ColumnNameGetter<?>... columnNameGetters) {
        return Strings.format("({})", IntStream.range(CollectionConstant.INDEX_FIRST, columnNameGetters.length)
                .mapToObj(index -> {
                    String columnName = MybatisFlexs.getFieldName(columnNameGetters[index]);
                    if (index == CollectionConstant.INDEX_FIRST) {
                        return Strings.format("\"{}\".{}", this.tableName, columnName);
                    } else if (index == columnNameGetters.length - 1) {
                        return Strings.format(" ->> '{}'", columnName);
                    } else {
                        return Strings.format(" -> '{}'", columnName);
                    }
                })
                .collect(Collectors.joining())
        );
    }

    private <C extends BaseChainer, W extends QueryWrapper> String connectJsonbFunctionQuerySQL(PostgresqlFunctionQueryConditional<C, W> function) {
        return Strings.format(function.getFunctionSQLAppender(), Strings.format("\"{}\".", this.tableName));
    }

}