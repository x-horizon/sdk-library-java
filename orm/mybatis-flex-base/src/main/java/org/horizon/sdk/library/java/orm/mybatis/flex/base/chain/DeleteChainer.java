package org.horizon.sdk.library.java.orm.mybatis.flex.base.chain;

import com.mybatisflex.core.logicdelete.LogicDeleteManager;
import com.mybatisflex.core.update.UpdateChain;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.horizon.sdk.library.java.contract.model.base.PO;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.support.ColumnNameGetter;

/**
 * @author wjm
 * @since 2023-12-08 20:40
 */
@AllArgsConstructor
public class DeleteChainer<P extends PO> extends BaseDeleteChainer<P> {

    @Getter(AccessLevel.PROTECTED) private final UpdateChain<P> nativeUpdateChainer;

    public QueryConditional<DeleteChainer<P>, UpdateChain<P>> where(ColumnNameGetter<P> columnNameGetter) {
        return new QueryConditional<>(this, getNativeUpdateChainer().where(columnNameGetter));
    }

    public QueryConditional<DeleteChainer<P>, UpdateChain<P>> and(ColumnNameGetter<P> columnNameGetter) {
        return new QueryConditional<>(this, getNativeUpdateChainer().and(columnNameGetter));
    }

    public QueryConditional<DeleteChainer<P>, UpdateChain<P>> or(ColumnNameGetter<P> columnNameGetter) {
        return new QueryConditional<>(this, getNativeUpdateChainer().or(columnNameGetter));
    }

    public DeleteChainer<P> ignoreLogicDelete() {
        this.needToIgnoreLogicDelete = true;
        return this;
    }

    public void delete() {
        if (this.needToIgnoreLogicDelete) {
            LogicDeleteManager.execWithoutLogicDelete(this::doDelete);
        } else {
            doDelete();
        }
    }

    private void doDelete() {
        getNativeUpdateChainer().remove();
    }

    public String toSQL() {
        return getNativeUpdateChainer().toSQL();
    }

}