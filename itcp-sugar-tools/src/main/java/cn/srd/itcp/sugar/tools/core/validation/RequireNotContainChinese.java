package cn.srd.itcp.sugar.tools.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * 标记在字段上用于验证字符串类型的字段不包含中文字符
 *
 * @author
 * @see RequireNotContainChineseSupport
 * @since
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequireNotContainChineseSupport.class)
@Documented
public @interface RequireNotContainChinese {

    /**
     * 校验失败时的信息
     *
     * @return
     */
    String message() default "cannot contain chinese";

    /**
     * 要校验的分组
     *
     * @return
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
