package cn.srd.itcp.sugar.component.convert.jackson.support;

import cn.srd.itcp.sugar.tool.core.time.TimeUtil;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Jackson 反序列化处理器：String =&gt; LocalDate（仅支持如：2023-03-27）
 *
 * @author wjm
 * @since 2022-07-20 11:37:28
 */
public class JacksonStringToLocalDateDeserializer extends StdConverter<String, LocalDate> {

    @Override
    public LocalDate convert(String from) {
        return TimeUtil.parseDate(from, DateTimeFormatter.ISO_DATE);
    }

}
