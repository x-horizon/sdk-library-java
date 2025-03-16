package org.horizon.sdk.library.java.orm.mybatis.flex.base.logic;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.core.logicdelete.LogicDeleteProcessor;
import com.mybatisflex.core.logicdelete.impl.BooleanLogicDeleteProcessor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * the global delete logic config
 *
 * @author wjm
 * @since 2023-11-13 21:19
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface DeleteLogicConfig {

    /**
     * <p>logic delete processor for database records. see <a href="https://mybatis-flex.com/zh/core/logic-delete.html#%E9%80%BB%E8%BE%91%E5%88%A0%E9%99%A4">official document</a>.</p>
     *
     * @return logic delete handler implementation
     * @apiNote only works when {@link Column#isLogicDelete()} is enabled on field. example:
     * <pre>{@code
     * @Column(value = "row_is_deleted", isLogicDelete = true)
     * private Boolean rowIsDeleted;
     * }</pre>
     */
    Class<? extends LogicDeleteProcessor> processor() default BooleanLogicDeleteProcessor.class;

}