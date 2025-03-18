package org.horizon.sdk.library.java.orm.mybatis.flex.postgresql.chain;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mybatisflex.core.constant.SqlConnector;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.query.RawQueryTable;
import lombok.AccessLevel;
import lombok.Getter;
import org.horizon.sdk.library.java.contract.model.base.PO;
import org.horizon.sdk.library.java.contract.model.base.POJO;
import org.horizon.sdk.library.java.contract.model.throwable.UnsupportedException;
import org.horizon.sdk.library.java.orm.contract.constant.GenericTableAlias;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.chain.BaseQueryChainer;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.chain.QueryChain;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.constant.MybatisFlexDefaultDML;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.support.ColumnNameGetter;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.reflect.Reflects;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import static com.mybatisflex.core.query.QueryMethods.exists;

/**
 * @author wjm
 * @since 2024-04-23 19:48
 */
@Getter(AccessLevel.PROTECTED)
@CanIgnoreReturnValue
public class JsonQueryChainer<P extends PO, PJ extends POJO> extends BaseQueryChainer<PJ> {

    private final NormalQueryChainer<P, PJ> normalQueryChainer;

    private final QueryChain<PJ> nativeQueryChain;

    private final String tableName;

    private final Class<P> poClass;

    public JsonQueryChainer(NormalQueryChainer<P, PJ> normalQueryChainer, String tableName, Class<P> poClass) {
        this.normalQueryChainer = normalQueryChainer;
        this.nativeQueryChain = Reflects.getFieldValue(normalQueryChainer, "nativeQueryChain");
        this.tableName = tableName;
        this.poClass = poClass;
    }

    public <PJ1 extends POJO, PJ2 extends POJO> JsonQueryCaster<P, PJ> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter) {
        return and(columnNameGetter, jsonKeyGetter);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO> JsonQueryCaster<P, PJ> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2) {
        return and(columnNameGetter, jsonKeyGetter1, jsonKeyGetter2);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO> JsonQueryCaster<P, PJ> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3) {
        return and(columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO> JsonQueryCaster<P, PJ> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4) {
        return and(columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO, PJ6 extends POJO> JsonQueryCaster<P, PJ> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5) {
        return and(columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4, jsonKeyGetter5);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO, PJ6 extends POJO, PJ7 extends POJO> JsonQueryCaster<P, PJ> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5, ColumnNameGetter<PJ7> jsonKeyGetter6) {
        return and(columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4, jsonKeyGetter5, jsonKeyGetter6);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO, PJ6 extends POJO, PJ7 extends POJO, PJ8 extends POJO> JsonQueryCaster<P, PJ> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5, ColumnNameGetter<PJ7> jsonKeyGetter6, ColumnNameGetter<PJ8> jsonKeyGetter7) {
        return and(columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4, jsonKeyGetter5, jsonKeyGetter6, jsonKeyGetter7);
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO, PJ6 extends POJO, PJ7 extends POJO, PJ8 extends POJO, PJ9 extends POJO> JsonQueryCaster<P, PJ> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5, ColumnNameGetter<PJ7> jsonKeyGetter6, ColumnNameGetter<PJ8> jsonKeyGetter7, ColumnNameGetter<PJ9> jsonKeyGetter8) {
        return and(columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4, jsonKeyGetter5, jsonKeyGetter6, jsonKeyGetter7, jsonKeyGetter8);
    }

    public <PJ1 extends POJO> JsonQueryChainer<P, PJ> whereExist(JsonQueryFunctionChainer<PJ1> chainer) {
        return andExist(chainer);
    }

    public <PJ1 extends POJO, PJ2 extends POJO> JsonQueryCaster<P, PJ> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter) {
        return new JsonQueryCaster<>(
                JsonQuerySQLConnector.directConnect(this.tableName, columnNameGetter, jsonKeyGetter),
                SqlConnector.AND,
                this.nativeQueryChain,
                this
        );
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO> JsonQueryCaster<P, PJ> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2) {
        return new JsonQueryCaster<>(
                JsonQuerySQLConnector.directConnect(this.tableName, columnNameGetter, jsonKeyGetter1, jsonKeyGetter2),
                SqlConnector.AND,
                this.nativeQueryChain,
                this
        );
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO> JsonQueryCaster<P, PJ> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3) {
        return new JsonQueryCaster<>(
                JsonQuerySQLConnector.directConnect(this.tableName, columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3),
                SqlConnector.AND,
                this.nativeQueryChain,
                this
        );
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO> JsonQueryCaster<P, PJ> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4) {
        return new JsonQueryCaster<>(
                JsonQuerySQLConnector.directConnect(this.tableName, columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4),
                SqlConnector.AND,
                this.nativeQueryChain,
                this
        );
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO, PJ6 extends POJO> JsonQueryCaster<P, PJ> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5) {
        return new JsonQueryCaster<>(
                JsonQuerySQLConnector.directConnect(this.tableName, columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4, jsonKeyGetter5),
                SqlConnector.AND,
                this.nativeQueryChain,
                this
        );
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO, PJ6 extends POJO, PJ7 extends POJO> JsonQueryCaster<P, PJ> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5, ColumnNameGetter<PJ7> jsonKeyGetter6) {
        return new JsonQueryCaster<>(
                JsonQuerySQLConnector.directConnect(this.tableName, columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4, jsonKeyGetter5, jsonKeyGetter6),
                SqlConnector.AND,
                this.nativeQueryChain,
                this
        );
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO, PJ6 extends POJO, PJ7 extends POJO, PJ8 extends POJO> JsonQueryCaster<P, PJ> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5, ColumnNameGetter<PJ7> jsonKeyGetter6, ColumnNameGetter<PJ8> jsonKeyGetter7) {
        return new JsonQueryCaster<>(
                JsonQuerySQLConnector.directConnect(this.tableName, columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4, jsonKeyGetter5, jsonKeyGetter6, jsonKeyGetter7),
                SqlConnector.AND,
                this.nativeQueryChain,
                this
        );
    }

    public <PJ1 extends POJO, PJ2 extends POJO, PJ3 extends POJO, PJ4 extends POJO, PJ5 extends POJO, PJ6 extends POJO, PJ7 extends POJO, PJ8 extends POJO, PJ9 extends POJO> JsonQueryCaster<P, PJ> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5, ColumnNameGetter<PJ7> jsonKeyGetter6, ColumnNameGetter<PJ8> jsonKeyGetter7, ColumnNameGetter<PJ9> jsonKeyGetter8) {
        return new JsonQueryCaster<>(
                JsonQuerySQLConnector.directConnect(this.tableName, columnNameGetter, jsonKeyGetter1, jsonKeyGetter2, jsonKeyGetter3, jsonKeyGetter4, jsonKeyGetter5, jsonKeyGetter6, jsonKeyGetter7, jsonKeyGetter8),
                SqlConnector.AND,
                this.nativeQueryChain,
                this
        );
    }

    public <PJ1 extends POJO> JsonQueryChainer<P, PJ> andExist(JsonQueryFunctionChainer<PJ1> chainer) {
        QueryCondition queryCondition = Reflects.getFieldValue(chainer.getNativeQueryChain(), "whereQueryCondition");
        if (Nil.isNull(queryCondition.getColumn())) {
            return this;
        }
        this.nativeQueryChain.and(getJsonbFunctionExistQueryConditional(chainer));
        return this;
    }

    public <PJ1 extends POJO, PJ2 extends POJO> JsonQueryCaster<P, PJ> or(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter) {
        throw new UnsupportedException();
    }

    public <PJ1 extends POJO> JsonQueryChainer<P, PJ> orExist(JsonQueryFunctionChainer<PJ1> chainer) {
        throw new UnsupportedException();
    }

    public NormalQueryChainer<P, PJ> switchToNormalQuery() {
        return this.normalQueryChainer;
    }

    public String toSQL() {
        return this.nativeQueryChain.toSQL();
    }

    public String toSQLIgnoreTable() {
        return this.nativeQueryChain.toSQLIgnoreTable();
    }

    private <PJ1 extends POJO> QueryCondition getJsonbFunctionExistQueryConditional(JsonQueryFunctionChainer<PJ1> chainer) {
        return exists(QueryWrapper.create()
                .from(new RawQueryTable(Strings.format("({})", Strings.removeIfStartWith(chainer.getNativeQueryChain().toSQLIgnoreTable(), MybatisFlexDefaultDML.SELECT_SUFFIX))))
                .as(GenericTableAlias.JSON_QUERY_TABLE)
                .select("1")
        );
    }

}