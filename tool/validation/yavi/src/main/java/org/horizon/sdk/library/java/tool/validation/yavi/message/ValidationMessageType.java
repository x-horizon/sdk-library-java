package org.horizon.sdk.library.java.tool.validation.yavi.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2025-04-17 17:47
 */
@Getter
@AllArgsConstructor
public enum ValidationMessageType {

    CHAR_SEQUENCE_NOT_VALID_PHONE("charSequence.notValidPhone", "“{}”不是有效的手机号码"), //
    CHAR_SEQUENCE_NOT_VALID_IDENTITY_CARD("charSequence.notValidIdentityCard", "“{}”不是有效的身份证"), //

    ;

    private final String messageKey;

    private final String messageFormat;

}