package org.horizon.sdk.library.java.tool.validation.yavi.message;

import am.ik.yavi.message.MessageFormatter;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import javax.annotation.Nonnull;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author wjm
 * @since 2025-04-17 16:55
 */
@NoArgsConstructor
public class ValidationMessageFormatter implements MessageFormatter {

    public static final ValidationMessageFormatter INSTANCE = new ValidationMessageFormatter();

    private static final String BUNDLE_NAME = "yavi_message";

    @Override
    @Nonnull
    public String format(@Nonnull String messageKey, @Nonnull String defaultMessageFormat, @Nonnull Object[] arguments, @Nonnull Locale locale) {
        ValidationMessageType validationMessageType = Converts.toEnumByValue(messageKey, ValidationMessageType.class);
        if (Nil.isNotNull(validationMessageType)) {
            return Strings.format(validationMessageType.getMessageFormat(), arguments);
        }

        ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, Locale.CHINA);
        return Strings.format(resourceBundle.getString(messageKey), arguments);
    }

}