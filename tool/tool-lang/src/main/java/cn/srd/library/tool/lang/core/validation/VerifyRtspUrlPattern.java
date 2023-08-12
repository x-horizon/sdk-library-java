package cn.srd.library.tool.lang.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * 校验一个字符串是否为rtsp格式，例如：rtsp://127.0.0.1:8080/ch01/0
 *
 * @author xiongjing
 * @see VerifyRtspUrlPatternSupport
 * @since 2023-03-10 15:29:13
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {VerifyRtspUrlPatternSupport.class})
@Documented
public @interface VerifyRtspUrlPattern {
    /**
     * 默认校验失败的信息
     *
     * @return 校验失败的信息
     */
    String message() default "format not supported";

    /**
     * 指定要校验的分组，默认不分组
     *
     * @return 指定要校验的分组
     */
    Class<?>[] groups() default {};

    /**
     * 负载类
     *
     * @return 负载类
     */
    Class<? extends Payload>[] payload() default {};

}
