package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import org.horizon.sdk.library.java.tool.lang.time.Times;

import java.time.LocalDateTime;

/**
 * the jackson serializer to convert {@code LocalDateTime} to {@code String} like "2011-12-03 10:15:30.974515"
 *
 * @author wjm
 * @since 2023-07-27 21:50
 */
public class JacksonLocalDateTimeToStringMS6Serializer extends JacksonSerializeToStringSerializer<LocalDateTime, String> {

    @Override
    public String getTargetValue(LocalDateTime sourceValue) {
        return Times.toStringWithDateTimeMS6(sourceValue);
    }

}