package cn.srd.library.java.tool.validation.jakarta;

import cn.srd.library.java.tool.lang.number.Numbers;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Short 类型数字的正整数校验注解（必须大于 0）
 *
 * @author wjm
 * @since 2020/12/08 13:49
 */
public class VerifyPositiveShortSupport implements ConstraintValidator<VerifyPositiveShort, Short> {

    @Override
    public void initialize(VerifyPositiveShort verifyPositiveLong) {

    }

    @Override
    public boolean isValid(Short value, ConstraintValidatorContext constraintValidatorContext) {
        return Numbers.isPositive(value);
    }

}
