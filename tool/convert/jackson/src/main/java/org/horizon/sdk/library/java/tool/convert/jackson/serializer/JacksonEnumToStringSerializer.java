package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import org.horizon.sdk.library.java.tool.lang.enums.Enums;

/**
 * the jackson serializer to convert {@code Enum<E>} to the enum internal string value, see {@link Enums#getFieldValue(Enum, Class)}
 *
 * @author wjm
 * @since 2022-10-27 18:56
 */
public class JacksonEnumToStringSerializer<E extends Enum<E>> extends JacksonSerializeToStringSerializer<Enum<E>, String> {

    @Override
    public String getTargetValue(Enum<E> sourceValue) {
        return Enums.getFieldValue(sourceValue, String.class);
    }

}