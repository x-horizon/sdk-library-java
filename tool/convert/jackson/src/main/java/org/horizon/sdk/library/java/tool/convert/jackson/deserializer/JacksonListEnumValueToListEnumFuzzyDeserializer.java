package org.horizon.sdk.library.java.tool.convert.jackson.deserializer;

import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.model.throwable.LibraryJavaInternalException;
import org.horizon.sdk.library.java.tool.convert.jackson.JacksonFieldNameRegister;
import org.horizon.sdk.library.java.tool.convert.jackson.Jacksons;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.enums.Enums;
import org.horizon.sdk.library.java.tool.lang.functional.Assert;
import org.horizon.sdk.library.java.tool.lang.object.Classes;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.object.Types;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.List;

/**
 * the jackson deserializer to convert {@link List}&lt;the enum internal int value&gt; to {@link List}<{@link Enum}<?>>, it suitable for scenarios where the json field name is different from the class field name, see {@link Converts#toEnumByValue(Object, Class)}
 *
 * @param <E> the data type after deserialize
 * @author wjm
 * @since 2023-06-16 17:26
 */
public class JacksonListEnumValueToListEnumFuzzyDeserializer<E extends Enum<E>> extends JsonDeserializer<List<E>> {

    @SneakyThrows
    @Override
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public List<E> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        String jsonFieldName = jsonParser.currentName();
        Class<?> fieldOfClass = jsonParser.getParsingContext().getParent().getCurrentValue().getClass();

        Field matchField = null;
        String actualFieldName = JacksonFieldNameRegister.getInstance().getClassFieldName(jsonFieldName);
        if (Nil.isNotBlank(actualFieldName)) {
            matchField = Classes.getFieldDeep(fieldOfClass, actualFieldName);
        }
        if (Nil.isNull(matchField)) {
            matchField = Classes.getFieldFuzzy(fieldOfClass, jsonFieldName);
        }

        Assert.of().setMessage("jackson deserializer: cannot deserializer field [{}] on class [{}] because the json field name [{}] unable to match field, please check!", jsonFieldName, fieldOfClass.getSimpleName())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfNull(matchField);

        Class<?> fieldGenericType = Types.getEmbedGenericTypeClass(fieldOfClass, matchField.getName());
        Assert.of().setMessage("jackson deserializer: cannot deserializer field [{}] on class [{}] because the generic type in List.class is not Enum, please check!", jsonFieldName, fieldOfClass.getSimpleName())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfTrue(Enums.isNotEnum(fieldGenericType));

        return Converts.toList(
                jsonParser.readValuesAs(Jacksons.<List<?>>newTypeReference()).next(),
                filedValue -> Converts.toEnumByValue(filedValue, (Class<E>) fieldGenericType)
        );
    }

}