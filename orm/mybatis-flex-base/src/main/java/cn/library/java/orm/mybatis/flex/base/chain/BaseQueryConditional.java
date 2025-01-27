package cn.library.java.orm.mybatis.flex.base.chain;

import com.mybatisflex.core.query.QueryConditionBuilder;
import com.mybatisflex.core.query.QueryWrapper;

/**
 * @author wjm
 * @since 2023-12-05 16:38
 */
public abstract class BaseQueryConditional<W extends QueryWrapper> {

    protected abstract QueryConditionBuilder<W> getNativeQueryConditional();

}