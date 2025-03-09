package org.horizon.sdk.library.java.orm.mybatis.flex.base.chain;

import com.mybatisflex.core.update.UpdateChain;
import org.horizon.sdk.library.java.contract.model.base.PO;

/**
 * @author wjm
 * @since 2023-12-05 16:38
 */
public abstract class BaseUpdateChainer<P extends PO> extends BaseChainer {

    protected abstract UpdateChain<P> getNativeUpdateChainer();

}