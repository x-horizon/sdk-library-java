package cn.srd.library.contract.throwable.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常模板信息
 *
 * @author wjm
 * @since 2021/2/1 20:38
 */
@Getter
@AllArgsConstructor
public enum ExceptionMessageTemplate {

    /**
     * 必须不为 null 异常信息
     */
    NULL_CHECK("It is not allowed to be null here"),

    /**
     * 必须不为 empty 异常信息
     */
    EMPTY_CHECK("It is not allowed to be empty here"),

    /**
     * 必须不为 blank 异常信息
     */
    BLANK_CHECK("It is not allowed to be blank here"),

    /**
     * 必须不为 true 异常信息
     */
    TRUE_CHECK("It is not allowed to be true here"),

    /**
     * 必须不为 false 异常信息
     */
    FALSE_CHECK("It is not allowed to be false here"),

    /**
     * 必须不为 正整数 异常信息
     */
    POSITIVE_CHECK("It is not allowed to be positive here"),

    /**
     * 必须 不相等 异常信息
     */
    EQUALS_CHECK("It is not allowed to be equal here"),

    /**
     * 必须为 null 异常信息
     */
    NULL_NEED("This must be null"),

    /**
     * 必须为 empty 异常信息
     */
    EMPTY_NEED("This must be empty"),

    /**
     * 必须为 blank 异常信息
     */
    BLANK_NEED("This must be blank"),

    /**
     * 必须为 true 异常信息
     */
    TRUE_NEED("This must be true"),

    /**
     * 必须为 false 异常信息
     */
    FALSE_NEED("This must be false"),

    /**
     * 必须为 正整数 异常信息
     */
    POSITIVE_NEED("This must be positive"),

    /**
     * 必须 相等 异常信息
     */
    EQUALS_NEED("This must be equal"),

    /**
     * 不支持的操作 异常信息
     */
    UNSUPPORTED_OPERATE("unsupported operation"),

    /**
     * 不支持的参数 异常信息
     */
    UNSUPPORTED_PARAMETERS("unsupported parameters");

    /**
     * 异常信息
     */
    private final String exceptionMessage;

}
