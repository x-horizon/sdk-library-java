package cn.srd.library.java.orm.mybatis.flex.base.logic;

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
     * the logic deleted processor when data in database to be deleted, see <a href="https://mybatis-flex.com/zh/core/logic-delete.html#%E9%80%BB%E8%BE%91%E5%88%A0%E9%99%A4">official document</url>.
     *
     * @return the logic deleted processor when data in database to be deleted.
     * @apiNote only when {@link Column#isLogicDelete()} = true on the field will it take effect, like:
     * <p>
     * <pre>
     * {@code
     *  @Column(value = "row_is_deleted", isLogicDelete = true)
     *  private Boolean rowIsDeleted;
     * }
     * </pre>
     */
    Class<? extends LogicDeleteProcessor> processor() default BooleanLogicDeleteProcessor.class;

}