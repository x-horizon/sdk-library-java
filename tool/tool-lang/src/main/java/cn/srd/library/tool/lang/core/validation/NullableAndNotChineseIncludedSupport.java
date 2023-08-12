package cn.srd.library.tool.lang.core.validation;

import cn.srd.library.tool.lang.core.StringsUtil;
import cn.srd.library.tool.lang.core.object.Objects;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 验证字符串的格式不能有中文，可以为空字符串
 *
 * @author xiongjing
 * @since 2023-03-11 09:19:45
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
        if (Objects.isBlank(value)) {
            return true;
        }
        return value.length() <= max && !StringsUtil.isChineseIncluded(value);
    }

}
