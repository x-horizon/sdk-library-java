package org.horizon.sdk.library.java.tool.convert.jackson.deserializer;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.horizon.sdk.library.java.tool.lang.time.Times;

import java.time.LocalTime;

/**
 * the jackson deserializer to convert {@code String} to {@code LocalTime}
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