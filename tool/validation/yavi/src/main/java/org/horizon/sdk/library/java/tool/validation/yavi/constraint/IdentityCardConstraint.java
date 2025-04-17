package org.horizon.sdk.library.java.tool.validation.yavi.constraint;

import am.ik.yavi.core.CustomConstraint;
import org.horizon.sdk.library.java.tool.lang.text.IdentityCards;
import org.horizon.sdk.library.java.tool.validation.yavi.message.ValidationMessageType;

import javax.annotation.Nonnull;

/**
 * @author wjm
 * @since 2025-04-17 17:54
 */
public class IdentityCardConstraint implements CustomConstraint<String> {

    public static IdentityCardConstraint of() {
        return new IdentityCardConstraint();
    }

    @Override
    public boolean test(String identityCard) {
        return IdentityCards.isValid(identityCard);
    }

    @Override
    @Nonnull
    public String messageKey() {
        return ValidationMessageType.CHAR_SEQUENCE_NOT_VALID_IDENTITY_CARD.getMessageKey();
    }

    @Override
    @Nonnull
    public String defaultMessageFormat() {
        return ValidationMessageType.CHAR_SEQUENCE_NOT_VALID_IDENTITY_CARD.getMessageFormat();
    }

}