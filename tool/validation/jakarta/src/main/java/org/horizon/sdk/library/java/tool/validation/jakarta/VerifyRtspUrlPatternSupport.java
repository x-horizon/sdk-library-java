package org.horizon.sdk.library.java.tool.validation.jakarta;

import org.horizon.sdk.library.java.tool.lang.object.Nil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 校验一个字符串是否为rtsp格式，例如：rtsp://127.0.0.1:8080/ch01/0
 *
 * @author xiongjing
 * @since 2023-03-10 15:31
 */
public class VerifyRtspUrlPatternSupport implements ConstraintValidator<VerifyRtspUrlPattern, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Nil.isNotBlank(value)) {
            String regex = "^rtsp://((\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.){3}(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5]):(\\d{1,4}|[1-5]\\d{4}|6[0-4]\\d{3}|65[0-4]\\d{2}|655[0-2]\\d|6553[0-5])/([\\w\\W]*)$";
            return value.matches(regex);
        }
        return false;
    }

}