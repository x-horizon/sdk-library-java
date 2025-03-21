package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import org.horizon.sdk.library.java.tool.lang.convert.Converts;

/**
 * the jackson serializer to convert {@code Long} to {@code String}
 *
 * @author wjm
 * @since 2022-10-13 10:26
 */
public class JacksonLongToStringSerializer extends JacksonSerializeToStringSerializer<Long, String> {

    @Override
    public String getTargetValue(Long sourceValue) {
        return Converts.toString(sourceValue);
    }

}