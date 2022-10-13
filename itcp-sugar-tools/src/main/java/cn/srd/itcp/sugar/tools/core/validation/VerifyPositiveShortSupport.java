package cn.srd.itcp.sugar.tools.core.validation;

import cn.srd.itcp.sugar.tools.core.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Short 类型数字的正整数校验注解（必须大于 0）
 *
 * @author wjm
 * @date 2020/12/08 13:49
 */
public class VerifyPositiveShortSupport implements ConstraintValidator<VerifyPositiveLong, Short> {

    @Override
    public void initialize(VerifyPositiveLong verifyPositiveLong) {

    }

    @Override
    public boolean isValid(Short value, ConstraintValidatorContext constraintValidatorContext) {
        return !Objects.isNotPositive(value);
    }

}
