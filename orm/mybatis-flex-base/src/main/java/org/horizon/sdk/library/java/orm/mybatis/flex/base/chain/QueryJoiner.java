package org.horizon.sdk.library.java.orm.mybatis.flex.base.chain;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.Joiner;
import com.mybatisflex.core.query.QueryCondition;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.model.base.PO;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.support.ColumnNameGetter;
import org.horizon.sdk.library.java.tool.lang.reflect.Reflects;

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

    public <P1 extends PO> Q onEquals(ColumnNameGetter<P> masterTableColumn, ColumnNameGetter<P1> joinedTableColumn) {
        return on(newQueryChainer -> newQueryChainer.and(masterTableColumn).equalsTo(joinedTableColumn));
    }

    public <P1 extends PO> Q onEquals(ColumnNameGetter<P> masterTableColumn1, ColumnNameGetter<P1> joinedTableColumn1, ColumnNameGetter<P> masterTableColumn2, ColumnNameGetter<P1> joinedTableColumn2) {
        return on(newQueryChainer -> newQueryChainer
                .and(masterTableColumn1)
                .equalsTo(joinedTableColumn1)
                .and(masterTableColumn2)
                .equalsTo(joinedTableColumn2)
        );
    }

    public <P1 extends PO> Q onEquals(ColumnNameGetter<P> masterTableColumn1, ColumnNameGetter<P1> joinedTableColumn1, ColumnNameGetter<P> masterTableColumn2, ColumnNameGetter<P1> joinedTableColumn2, ColumnNameGetter<P> masterTableColumn3, ColumnNameGetter<P1> joinedTableColumn3) {
        return on(newQueryChainer -> newQueryChainer
                .and(masterTableColumn1)
                .equalsTo(joinedTableColumn1)
                .and(masterTableColumn2)
                .equalsTo(joinedTableColumn2)
                .and(masterTableColumn3)
                .equalsTo(joinedTableColumn3)
        );
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <P1 extends PO> Q onEquals(ColumnNameGetter<P> masterTableColumn1, ColumnNameGetter<P1> joinedTableColumn1, ColumnNameGetter<P> masterTableColumn2, ColumnNameGetter<P1> joinedTableColumn2, ColumnNameGetter<P> masterTableColumn3, ColumnNameGetter<P1> joinedTableColumn3, ColumnNameGetter<P> masterTableColumn4, ColumnNameGetter<P1> joinedTableColumn4) {
        return on(newQueryChainer -> newQueryChainer
                .and(masterTableColumn1)
                .equalsTo(joinedTableColumn1)
                .and(masterTableColumn2)
                .equalsTo(joinedTableColumn2)
                .and(masterTableColumn3)
                .equalsTo(joinedTableColumn3)
                .and(masterTableColumn4)
                .equalsTo(joinedTableColumn4)
        );
    }

    public <P1 extends PO> Q onEquals(ColumnNameGetter<P> masterTableColumn, ColumnNameGetter<P1> joinedTableColumn1, ColumnNameGetter<P1> joinedTableColumn2, Object joinedTableColumnValue2) {
        return on(newQueryChainer -> newQueryChainer
                .and(masterTableColumn)
                .equalsTo(joinedTableColumn1)
                .and(joinedTableColumn2)
                .equalsTo(joinedTableColumnValue2)
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