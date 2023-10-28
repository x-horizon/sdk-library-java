package cn.srd.library.java.tool.validation.jakarta;

import cn.hutool.json.JSONUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 用于验证数据类型必须为可转换为 JSON 对象的字符串
 *
 * @author wjm
 * @see RequireJsonString
 * @since 2022-08-03 14:24:41
 */
public class RequireJsonStringSupport implements ConstraintValidator<RequireJsonString, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return value instanceof String check && JSONUtil.isTypeJSONObject(check);
    }

}
