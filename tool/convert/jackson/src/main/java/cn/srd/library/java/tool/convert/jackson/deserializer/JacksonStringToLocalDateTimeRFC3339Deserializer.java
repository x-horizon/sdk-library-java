package cn.srd.library.java.tool.convert.jackson.deserializer;

import cn.srd.library.java.contract.constant.time.TimePatternConstant;
import cn.srd.library.java.tool.lang.time.Times;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;

/**
 * the jackson deserializer to convert RFC3339 {@link String} like "2006-01-02T15:04:05Z07:00" to {@link LocalDateTime}
 *
 * @author wjm
 * @since 2023-03-28 10:00
 */
public class JacksonStringToLocalDateTimeRFC3339Deserializer extends StdConverter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String from) {
        return Times.toLocalDateTime(from, TimePatternConstant.DATETIME_RFC3339_EAST_EIGHTH_TIMEZONE);
    }

}