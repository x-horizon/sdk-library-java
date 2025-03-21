package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import org.horizon.sdk.library.java.tool.lang.convert.Converts;

/**
 * the jackson serializer to convert {@code Float} to {@code String}
 *
 * @author wjm
 * @since 2024-07-23 19:46
 */
public class JacksonFloatToStringSerializer extends JacksonSerializeToStringSerializer<Float, String> {

    @Override
    public String getTargetValue(Float sourceValue) {
        return Converts.toString(sourceValue);
    }

}