package org.horizon.sdk.library.java.tool.validation.yavi.constraint;

import am.ik.yavi.core.CustomConstraint;
import org.horizon.sdk.library.java.tool.lang.text.Phones;
import org.horizon.sdk.library.java.tool.validation.yavi.message.ValidationMessageType;

import javax.annotation.Nonnull;

/**
 * @author wjm
 * @since 2025-04-17 17:45
 */
public class PhoneConstraint implements CustomConstraint<String> {

    public static PhoneConstraint of() {
        return new PhoneConstraint();
    }

    @Override
    public boolean test(String phone) {
        return Phones.isValid(phone);
    }

    @Override
    @Nonnull
    public String messageKey() {
        return ValidationMessageType.CHAR_SEQUENCE_NOT_VALID_PHONE.getMessageKey();
    }

    @Override
    @Nonnull
    public String defaultMessageFormat() {
        return ValidationMessageType.CHAR_SEQUENCE_NOT_VALID_PHONE.getMessageFormat();
    }

}