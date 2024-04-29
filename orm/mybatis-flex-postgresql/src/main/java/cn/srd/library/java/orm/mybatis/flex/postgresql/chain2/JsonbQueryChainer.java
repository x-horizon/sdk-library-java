// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.chain2;

import cn.srd.library.java.contract.constant.collection.CollectionConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.base.POJO;
import cn.srd.library.java.orm.mybatis.flex.base.chain.BaseQueryChainer;
import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryChain;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.MybatisFlexs;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import cn.srd.library.java.tool.lang.text.Strings;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mybatisflex.core.constant.SqlConnector;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.mybatisflex.core.query.QueryMethods.exists;

/**
 * @author wjm
 * @since 2024-04-23 19:48
 */
@Getter(AccessLevel.PROTECTED)
@CanIgnoreReturnValue
public class JsonbQueryChainer<P extends PO, PJ extends POJO> extends BaseQueryChainer<PJ> {

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

    public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryCaster<P, PJ> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter) {
        return and(columnNameGetter, jsonKeyGetter);
    }

    public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryCaster<P, PJ> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter) {
        return new JsonbQueryCaster<>(connectJsonbDirectQuerySQL(columnNameGetter, jsonKeyGetter), SqlConnector.AND, this.nativeQueryChain, this);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO> JsonbQueryCaster<P, PJ> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2) {
        return new JsonbQueryCaster<>(connectJsonbDirectQuerySQL(columnNameGetter, jsonKeyGetter1, jsonKeyGetter2), SqlConnector.AND, this.nativeQueryChain, this);
    }

    public <PJ1 extends POJO> JsonbQueryChainer<P, PJ> andExist(JsonbQueryFunctionChainer<PJ1> chainer) {
        // QueryWrapper queryWrapper = chainer.getNativeQueryChain().toQueryWrapper();
        // queryWrapper.from(new RawQueryTable(Strings.removeIfStartWith(queryWrapper.toSQL(), "SELECT * FROM  WHERE "))).as(SnowflakeIds.getString());
        // Reflects.getFieldValue(queryWrapper, "whereQueryCondition");
        // this.nativeQueryChain.and(exists(queryWrapper));
        this.nativeQueryChain.and(exists(chainer.getNativeQueryChain().toQueryWrapper()));
        return this;
    }

    // public JsonbJsonbQueryCaster<P, PJ> andExist(DatabaseFunctional function) {
    //     return new JsonbJsonbQueryCaster<>(connectJsonbFunctionQuerySQL(function), SqlConnector.AND, this.nativeQueryChain, this);
    // }

    public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryCaster<P, PJ> or(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter) {
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

    // private QueryWrapper connectJsonbFunctionQuerySQL(JsonbQueryFunctionChainer<PJ> chainer) {
    //     exists(selectOne().from("1").where("1")).toSql();
    //
    //     exists(selectOne()
    //             .from(new RawQueryTable("JSONB_ARRAY_ELEMENTS(\"student\".teacher_ids)"))
    //             .as("studentPO_teacher_ids_540330549004101")
    //             .where(new RawQueryCondition("\"studentPO_teacher_ids_540330549004101\"::INTEGER"))
    //             .in(StudentBO::getName, 1, 2, 3)
    //     );
    //
    //     String alias = Strings.format("{}_{}", Strings.lowerFirst(chainer.getClassOfFieldName()), chainer.getFieldName());
    //     return selectOne()
    //             .from(new RawQueryTable(Strings.format(chainer.getSqlAppender(), Strings.format("\"{}\".", this.tableName))))
    //             .as(alias)
    //             .where(new RawQueryCondition("\"studentPO_teacher_ids_540330549004101\"::INTEGER"))
    //             .in(StudentBO::getName, 1, 2, 3);
    //
    //     return Strings.format(function.getSqlAppender(), Strings.format("\"{}\".", this.tableName));
    // }

}