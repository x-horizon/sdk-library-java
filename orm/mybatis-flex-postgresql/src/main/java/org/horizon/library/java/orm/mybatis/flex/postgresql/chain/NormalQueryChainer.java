package org.horizon.library.java.orm.mybatis.flex.postgresql.chain;

import org.horizon.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.library.java.contract.model.base.PO;
import org.horizon.library.java.contract.model.base.POJO;
import org.horizon.library.java.orm.mybatis.flex.base.chain.QueryChain;
import org.horizon.library.java.orm.mybatis.flex.base.chain.QueryChainer;
import org.horizon.library.java.orm.mybatis.flex.base.chain.QueryConditional;
import org.horizon.library.java.orm.mybatis.flex.base.chain.QueryJoiner;
import org.horizon.library.java.orm.mybatis.flex.base.support.ColumnNameGetter;

import java.util.function.BooleanSupplier;

/**
 * @param <P> the entity extends {@link PO}
 * @author wjm
 * @since 2024-04-19 00:05
 */
public class NormalQueryChainer<P extends PO, PJ extends POJO> extends QueryChainer<P> {

    private final JsonbQueryChainer<P, PJ> jsonbQueryChainer;

    public NormalQueryChainer(QueryChain<P> nativeQueryChainer, String tableName, Class<P> poClass) {
        super(nativeQueryChainer);
        this.jsonbQueryChainer = new JsonbQueryChainer<>(this, tableName, poClass);
    }

    @Override
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <P1 extends PO> NormalQueryChainer<P, PJ> select(ColumnNameGetter<P1>... columnNameGetters) {
        getNativeQueryChain().select(columnNameGetters);
        return this;
    }

    @Override
    public <P1 extends PO> QueryJoiner<P, NormalQueryChainer<P, PJ>> innerJoin(Class<P1> entityClass) {
        return innerJoin(entityClass, true);
    }

    @Override
    public <P1 extends PO> QueryJoiner<P, NormalQueryChainer<P, PJ>> innerJoin(Class<P1> entityClass, BooleanSupplier condition) {
        return innerJoin(entityClass, condition.getAsBoolean());
    }

    @Override
    public <P1 extends PO> QueryJoiner<P, NormalQueryChainer<P, PJ>> innerJoin(Class<P1> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().innerJoin(entityClass, condition), this);
    }

    @Override
    public <P1 extends PO> QueryJoiner<P, NormalQueryChainer<P, PJ>> leftJoin(Class<P1> entityClass) {
        return leftJoin(entityClass, true);
    }

    @Override
    public <P1 extends PO> QueryJoiner<P, NormalQueryChainer<P, PJ>> leftJoin(Class<P1> entityClass, BooleanSupplier condition) {
        return leftJoin(entityClass, condition.getAsBoolean());
    }

    @Override
    public <P1 extends PO> QueryJoiner<P, NormalQueryChainer<P, PJ>> leftJoin(Class<P1> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().leftJoin(entityClass, condition), this);
    }

    @Override
    public <P1 extends PO> QueryJoiner<P, NormalQueryChainer<P, PJ>> rightJoin(Class<P1> entityClass) {
        return rightJoin(entityClass, true);
    }

    @Override
    public <P1 extends PO> QueryJoiner<P, NormalQueryChainer<P, PJ>> rightJoin(Class<P1> entityClass, BooleanSupplier condition) {
        return rightJoin(entityClass, condition.getAsBoolean());
    }

    @Override
    public <P1 extends PO> QueryJoiner<P, NormalQueryChainer<P, PJ>> rightJoin(Class<P1> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().rightJoin(entityClass, condition), this);
    }

    @Override
    public <P1 extends PO> QueryJoiner<P, NormalQueryChainer<P, PJ>> fullJoin(Class<P1> entityClass) {
        return fullJoin(entityClass, true);
    }

    @Override
    public <P1 extends PO> QueryJoiner<P, NormalQueryChainer<P, PJ>> fullJoin(Class<P1> entityClass, BooleanSupplier condition) {
        return fullJoin(entityClass, condition.getAsBoolean());
    }

    @Override
    public <P1 extends PO> QueryJoiner<P, NormalQueryChainer<P, PJ>> fullJoin(Class<P1> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().fullJoin(entityClass, condition), this);
    }

    @Override
    public <PJ1 extends POJO> QueryConditional<NormalQueryChainer<P, PJ>, QueryChain<P>> where(ColumnNameGetter<PJ1> columnNameGetter) {
        return new QueryConditional<>(this, getNativeQueryChain().where(columnNameGetter));
    }

    @Override
    public <PJ1 extends POJO> QueryConditional<NormalQueryChainer<P, PJ>, QueryChain<P>> and(ColumnNameGetter<PJ1> columnNameGetter) {
        return new QueryConditional<>(this, getNativeQueryChain().and(columnNameGetter));
    }

    @Override
    public <PJ1 extends POJO> QueryConditional<NormalQueryChainer<P, PJ>, QueryChain<P>> or(ColumnNameGetter<PJ1> columnNameGetter) {
        return new QueryConditional<>(this, getNativeQueryChain().or(columnNameGetter));
    }

    public JsonbQueryChainer<P, PJ> switchToJsonbQuery() {
        return this.jsonbQueryChainer;
    }

}