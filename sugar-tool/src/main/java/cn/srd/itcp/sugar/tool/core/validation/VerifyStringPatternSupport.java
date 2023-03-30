package cn.srd.itcp.sugar.tool.core.validation;

import cn.srd.itcp.sugar.tool.core.Objects;
import cn.srd.itcp.sugar.tool.core.StringsUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 验证字符串的格式，数字、字母、下划线三种随机组合，头尾不能为空格，字符串中间可夹空格，但不能输入换行符。
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
        if (value.length() > max || Objects.isBlank(value)) {
            return false;
        }
        return !StringsUtil.isChineseIncluded(value);
    }

}
