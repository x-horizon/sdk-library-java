package org.horizon.library.java.orm.mybatis.flex.base.chain;

import org.horizon.library.java.contract.model.base.PO;
import com.mybatisflex.core.update.UpdateChain;

/**
 * @author wjm
 * @since 2023-12-05 16:38
 */
public abstract class BaseUpdateChainer<P extends PO> extends BaseChainer {

    protected abstract UpdateChain<P> getNativeUpdateChainer();

}