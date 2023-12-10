// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.chain;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnValueGetter;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.logicdelete.LogicDeleteManager;
import com.mybatisflex.core.update.UpdateChain;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2023-12-08 20:40
 */
@AllArgsConstructor(access = AccessLevel.MODULE)
@SuppressWarnings(SuppressWarningConstant.UNUSED)
public class DeleteChainer<T extends PO> extends AbstractDeleteChainer<T> {

    @Getter(AccessLevel.PROTECTED) private final BaseMapper<T> nativeBaseMapper;

    @Getter(AccessLevel.PROTECTED) private final UpdateChain<T> nativeUpdateChainer;

    public static <T extends PO> DeleteChainer<T> of(BaseMapper<T> baseMapper) {
        return new DeleteChainer<>(baseMapper, UpdateChain.of(baseMapper));
    }

    public QueryConditional<T, DeleteChainer<T>, UpdateChain<T>> where(ColumnValueGetter<T> columnValueGetter) {
        return new QueryConditional<>(getNativeUpdateChainer().where(columnValueGetter), this);
    }

    public QueryConditional<T, DeleteChainer<T>, UpdateChain<T>> and(ColumnValueGetter<T> columnValueGetter) {
        return new QueryConditional<>(getNativeUpdateChainer().and(columnValueGetter), this);
    }

    public DeleteChainer<T> all() {
        return this;
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
