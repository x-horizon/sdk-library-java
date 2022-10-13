package cn.srd.itcp.sugar.tools.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Long 类型数字的正整数校验注解（必须大于 0）
 *
 * @author wjm
 * @date 2020/12/08 13:49
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VerifyPositiveLongSupport.class)
@Documented
public @interface VerifyPositiveLong {

    /**
     * 默认校验失败的信息
     *
     * @return
     */
    String message() default "must be greater than 0";

    /**
     * 指定要校验的分组，默认不分组
     *
     * @return
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
