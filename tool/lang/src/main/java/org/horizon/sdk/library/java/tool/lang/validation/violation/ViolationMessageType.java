package org.horizon.sdk.library.java.tool.lang.validation.violation;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2025-04-19 01:20
 */
@Getter
@AllArgsConstructor
public enum ViolationMessageType {

    NULL("“{}”必须为空"),
    NOT_NULL("“{}”不可为空"),

    EMPTY(NULL.getMessage()),
    NOT_EMPTY(NOT_NULL.getMessage()),

    BLANK(NULL.getMessage()),
    NOT_BLANK(NOT_NULL.getMessage()),

    EQUALS("“{}”的值必须为“{}”"),
    NOT_EQUALS("“{}”的值必须不为“{}”"),

    NUMBER_EQUALS("“{}”必须等于“{}”"),
    NUMBER_NOT_EQUALS("“{}”必须不等于“{}”"),
    NUMBER_GREATER_THAN("“{}”必须大于“{}”"),
    NUMBER_GREATER_THAN_OR_EQUALS("“{}”必须大于或等于“{}”"),
    NUMBER_LESS_THAN("“{}”必须小于“{}”"),
    NUMBER_LESS_THAN_OR_EQUALS("“{}”必须小于或等于“{}”"),

    CHAR_SEQUENCE_LENGTH_EQUALS("“{}”的长度必须等于“{}”"),
    CHAR_SEQUENCE_LENGTH_NOT_EQUALS("“{}”的长度必须不等于“{}”"),
    CHAR_SEQUENCE_LENGTH_GREATER_THAN("“{}”的长度必须大于“{}”"),
    CHAR_SEQUENCE_LENGTH_GREATER_THAN_OR_EQUALS("“{}”的长度必须大于或等于“{}”"),
    CHAR_SEQUENCE_LENGTH_LESS_THAN("“{}”的长度必须小于“{}”"),
    CHAR_SEQUENCE_LENGTH_LESS_THAN_OR_EQUALS("“{}”的长度必须小于或等于“{}”"),

    STRING_INVALID_UUID("“{}”必须为有效的UUID"),
    STRING_INVALID_HEX("“{}”必须为有效的16进制字符串"),
    STRING_INVALID_URL("“{}”必须为有效的URL"),
    STRING_INVALID_IPV4("“{}”必须为有效的IPV4地址"),
    STRING_INVALID_IPV6("“{}”必须为有效的IPV6地址"),
    STRING_INVALID_MAC_ADDRESS("“{}”必须为有效的MAC地址"),
    STRING_INVALID_EMAIL("“{}”必须为有效的邮箱"),
    STRING_INVALID_EMAIL_QQ("“{}”必须为有效的QQ邮箱"),
    STRING_INVALID_IDENTITY_CARD("“{}”必须为有效的身份证"),
    STRING_INVALID_UNIFY_SOCIAL_CREDIT_CODE("“{}”必须为有效的统一社会信用代码"),
    STRING_INVALID_POSTCODE("“{}”必须为有效的邮政编码"),
    STRING_INVALID_BIRTHDAY("“{}”必须为有效的生日格式"),
    STRING_INVALID_MOBILE_CHINA("“{}”必须为有效的中国手机号码"),
    STRING_INVALID_MOBILE_CHINA_HONG_KONG("“{}”必须为有效的中国香港手机号码"),
    STRING_INVALID_MOBILE_CHINA_MACAO("“{}”必须为有效的中国澳门手机号码"),
    STRING_INVALID_MOBILE_CHINA_TAIWAN("“{}”必须为有效的中国台湾手机号码"),
    STRING_INVALID_PHONE("“{}”必须为有效的号码"),
    STRING_INVALID_PLATE_NUMBER_CHINA("“{}”必须为有效的中国车牌号码"),
    STRING_INVALID_CAR_VIN("“{}”必须为有效的车架号码"),
    STRING_INVALID_DRIVER_LICENCE("“{}”必须为有效的驾驶证"),
    STRING_INVALID_MONEY("“{}”必须为有效的货币格式"),

    ITERABLE_SIZE_EQUALS("“{}”的大小必须等于“{}”"),
    ITERABLE_SIZE_NOT_EQUALS("“{}”的大小必须不等于“{}”"),
    ITERABLE_SIZE_GREATER_THAN("“{}”的大小必须大于“{}”"),
    ITERABLE_SIZE_GREATER_THAN_OR_EQUALS("“{}”的大小必须大于或等于“{}”"),
    ITERABLE_SIZE_LESS_THAN("“{}”的大小必须小于“{}”"),
    ITERABLE_SIZE_LESS_THAN_OR_EQUALS("“{}”的大小必须小于或等于“{}”"),

    ;

    private final String message;

}