package org.horizon.sdk.library.java.tool.convert.jackson.deserializer;

import org.horizon.sdk.library.java.contract.constant.time.TimePatternConstant;
import org.horizon.sdk.library.java.tool.lang.time.Times;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;

/**
 * the jackson deserializer to convert {@link String} like "2023-03-27 21:41:07.76971" to {@link LocalDateTime}
 *
 * @author wjm
 * @since 2023-03-28 10:00
 */
public class JacksonStringToLocalDateTimeMS5Deserializer extends StdConverter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String from) {
        return Times.toLocalDateTime(from, TimePatternConstant.DATETIME_MS5);
    }

}