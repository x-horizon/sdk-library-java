package cn.srd.itcp.sugar.tools.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 标记在字段上用于验证数据类型必须为可转换为 JSON 对象的字符串
 *
 * @author wjm
 * @see RequireJsonStringSupport
 * @since 2022-08-03 14:24:41
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequireJsonStringSupport.class)
@Documented
public @interface RequireJsonString {

    /**
     * 校验失败时的信息
     *
     * @return
     */
    String message() default "json string is required";

    /**
     * 要校验的分组
     *
     * @return
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
