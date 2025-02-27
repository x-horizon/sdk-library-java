package org.horizon.sdk.library.java.orm.mybatis.flex.postgresql.chain;

import org.horizon.sdk.library.java.contract.constant.database.PostgresqlFunctionType;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.contract.model.base.PO;
import org.horizon.sdk.library.java.contract.model.base.POJO;
import org.horizon.sdk.library.java.contract.model.throwable.UnsupportedException;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.cache.MybatisFlexSystemCache;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.chain.BaseQueryChainer;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.chain.QueryChain;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.constant.MybatisFlexDefaultDML;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.support.ColumnNameGetter;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.support.MybatisFlexs;
import org.horizon.sdk.library.java.tool.lang.text.Strings;
import com.mybatisflex.core.constant.SqlConnector;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.query.RawQueryCondition;
import com.mybatisflex.core.query.RawQueryTable;
import lombok.AccessLevel;
import lombok.Getter;

import static com.mybatisflex.core.query.QueryMethods.selectOne;

/**
 * the postgresql jsonb function.
 *
 * @author wjm
 * @since 2024-04-18 20:34
 */

public class JsonbQueryFunctionChainer<PJ extends POJO> extends BaseQueryChainer<PJ> {

    @Getter(AccessLevel.PROTECTED)
    private final QueryChain<PJ> nativeQueryChain;

    @Getter(AccessLevel.PROTECTED)
    private String sqlAppender;

    private boolean hasConnected;

    private JsonbQueryFunctionChainer(String sqlAppender) {
        this.nativeQueryChain = new QueryChain<>(null);
        this.sqlAppender = sqlAppender;
    }

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static <PJ1 extends POJO> JsonbQueryFunctionChainer<PJ1> jsonbArrayElements(ColumnNameGetter<PJ1> columnNameGetter) {
        return new JsonbQueryFunctionChainer<>(STR."\{PostgresqlFunctionType.JSONB_ARRAY_ELEMENTS.getValue()}({}\{MybatisFlexs.getColumnName(columnNameGetter)})");
    }

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static <PJ1 extends POJO> JsonbQueryFunctionChainer<PJ1> jsonbArrayElements(JsonbQueryFunctionChainer<PJ1> function) {
        return new JsonbQueryFunctionChainer<>(STR."\{PostgresqlFunctionType.JSONB_ARRAY_ELEMENTS.getValue()}(\{function.getSqlAppender()})");
    }

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO> JsonbQueryFunctionChainer<PJ1> jsonbExtractPath(ColumnNameGetter<PJ2> columnNameGetter, ColumnNameGetter<PJ3> jsonKeyGetter) {
        return new JsonbQueryFunctionChainer<>(STR."\{PostgresqlFunctionType.JSONB_EXTRACT_PATH.getValue()}({}\{MybatisFlexs.getColumnName(columnNameGetter)}, '\{MybatisFlexs.getFieldName(jsonKeyGetter)}')");
    }

    public <P extends PO> JsonbQueryFunctionChainer<PJ> addTableSuffix(Class<P> poClass) {
        this.sqlAppender = Strings.format(this.sqlAppender, Strings.format("\"{}\".", MybatisFlexSystemCache.getInstance().getByPOClass(poClass).getTableName()));
        return this;
    }

    public <PJ1 extends POJO> JsonbQueryFunctionCaster<PJ> where(ColumnNameGetter<PJ1> columnNameGetter) {
        return and(columnNameGetter);
    }

    public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryFunctionCaster<PJ> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter) {
        return and(columnNameGetter, jsonKeyGetter);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO> JsonbQueryFunctionCaster<PJ> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2) {
        return and(columnNameGetter, jsonKeyGetter1, jsonKeyGetter2);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO> JsonbQueryFunctionCaster<PJ> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3) {
        return and(columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO> JsonbQueryFunctionCaster<PJ> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4) {
        return and(columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO, PJ6 extends POJO> JsonbQueryFunctionCaster<PJ> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5) {
        return and(columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4, jsonKeyGetter5);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO, PJ6 extends POJO, PJ7 extends POJO> JsonbQueryFunctionCaster<PJ> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5, ColumnNameGetter<PJ7> jsonKeyGetter6) {
        return and(columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4, jsonKeyGetter5, jsonKeyGetter6);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO, PJ6 extends POJO, PJ7 extends POJO, PJ8 extends POJO> JsonbQueryFunctionCaster<PJ> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5, ColumnNameGetter<PJ7> jsonKeyGetter6, ColumnNameGetter<PJ8> jsonKeyGetter7) {
        return and(columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4, jsonKeyGetter5, jsonKeyGetter6, jsonKeyGetter7);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO, PJ6 extends POJO, PJ7 extends POJO, PJ8 extends POJO, PJ9 extends POJO> JsonbQueryFunctionCaster<PJ> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5, ColumnNameGetter<PJ7> jsonKeyGetter6, ColumnNameGetter<PJ8> jsonKeyGetter7, ColumnNameGetter<PJ9> jsonKeyGetter8) {
        return and(columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4, jsonKeyGetter5, jsonKeyGetter6, jsonKeyGetter7, jsonKeyGetter8);
    }

    public <PJ1 extends POJO> JsonbQueryFunctionCaster<PJ> and(ColumnNameGetter<PJ1> columnNameGetter) {
        return connect(SqlConnector.AND, columnNameGetter);
    }

    public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryFunctionCaster<PJ> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter) {
        return connect(SqlConnector.AND, columnNameGetter, jsonKeyGetter);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO> JsonbQueryFunctionCaster<PJ> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2) {
        return connect(SqlConnector.AND, columnNameGetter, jsonKeyGetter1, jsonKeyGetter2);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO> JsonbQueryFunctionCaster<PJ> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3) {
        return connect(SqlConnector.AND, columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO> JsonbQueryFunctionCaster<PJ> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4) {
        return connect(SqlConnector.AND, columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO, PJ6 extends POJO> JsonbQueryFunctionCaster<PJ> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5) {
        return connect(SqlConnector.AND, columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4, jsonKeyGetter5);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO, PJ6 extends POJO, PJ7 extends POJO> JsonbQueryFunctionCaster<PJ> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5, ColumnNameGetter<PJ7> jsonKeyGetter6) {
        return connect(SqlConnector.AND, columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4, jsonKeyGetter5, jsonKeyGetter6);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO, PJ6 extends POJO, PJ7 extends POJO, PJ8 extends POJO> JsonbQueryFunctionCaster<PJ> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5, ColumnNameGetter<PJ7> jsonKeyGetter6, ColumnNameGetter<PJ8> jsonKeyGetter7) {
        return connect(SqlConnector.AND, columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4, jsonKeyGetter5, jsonKeyGetter6, jsonKeyGetter7);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO, PJ6 extends POJO, PJ7 extends POJO, PJ8 extends POJO, PJ9 extends POJO> JsonbQueryFunctionCaster<PJ> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5, ColumnNameGetter<PJ7> jsonKeyGetter6, ColumnNameGetter<PJ8> jsonKeyGetter7, ColumnNameGetter<PJ9> jsonKeyGetter8) {
        return connect(SqlConnector.AND, columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4, jsonKeyGetter5, jsonKeyGetter6, jsonKeyGetter7, jsonKeyGetter8);
    }

    public <PJ1 extends POJO> JsonbQueryFunctionChainer<PJ> andExist(JsonbQueryFunctionChainer<PJ1> chainer) {
        // new JsonbQueryChainer<>(new NormalQueryChainer<>(new QueryChain<>(null), null, null), null, null).andExist(chainer).toSQLIgnoreTable();
        // QueryWrapper.create().and(exists(QueryWrapper.create()
        //         .from(new RawQueryTable(Strings.format("({})", Strings.removeIfStartWith(chainer.getNativeQueryChain().toSQLIgnoreTable(), MybatisFlexDefaultDML.SELECT_SUFFIX))))
        //         .as(GenericTableAlias.JSONB_QUERY_TABLE)
        //         .select("1")
        // )).toSQL();
        // return this;
        throw new UnsupportedException();
    }

    public <PJ1 extends POJO> JsonbQueryFunctionCaster<PJ> or(ColumnNameGetter<PJ1> columnNameGetter) {
        throw new UnsupportedException();
    }

    private <PJ1 extends POJO> JsonbQueryFunctionCaster<PJ> connect(SqlConnector sqlConnector, ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<?>... jsonKeyGetters) {
        String tableName = Strings.format(this.sqlAppender, SymbolConstant.EMPTY);
        String tableAlias = Strings.format("{}_{}", Strings.lowerFirst(MybatisFlexs.getClassName(columnNameGetter)), MybatisFlexs.getFieldName(columnNameGetter));
        RawQueryCondition queryCondition = new RawQueryCondition(JsonbQuerySQLConnector.functionConnect(Strings.joinWithDoubleQuoteAndComma(tableAlias), jsonKeyGetters));
        String sql = this.hasConnected ?
                Strings.removeIfStartWith(new QueryWrapper().where(queryCondition).toSQL(), MybatisFlexDefaultDML.SELECT_SUFFIX) :
                selectOne().from(new RawQueryTable(tableName)).as(tableAlias).where(queryCondition).toSQL();
        this.hasConnected = true;
        return connect(sqlConnector, sql);
    }

    private JsonbQueryFunctionCaster<PJ> connect(SqlConnector sqlConnector, String sql) {
        return new JsonbQueryFunctionCaster<>(sql, sqlConnector, this.nativeQueryChain, this);
    }

}