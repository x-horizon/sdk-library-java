package cn.srd.library.tool.lang.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * 验证字符串的格式，不能为中文，且长度有限制
 *
 * @author xiongjing
 * @since 2023-03-11 09:15:16
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NullableAndNotChineseIncludedSupport.class})
@Documented
public @interface NullableAndNotChineseIncluded {

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
