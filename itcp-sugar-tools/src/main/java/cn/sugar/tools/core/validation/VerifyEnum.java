package cn.sugar.tools.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 枚举有效性注解，使用 @VerifyEnum 标记在实体类字段上，校验枚举的属性值是否可以转换为对应的枚举
 *
 * @author wjm
 * @date 2020/12/08 13:49
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VerifyEnumSupport.class)
@Documented
public @interface VerifyEnum {

    /**
     * 默认校验失败的信息
     *
     * @return
     */
    String message() default "无效的枚举";

    /**
     * 指定要校验的枚举类
     *
     * @return
     */
    Class enumClass();

    /**
     * 是否允许空值，默认允许空值
     *
     * @return
     */
    boolean ignoreEmpty() default true;

    /**
     * 指定要校验的分组，默认不分组
     *
     * @return
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
