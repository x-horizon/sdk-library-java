package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import org.horizon.sdk.library.java.tool.lang.enums.Enums;

/**
 * the jackson serializer to convert {@code Enum<E>} to the enum internal int value, see {@link Enums#getFieldValue(Enum, Class)}
 *
 * @author wjm
 * @since 2020-12-15 17:02
 */
public class JacksonEnumToIntegerSerializer<E extends Enum<E>> extends JacksonSerializeToNumberSerializer<Enum<E>, Integer> {

    @Override
    public Integer getTargetValue(Enum<E> sourceValue) {
        return Enums.getFieldValue(sourceValue, Integer.class);
    }

}