package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import org.horizon.sdk.library.java.tool.lang.time.Times;

import java.time.LocalDateTime;

/**
 * the jackson serializer to convert {@code LocalDateTime} to {@code Long}
 *
 * @author wjm
 * @since 2022-10-14 18:16
 */
public class JacksonLocalDateTimeToLongSerializer extends JacksonSerializeToNumberSerializer<LocalDateTime, Long> {

    @Override
    public Long getTargetValue(LocalDateTime sourceValue) {
        return Times.toLong(sourceValue);
    }

}