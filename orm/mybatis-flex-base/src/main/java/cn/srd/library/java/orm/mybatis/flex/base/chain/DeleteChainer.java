// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.chain;

import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.mybatis.flex.base.support.ColumnNameGetter;
import com.mybatisflex.core.logicdelete.LogicDeleteManager;
import com.mybatisflex.core.update.UpdateChain;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

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

    public void delete() {
        getNativeUpdateChainer().remove();
    }

    public void deleteSkipLogic() {
        LogicDeleteManager.execWithoutLogicDelete(this::delete);
    }

    public String toSQL() {
        return getNativeUpdateChainer().toSQL();
    }

}