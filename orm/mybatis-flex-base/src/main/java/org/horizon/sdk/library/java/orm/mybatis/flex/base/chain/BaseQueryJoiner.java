package org.horizon.sdk.library.java.orm.mybatis.flex.base.chain;

import com.mybatisflex.core.query.Joiner;
import org.horizon.sdk.library.java.contract.model.base.POJO;

/**
 * @author wjm
 * @since 2023-12-05 16:38
 */
public abstract class BaseQueryJoiner<PJ extends POJO> {

    protected abstract Joiner<QueryChain<PJ>> getNativeQueryJoiner();

}