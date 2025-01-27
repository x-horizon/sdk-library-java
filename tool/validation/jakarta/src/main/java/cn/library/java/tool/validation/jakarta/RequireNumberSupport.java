package cn.library.java.tool.validation.jakarta;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 用于验证数据类型必须为数字类型
 *
 * @author wjm
 * @see RequireNumber
 * @since 2022-07-20 11:18
 */
public class RequireNumberSupport implements ConstraintValidator<RequireNumber, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return value instanceof Number;
    }

}