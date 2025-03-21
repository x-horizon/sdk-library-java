package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.enums.Enums;

import java.util.List;

/**
 * the jackson serializer to convert {@code List<Enum<E>>} to {@code List<the enum internal string value>}, see {@link Enums#getFieldValue(Enum, Class)}
 *
 * @author wjm
 * @since 2024-04-16 21:32
 */
public class JacksonListEnumToListStringSerializer<E extends Enum<E>> extends JacksonSerializeToArraySerializer<List<Enum<E>>, List<String>> {

    @Override
    public List<String> getTargetValue(List<Enum<E>> sourceValues) {
        return Converts.toList(sourceValues, sourceValue -> Enums.getFieldValue(sourceValue, String.class));
    }

}