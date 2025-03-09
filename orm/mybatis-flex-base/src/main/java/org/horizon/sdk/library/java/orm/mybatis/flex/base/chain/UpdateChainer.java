package org.horizon.sdk.library.java.orm.mybatis.flex.base.chain;

import com.mybatisflex.core.logicdelete.LogicDeleteManager;
import com.mybatisflex.core.update.UpdateChain;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.horizon.sdk.library.java.contract.model.base.PO;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.support.ColumnNameGetter;
import org.horizon.sdk.library.java.tool.lang.functional.If;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

/**
 * @author wjm
 * @since 2023-12-05 23:25
 */
@AllArgsConstructor
public class UpdateChainer<P extends PO> extends BaseUpdateChainer<P> {

    @Getter(AccessLevel.PROTECTED) private final UpdateChain<P> nativeUpdateChainer;

    public UpdateChainer<P> set(ColumnNameGetter<P> columnNameGetter, Object value) {
        return set(columnNameGetter, value, true);
    }

    public UpdateChainer<P> set(ColumnNameGetter<P> columnNameGetter, Object value, BooleanSupplier condition) {
        return set(columnNameGetter, value, condition.getAsBoolean());
    }

    public <U> UpdateChainer<P> set(ColumnNameGetter<P> columnNameGetter, U value, Predicate<U> condition) {
        return set(columnNameGetter, value, condition.test(value));
    }

    public UpdateChainer<P> set(ColumnNameGetter<P> columnNameGetter, Object value, boolean condition) {
        getNativeUpdateChainer().set(columnNameGetter, value, condition);
        return this;
    }

    public UpdateChainer<P> setIfNotNull(ColumnNameGetter<P> columnNameGetter, Object value) {
        return set(columnNameGetter, value, If::notNull);
    }

    public UpdateChainer<P> setIfNotZeroValue(ColumnNameGetter<P> columnNameGetter, Number value) {
        return set(columnNameGetter, value, If::notZeroValue);
    }

    public UpdateChainer<P> setIfNotEmpty(ColumnNameGetter<P> columnNameGetter, CharSequence value) {
        return set(columnNameGetter, value, If::notEmpty);
    }

    public UpdateChainer<P> setIfNotBlank(ColumnNameGetter<P> columnNameGetter, CharSequence value) {
        return set(columnNameGetter, value, If::notBlank);
    }

    public QueryConditional<UpdateChainer<P>, UpdateChain<P>> where(ColumnNameGetter<P> columnNameGetter) {
        return new QueryConditional<>(this, getNativeUpdateChainer().where(columnNameGetter));
    }

    public QueryConditional<UpdateChainer<P>, UpdateChain<P>> and(ColumnNameGetter<P> columnNameGetter) {
        return new QueryConditional<>(this, getNativeUpdateChainer().and(columnNameGetter));
    }

    public QueryConditional<UpdateChainer<P>, UpdateChain<P>> or(ColumnNameGetter<P> columnNameGetter) {
        return new QueryConditional<>(this, getNativeUpdateChainer().or(columnNameGetter));
    }

    public UpdateChainer<P> ignoreLogicDelete() {
        this.needToIgnoreLogicDelete = true;
        return this;
    }

    public void update() {
        if (this.needToIgnoreLogicDelete) {
            LogicDeleteManager.execWithoutLogicDelete(this::doUpdate);
        } else {
            doUpdate();
        }
    }

    private void doUpdate() {
        getNativeUpdateChainer().update();
    }

    public String toSQL() {
        return getNativeUpdateChainer().toSQL();
    }

}