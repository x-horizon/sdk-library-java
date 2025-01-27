package cn.library.java.tool.convert.jackson.deserializer;

import cn.library.java.tool.lang.time.Times;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalTime;

/**
 * the jackson deserializer to convert {@link String} to {@link LocalTime}
 *
 * @author wjm
 * @since 2023-06-21 12:01
 */
public class JacksonStringToLocalTimeDeserializer extends StdConverter<String, LocalTime> {

    @Override
    public LocalTime convert(String from) {
        return Times.toLocalTime(from);
    }

}