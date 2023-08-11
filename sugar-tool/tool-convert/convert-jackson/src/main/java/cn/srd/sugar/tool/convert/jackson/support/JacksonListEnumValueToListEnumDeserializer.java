package cn.srd.sugar.tool.convert.jackson.support;

import cn.srd.sugar.tool.convert.jackson.core.Jacksons;
import cn.srd.sugar.tool.convert.jackson.exception.JacksonDeserializerException;
import cn.srd.sugar.tool.lang.core.CollectionsUtil;
import cn.srd.sugar.tool.lang.core.EnumsUtil;
import cn.srd.sugar.tool.lang.core.StringsUtil;
import cn.srd.sugar.tool.lang.core.TypesUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.util.List;

/**
 * Jackson 反序列化处理器：List&lt;Enum 属性值&gt; =&gt; List&lt;Enum&gt;
 *
 * @author wjm
 * @since 2022-09-13 09:45:13
 */
public class JacksonListEnumValueToListEnumDeserializer<E extends Enum<E>> extends JsonDeserializer<List<E>> {

    @SneakyThrows
    @Override
    @SuppressWarnings("unchecked")
    public List<E> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        String jsonFieldName = jsonParser.getCurrentName();
        Class<?> fieldOfClass = jsonParser.getParsingContext().getParent().getCurrentValue().getClass();
        Class<?> fieldGenericType = TypesUtil.getEmbedGenericTypeClass(fieldOfClass, jsonFieldName);
        if (EnumsUtil.isNotEnum(fieldGenericType)) {
            throw new JacksonDeserializerException(StringsUtil.format("jackson deserializer: cannot deserializer field [{}] on class [{}] because the generic type in List.class is not Enum, please check!", jsonFieldName, fieldOfClass.getSimpleName()));
        }
        return CollectionsUtil.toList(
                jsonParser.readValuesAs(Jacksons.<List<?>>newTypeReference()).next(),
                filedValue -> EnumsUtil.capableToEnum(filedValue, (Class<E>) fieldGenericType)
        );
    }

}
