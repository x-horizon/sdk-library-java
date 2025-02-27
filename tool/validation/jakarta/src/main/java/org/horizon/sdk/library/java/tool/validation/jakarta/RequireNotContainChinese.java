package org.horizon.sdk.library.java.tool.validation.jakarta;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * 标记在字段上用于验证字符串类型的字段不包含中文字符
 *
 * @author xiongjing
 * @see RequireNotContainChineseSupport
 * @since 2022-11-20 13:49
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequireNotContainChineseSupport.class)
@Documented
public @interface RequireNotContainChinese {

    /**
     * 校验失败时的信息
     *
     * @return 校验失败时的信息
     */
    String message() default "cannot contain chinese";

    /**
     * 要校验的分组
     *
     * @return 要校验的分组
     */
    Class<?>[] groups() default {};

    /**
     * 负载类
     *
     * @return 负载类
     */
    Class<? extends Payload>[] payload() default {};

}