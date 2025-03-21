package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import org.horizon.sdk.library.java.tool.lang.enums.Enums;

/**
 * the jackson serializer to convert {@code Enum<E>} to the enum internal long value, see {@link Enums#getFieldValue(Enum, Class)}
 *
 * @author wjm
 * @since 2020-12-15 17:02
 */
public class JacksonEnumToLongSerializer<E extends Enum<E>> extends JacksonSerializeToNumberSerializer<Enum<E>, Long> {

    @Override
    public Long getTargetValue(Enum<E> sourceValue) {
        return Enums.getFieldValue(sourceValue, Long.class);
    }

}