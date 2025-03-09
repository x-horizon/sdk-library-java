package org.horizon.sdk.library.java.tool.validation.jakarta;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

/**
 * 验证字符串的格式不能有中文，可以为空字符串
 *
 * @author xiongjing
 * @since 2023-03-11 09:19
 */
public class NullableAndNotChineseIncludedSupport implements ConstraintValidator<NullableAndNotChineseIncluded, String> {

    /**
     * 字符串长度的最大值
     */
    private int max;

    @Override
    public void initialize(NullableAndNotChineseIncluded constraintAnnotation) {
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Nil.isBlank(value)) {
            return true;
        }
        return value.length() <= max && Strings.notContainsChinese(value);
    }

}