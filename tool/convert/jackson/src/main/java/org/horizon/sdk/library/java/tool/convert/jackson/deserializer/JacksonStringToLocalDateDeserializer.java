package org.horizon.sdk.library.java.tool.convert.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;
import org.horizon.sdk.library.java.tool.lang.time.Times;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * the jackson deserializer to convert {@code String} like "2023-03-27" to {@code LocalDate}
 *
 * @author wjm
 * @since 2022-07-20 11:37
 */
public class JacksonStringToLocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @SneakyThrows
    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return Times.toLocalDate(jsonParser.getValueAsString(), DateTimeFormatter.ISO_DATE);
    }

}