package org.horizon.sdk.library.java.tool.convert.jackson.deserializer;

import org.horizon.sdk.library.java.tool.lang.time.Times;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;

/**
 * the jackson deserializer to convert {@link Long} to {@link LocalDateTime}
 *
 * @author wjm
 * @since 2022-10-28 11:18
 */
public class JacksonLongToLocalDateTimeDeserializer extends StdConverter<Long, LocalDateTime> {

    @Override
    public LocalDateTime convert(Long from) {
        return Times.toLocalDateTime(from);
    }

}