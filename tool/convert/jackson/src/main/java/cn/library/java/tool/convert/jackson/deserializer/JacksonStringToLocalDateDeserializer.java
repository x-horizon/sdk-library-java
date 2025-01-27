package cn.library.java.tool.convert.jackson.deserializer;

import cn.library.java.tool.lang.time.Times;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * the jackson deserializer to convert {@link String} like "2023-03-27" to {@link LocalDate}
 *
 * @author wjm
 * @since 2022-07-20 11:37
 */
public class JacksonStringToLocalDateDeserializer extends StdConverter<String, LocalDate> {

    @Override
    public LocalDate convert(String from) {
        return Times.toLocalDate(from, DateTimeFormatter.ISO_DATE);
    }

}