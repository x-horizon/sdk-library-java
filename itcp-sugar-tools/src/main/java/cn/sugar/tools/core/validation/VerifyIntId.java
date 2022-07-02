package cn.sugar.tools.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * ID 有效性注解，使用 @VerifyId 标记在实体类字段上，校验 id 不可为 null 且必须大于 0
 *
 * @author wjm
 * @date 2020/12/08 13:49
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VerifyIntIdSupport.class)
@Documented
public @interface VerifyIntId {

    /**
     * 默认校验失败的信息
     *
     * @return
     */
    String message() default "无效的ID";

    /**
     * 指定要校验的分组，默认不分组
     *
     * @return
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
