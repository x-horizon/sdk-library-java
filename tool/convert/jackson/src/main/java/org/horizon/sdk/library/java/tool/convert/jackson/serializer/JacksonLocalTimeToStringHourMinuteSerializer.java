package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import org.horizon.sdk.library.java.tool.lang.time.Times;

import java.time.LocalTime;

/**
 * the jackson serializer to convert {@code LocalTime} to {@code String} like "14:12"
 *
 * @author wjm
 * @since 2023-06-21 12:01
 */
public class JacksonLocalTimeToStringHourMinuteSerializer extends JacksonSerializeToStringSerializer<LocalTime, String> {

    @Override
    public String getTargetValue(LocalTime sourceValue) {
        return Times.toStringHourMinute(sourceValue);
    }

}