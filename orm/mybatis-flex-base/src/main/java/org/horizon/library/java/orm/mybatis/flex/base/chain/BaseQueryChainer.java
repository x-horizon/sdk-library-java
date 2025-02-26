package org.horizon.library.java.orm.mybatis.flex.base.chain;

import org.horizon.library.java.contract.model.base.POJO;

/**
 * @author wjm
 * @since 2023-12-05 16:38
 */
public abstract class BaseQueryChainer<PJ extends POJO> extends BaseChainer {

    protected abstract QueryChain<PJ> getNativeQueryChain();

}