package cn.library.java.tool.convert.jackson.deserializer;

import cn.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.library.java.tool.lang.convert.Converts;
import cn.library.java.tool.lang.enums.Enums;
import cn.library.java.tool.lang.functional.Assert;
import cn.library.java.tool.lang.object.Objects;
import cn.library.java.tool.lang.object.Types;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

/**
 * the jackson deserializer to convert the enum internal int value to {@link Enum}<?>, see {@link Converts#toEnumByValue(Object, Class)}
 *
 * @param <E> the data type after deserialize
 * @author wjm
 * @since 2020-12-15 17:02
 */
public class JacksonEnumValueToEnumDeserializer<E extends Enum<E>> extends JsonDeserializer<E> {

    @SneakyThrows
    @Override
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public E deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        String jsonFieldName = jsonParser.currentName();
        Class<?> fieldOfClass = jsonParser.currentValue().getClass();
        Class<?> fieldType = Types.getTypeClass(fieldOfClass, jsonFieldName);
        Assert.of().setMessage("jackson deserializer: cannot deserializer field [{}] on class [{}] because the generic type in List.class is not Enum, please check!", jsonFieldName, fieldOfClass.getSimpleName())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfTrue(Enums.isNotEnum(fieldType));
        return Converts.toEnumByValue(Objects.getActualValue(jsonParser.getText()), (Class<E>) fieldType);
    }

}