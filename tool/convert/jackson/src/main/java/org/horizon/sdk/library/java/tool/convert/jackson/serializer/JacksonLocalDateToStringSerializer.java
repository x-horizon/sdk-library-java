package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import org.horizon.sdk.library.java.tool.lang.time.Times;

import java.time.LocalDate;

/**
 * the jackson serializer to convert {@code LocalDate} to {@code String} like "2023-03-27"
 *
 * @author wjm
 * @since 2023-06-21 12:01
 */
public class JacksonLocalDateToStringSerializer extends JacksonSerializeToStringSerializer<LocalDate, String> {

    @Override
    public String getTargetValue(LocalDate sourceValue) {
        return Times.toStringYearMonthDay(sourceValue);
    }

}