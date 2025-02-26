package cn.srd.library.java.tool.lang.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Integer 类型数字的正整数校验注解（必须大于 0）
 *
 * @author wjm
 * @since 2020/12/08 13:49
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VerifyPositiveIntegerSupport.class)
@Documented
public @interface VerifyPositiveInteger {

    /**
     * 默认校验失败的信息
     *
     * @return 校验失败的信息
     */
    String message() default "must be greater than 0";

    /**
     * 指定要校验的分组，默认不分组
     *
     * @return 指定要校验的分组
     */
    Class<?>[] groups() default {};

    /**
     * 负载类
     *
     * @return 负载类
     */
    Class<? extends Payload>[] payload() default {};

}
