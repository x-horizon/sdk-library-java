package cn.srd.itcp.sugar.tools.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 用于验证数据类型必须为数字类型
 *
 * @author wjm
 * @date 2022-07-20 11:18:25
 * @see RequireNumber
 */
public class RequireNumberSupport implements ConstraintValidator<RequireNumber, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return value instanceof Number;
    }

}
