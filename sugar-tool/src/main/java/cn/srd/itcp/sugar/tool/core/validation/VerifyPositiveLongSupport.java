package cn.srd.itcp.sugar.tool.core.validation;

import cn.srd.itcp.sugar.tool.core.object.Objects;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Long 类型数字的正整数校验注解（必须大于 0）
 *
 * @author wjm
 * @since 2020/12/08 13:49
 */
public class VerifyPositiveLongSupport implements ConstraintValidator<VerifyPositiveLong, Long> {

    @Override
    public void initialize(VerifyPositiveLong verifyPositiveLong) {

    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        return !Objects.isNotPositive(value);
    }

}
