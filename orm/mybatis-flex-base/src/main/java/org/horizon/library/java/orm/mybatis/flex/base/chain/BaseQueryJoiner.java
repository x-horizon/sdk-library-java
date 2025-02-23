package org.horizon.library.java.orm.mybatis.flex.base.chain;

import org.horizon.library.java.contract.model.base.POJO;
import com.mybatisflex.core.query.Joiner;

/**
 * @author wjm
 * @since 2023-12-05 16:38
 */
public abstract class BaseQueryJoiner<PJ extends POJO> {

    protected abstract Joiner<QueryChain<PJ>> getNativeQueryJoiner();

}