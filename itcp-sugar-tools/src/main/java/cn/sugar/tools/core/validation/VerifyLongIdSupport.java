package cn.sugar.tools.core.validation;

import cn.sugar.tools.core.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Long ID 有效性校验
 *
 * @author wjm
 * @date 2020/12/08 13:49
 */
public class VerifyLongIdSupport implements ConstraintValidator<VerifyLongId, Long> {

    @Override
    public void initialize(VerifyLongId verifyLongId) {

    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        return !Objects.isNotPositive(value);
    }

}
