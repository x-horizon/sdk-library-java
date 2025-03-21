package org.horizon.sdk.library.java.tool.convert.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;
import org.horizon.sdk.library.java.tool.lang.time.Times;

import java.time.LocalTime;

/**
 * the jackson deserializer to convert {@code String} to {@code LocalTime}
 *
 * @author wjm
 * @since 2023-06-21 12:01
 */
public class JacksonStringToLocalTimeDeserializer extends JsonDeserializer<LocalTime> {

    @SneakyThrows
    @Override
    public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return Times.toLocalTime(jsonParser.getValueAsString());
    }

}