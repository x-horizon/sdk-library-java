package cn.srd.itcp.sugar.component.convert.jackson.support;

import cn.srd.itcp.sugar.tool.core.ArraysUtil;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

import java.util.List;

/**
 * Jackson 序列化处理器：List&lt;Long&gt; =&gt; List&lt;String&gt;
 *
 * @author xiongjing
 * @since 2023-03-14 17:57:59
 */
public class JacksonListLongToListStringSerializer extends JsonSerializer<List<Long>> {

    @Override
    @SneakyThrows
    public void serialize(List<Long> from, JsonGenerator jsonGenerator, SerializerProvider serializers) {
        List<String> strings = CollectionsUtil.map(from, param -> Long.toString(param), true);
        jsonGenerator.writeArray(ArraysUtil.toArray(strings, String.class), 0, strings.size());
    }

}
