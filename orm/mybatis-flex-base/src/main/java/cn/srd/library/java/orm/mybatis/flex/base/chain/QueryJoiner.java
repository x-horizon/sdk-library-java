package cn.srd.library.java.orm.mybatis.flex.base.chain;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnValueGetter;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.Joiner;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryCondition;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Consumer;

/**
 * @param <Q> query chain type
 * @author wjm
 * @since 2023-12-05 16:20
 */
@AllArgsConstructor(access = AccessLevel.MODULE)
@SuppressWarnings(SuppressWarningConstant.UNUSED)
public class QueryJoiner<T extends PO, Q extends BaseQueryChainer<T>> extends BaseQueryJoiner<T> {

    @Getter(AccessLevel.PROTECTED) private final Joiner<QueryChain<T>> nativeQueryJoiner;

    @Getter(AccessLevel.PRIVATE) private final Q queryChainer;

    private static final String WHERE_QUERY_CONDITION_FIELD_NAME = "whereQueryCondition";

    public QueryJoiner<T, Q> as(String alias) {
        getNativeQueryJoiner().as(alias);
        return this;
    }

    public <U extends PO> Q onEquals(ColumnValueGetter<T> masterTableColumnValue, ColumnValueGetter<U> joinedTableColumnValue) {
        return on(newQueryChainer -> newQueryChainer.and(masterTableColumnValue).equalsTo(joinedTableColumnValue));
    }

    public <U extends PO> Q onEquals(ColumnValueGetter<T> masterTableColumnValue1, ColumnValueGetter<U> joinedTableColumnValue1, ColumnValueGetter<T> masterTableColumnValue2, ColumnValueGetter<U> joinedTableColumnValue2) {
        return on(newQueryChainer -> newQueryChainer
                .and(masterTableColumnValue1)
                .equalsTo(joinedTableColumnValue1)
                .and(masterTableColumnValue2)
                .equalsTo(joinedTableColumnValue2)
        );
    }

    public <U extends PO> Q onEquals(ColumnValueGetter<T> masterTableColumnValue1, ColumnValueGetter<U> joinedTableColumnValue1, ColumnValueGetter<T> masterTableColumnValue2, ColumnValueGetter<U> joinedTableColumnValue2, ColumnValueGetter<T> masterTableColumnValue3, ColumnValueGetter<U> joinedTableColumnValue3) {
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
    public <U extends PO> Q onEquals(ColumnValueGetter<T> masterTableColumnValue1, ColumnValueGetter<U> joinedTableColumnValue1, ColumnValueGetter<T> masterTableColumnValue2, ColumnValueGetter<U> joinedTableColumnValue2, ColumnValueGetter<T> masterTableColumnValue3, ColumnValueGetter<U> joinedTableColumnValue3, ColumnValueGetter<T> masterTableColumnValue4, ColumnValueGetter<U> joinedTableColumnValue4) {
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

    public Q on(Consumer<QueryChainer<T>> queryChainAction) {
        BaseMapper<T> baseMapper = getQueryChainer().getNativeQueryChainer().baseMapper();
        QueryChainer<T> newQueryChainer = new QueryChainer<>(baseMapper, QueryChain.of(baseMapper));
        queryChainAction.accept(newQueryChainer);
        getNativeQueryJoiner().on(Reflects.getFieldValue(newQueryChainer.getNativeQueryChainer().toQueryWrapper(), WHERE_QUERY_CONDITION_FIELD_NAME, QueryCondition.class));
        return getQueryChainer();
    }

}