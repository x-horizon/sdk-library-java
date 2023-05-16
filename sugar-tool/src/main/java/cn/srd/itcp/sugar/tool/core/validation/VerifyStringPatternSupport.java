package cn.srd.itcp.sugar.tool.core.validation;

import cn.srd.itcp.sugar.tool.core.Objects;
import cn.srd.itcp.sugar.tool.core.StringsUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 验证字符串的格式不能有中文，可以为空字符串
 *
 * @author xiongjing
 * @since 2023-03-11 09:19:45
 */
public class VerifyStringPatternSupport implements ConstraintValidator<VerifyStringPattern, String> {

    /**
     * 字符串长度的最大值
     */
    private int max = 0;

    @Override
    public void initialize(VerifyStringPattern constraintAnnotation) {
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.isBlank(value)) {
            return true;
        }
        return value.length() <= max && !StringsUtil.isChineseIncluded(value);
    }

}
