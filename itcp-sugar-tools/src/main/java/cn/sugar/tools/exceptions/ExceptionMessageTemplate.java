package cn.sugar.tools.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常模板信息
 *
 * @author wjm
 * @date 2021/2/1 20:38
 */
@Getter
@AllArgsConstructor
public enum ExceptionMessageTemplate {

    NULL_CHECK("It is not allowed to be null here"),
    EMPTY_CHECK("It is not allowed to be empty here"),
    BLANK_CHECK("It is not allowed to be blank here"),
    TRUE_CHECK("It is not allowed to be true here"),
    FALSE_CHECK("It is not allowed to be false here"),
    POSITIVE_CHECK("It is not allowed to be positive here"),
    EQUALS_CHECK("It is not allowed to be equal here"),
    NULL_NEED("This must be null"),
    EMPTY_NEED("This must be empty"),
    BLANK_NEED("This must be blank"),
    TRUE_NEED("This must be true"),
    FALSE_NEED("This must be false"),
    POSITIVE_NEED("This must be positive"),
    EQUALS_NEED("This must be equal"),
    UNSUPPORTED_OPERATE("unsupported operation"),
    UNSUPPORTED_PARAMETERS("unsupported parameters");

    private final String exceptionMessage;

}
