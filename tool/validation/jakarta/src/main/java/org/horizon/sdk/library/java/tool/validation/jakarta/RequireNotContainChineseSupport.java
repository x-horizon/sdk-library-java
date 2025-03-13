package org.horizon.sdk.library.java.tool.validation.jakarta;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

/**
 * 用于验证字符串类型的字段不包含中文字符
 *
 * @author xiongjing
 * @see RequireNotContainChinese
 * @since 2022-11-20 13:49
 */
public class RequireNotContainChineseSupport implements ConstraintValidator<RequireNotContainChinese, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Strings.notContainsChinese(value);
    }

}