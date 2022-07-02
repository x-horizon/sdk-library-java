package cn.srd.itcp.sugar.tools.core.validation;

import cn.srd.itcp.sugar.tools.core.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * ID有效性校验
 *
 * @author wjm
 * @date 2020/12/08 13:49
 */
public class VerifyIntIdSupport implements ConstraintValidator<VerifyIntId, Integer> {

    @Override
    public void initialize(VerifyIntId verifyIntId) {

    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return !Objects.isNotPositive(value);
    }

}
