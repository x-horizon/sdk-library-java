package cn.srd.itcp.sugar.component.convert.jackson.support;

import cn.srd.itcp.sugar.tool.core.TimeUtil;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;

/**
 * Jackson 反序列化处理器：Long =&gt; LocalDateTime
 *
 * @author wjm
 * @since 2022-10-28 11:18:19
 */
public class JacksonLongToLocalDateTimeDeserializer extends StdConverter<Long, LocalDateTime> {

    @Override
    public LocalDateTime convert(Long value) {
        return TimeUtil.toLocalDateTime(value);
    }

}
