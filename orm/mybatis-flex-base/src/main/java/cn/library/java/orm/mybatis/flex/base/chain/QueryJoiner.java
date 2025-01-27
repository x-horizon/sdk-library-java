package cn.library.java.orm.mybatis.flex.base.chain;

import cn.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.library.java.contract.model.base.PO;
import cn.library.java.orm.mybatis.flex.base.support.ColumnNameGetter;
import cn.library.java.tool.lang.reflect.Reflects;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.Joiner;
import com.mybatisflex.core.query.QueryCondition;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Consumer;

/**
 * @param <P> the entity extends {@link PO}
 * @param <Q> the chainer extends {@link BaseQueryChainer}
 * @author wjm
 * @since 2023-12-05 16:20
 */
@AllArgsConstructor
public class QueryJoiner<P extends PO, Q extends BaseQueryChainer<P>> extends BaseQueryJoiner<P> {

    @Getter(AccessLevel.PROTECTED) private final Joiner<QueryChain<P>> nativeQueryJoiner;

    @Getter(AccessLevel.PRIVATE) private final Q queryChainer;

    private static final String WHERE_QUERY_CONDITION_FIELD_NAME = "whereQueryCondition";

    @SuppressWarnings(SuppressWarningConstant.DEPRECATED)
    public QueryJoiner<P, Q> as(String alias) {
        getNativeQueryJoiner().as(alias);
        return this;
    }

    public <P1 extends PO> Q onEquals(ColumnNameGetter<P> masterTableColumnValue, ColumnNameGetter<P1> joinedTableColumnValue) {
        return on(newQueryChainer -> newQueryChainer.and(masterTableColumnValue).equalsTo(joinedTableColumnValue));
    }

    public <P1 extends PO> Q onEquals(ColumnNameGetter<P> masterTableColumnValue1, ColumnNameGetter<P1> joinedTableColumnValue1, ColumnNameGetter<P> masterTableColumnValue2, ColumnNameGetter<P1> joinedTableColumnValue2) {
        return on(newQueryChainer -> newQueryChainer
                .and(masterTableColumnValue1)
                .equalsTo(joinedTableColumnValue1)
                .and(masterTableColumnValue2)
                .equalsTo(joinedTableColumnValue2)
        );
    }

    public <P1 extends PO> Q onEquals(ColumnNameGetter<P> masterTableColumnValue1, ColumnNameGetter<P1> joinedTableColumnValue1, ColumnNameGetter<P> masterTableColumnValue2, ColumnNameGetter<P1> joinedTableColumnValue2, ColumnNameGetter<P> masterTableColumnValue3, ColumnNameGetter<P1> joinedTableColumnValue3) {
        return on(newQueryChainer -> newQueryChainer
                .and(masterTableColumnValue1)
                .equalsTo(joinedTableColumnValue1)
                .and(masterTableColumnValue2)
                .equalsTo(joinedTableColumnValue2)
                .and(masterTableColumnValue3)
                .equalsTo(joinedTableColumnValue3)
        );
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <P1 extends PO> Q onEquals(ColumnNameGetter<P> masterTableColumnValue1, ColumnNameGetter<P1> joinedTableColumnValue1, ColumnNameGetter<P> masterTableColumnValue2, ColumnNameGetter<P1> joinedTableColumnValue2, ColumnNameGetter<P> masterTableColumnValue3, ColumnNameGetter<P1> joinedTableColumnValue3, ColumnNameGetter<P> masterTableColumnValue4, ColumnNameGetter<P1> joinedTableColumnValue4) {
        return on(newQueryChainer -> newQueryChainer
                .and(masterTableColumnValue1)
                .equalsTo(joinedTableColumnValue1)
                .and(masterTableColumnValue2)
                .equalsTo(joinedTableColumnValue2)
                .and(masterTableColumnValue3)
                .equalsTo(joinedTableColumnValue3)
                .and(masterTableColumnValue4)
                .equalsTo(joinedTableColumnValue4)
        );
    }

    public Q on(Consumer<QueryChainer<P>> queryChainAction) {
        BaseMapper<P> baseMapper = getQueryChainer().getNativeQueryChain().baseMapper();
        QueryChainer<P> newQueryChainer = new QueryChainer<>(QueryChain.of(baseMapper));
        queryChainAction.accept(newQueryChainer);
        getNativeQueryJoiner().on(Reflects.getFieldValue(newQueryChainer.getNativeQueryChain().toQueryWrapper(), WHERE_QUERY_CONDITION_FIELD_NAME, QueryCondition.class));
        return getQueryChainer();
    }

}