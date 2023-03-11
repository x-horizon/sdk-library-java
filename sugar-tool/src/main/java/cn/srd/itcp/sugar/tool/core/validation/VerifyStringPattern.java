package cn.srd.itcp.sugar.tool.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * 验证字符串的格式，只能由数字、字符、下划线构成
 *
 * @author xiongjing
 * @since 2023-03-11 09:15:16
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {VerifyStringPatternSupport.class})
@Documented
public @interface VerifyStringPattern {

    /**
     * 默认校验失败的信息
     *
     * @return 校验失败的信息
     */
    String message() default "format not supported";

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

    /**
     * 字符串的长度的最大值
     *
     * @return 字符串的长度的最大值
     */
    int max() default Integer.MAX_VALUE;

}
