package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.enums.Enums;

import java.util.List;

/**
 * the jackson serializer to convert {@code List<Enum<E>>} to {@code List<the enum internal long value>}, see {@link Enums#getFieldValue(Enum, Class)}
 *
 * @author wjm
 * @since 2022-09-16 16:24
 */
public class JacksonListEnumToListLongSerializer<E extends Enum<E>> extends JacksonSerializeToArraySerializer<List<Enum<E>>, List<Long>> {

    @Override
    public List<Long> getTargetValue(List<Enum<E>> sourceValues) {
        return Converts.toArrayList(sourceValues, sourceValue -> Enums.getFieldValue(sourceValue, Long.class));
    }

}