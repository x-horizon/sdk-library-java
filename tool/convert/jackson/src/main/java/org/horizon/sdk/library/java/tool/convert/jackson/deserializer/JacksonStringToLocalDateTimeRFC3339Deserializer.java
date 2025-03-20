package org.horizon.sdk.library.java.tool.convert.jackson.deserializer;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.horizon.sdk.library.java.contract.constant.time.TimePatternConstant;
import org.horizon.sdk.library.java.tool.lang.time.Times;

import java.time.LocalDateTime;

/**
 * the jackson deserializer to convert RFC3339 {@code String} like "2006-01-02T15:04:05Z07:00" to {@code LocalDateTime}
 *
 * @author wjm
 * @since 2023-03-28 10:00
 */
public class JacksonStringToLocalDateTimeRFC3339Deserializer extends StdConverter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String sourceValue) {
        return Times.toLocalDateTime(sourceValue, TimePatternConstant.DATETIME_RFC3339_EAST_EIGHTH_TIMEZONE);
    }

}