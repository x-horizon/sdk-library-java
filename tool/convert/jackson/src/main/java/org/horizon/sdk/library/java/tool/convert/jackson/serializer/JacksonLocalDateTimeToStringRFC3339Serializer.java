package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import org.horizon.sdk.library.java.tool.lang.time.Times;

import java.time.LocalDateTime;

/**
 * the jackson serializer to convert {@code LocalDateTime} to RFC3339 {@code String} like "2006-01-02T15:04:05Z07:00"
 *
 * @author wjm
 * @since 2023-03-28 10:00
 */
public class JacksonLocalDateTimeToStringRFC3339Serializer extends JacksonSerializeToStringSerializer<LocalDateTime, String> {

    @Override
    public String getTargetValue(LocalDateTime sourceValue) {
        return Times.toStringWithRFC3339DateTime(sourceValue);
    }

}