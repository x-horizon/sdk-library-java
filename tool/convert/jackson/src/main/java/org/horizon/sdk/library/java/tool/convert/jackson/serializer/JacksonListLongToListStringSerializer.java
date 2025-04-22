package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import org.horizon.sdk.library.java.tool.lang.convert.Converts;

import java.util.List;

/**
 * the jackson serializer to convert {@code List<Long>} to {@code List<String>}
 *
 * @author wjm
 * @since 2023-03-14 17:57
 */
public class JacksonListLongToListStringSerializer extends JacksonSerializeToArraySerializer<List<Long>, List<String>> {

    @Override
    public List<String> getTargetValue(List<Long> sourceValues) {
        return Converts.toArrayList(sourceValues, Converts::toString);
    }

}