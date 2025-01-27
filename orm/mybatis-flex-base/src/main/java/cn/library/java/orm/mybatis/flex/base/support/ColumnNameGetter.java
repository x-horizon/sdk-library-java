package cn.library.java.orm.mybatis.flex.base.support;

import com.mybatisflex.core.util.LambdaGetter;

/**
 * @author wjm
 * @since 2023-12-03 23:39
 */
@FunctionalInterface
public interface ColumnNameGetter<T> extends LambdaGetter<T> {

}