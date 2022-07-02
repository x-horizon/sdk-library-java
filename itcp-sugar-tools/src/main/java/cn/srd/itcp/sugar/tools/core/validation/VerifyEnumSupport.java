package cn.srd.itcp.sugar.tools.core.validation;

import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.core.enums.EnumsUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 枚举有效性校验
 *
 * @author wjm
 * @date 2020/12/08 13:49
 */
public class VerifyEnumSupport implements ConstraintValidator<VerifyEnum, Object> {

    private VerifyEnum verifyEnum;

    @Override
    public void initialize(VerifyEnum constraintAnnotation) {
        verifyEnum = constraintAnnotation;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (verifyEnum.ignoreEmpty()) {
            return true;
        }

        Class enumClass = verifyEnum.enumClass();
        if (Objects.isEmpty(value) || !enumClass.isEnum()) {
            return false;
        }

        return Objects.isNotEmpty(EnumsUtil.capableToEnum(value, enumClass));
    }

}
