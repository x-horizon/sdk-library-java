package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import org.horizon.sdk.library.java.tool.lang.convert.Converts;

/**
 * the jackson serializer to convert {@code Double} to {@code String}
 *
 * @author wjm
 * @since 2024-07-23 19:46
 */
public class JacksonDoubleToStringSerializer extends JacksonSerializeToStringSerializer<Double, String> {

    @Override
    public String getTargetValue(Double sourceValue) {
        return Converts.toString(sourceValue);
    }

}