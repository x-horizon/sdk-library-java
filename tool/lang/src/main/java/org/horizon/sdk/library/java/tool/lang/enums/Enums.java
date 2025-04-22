package org.horizon.sdk.library.java.tool.lang.enums;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.contract.constant.enums.EnumConstant;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.compare.Comparators;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.functional.Action;
import org.horizon.sdk.library.java.tool.lang.functional.Functional;
import org.horizon.sdk.library.java.tool.lang.object.Classes;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.object.Types;
import org.horizon.sdk.library.java.tool.lang.reflect.Reflects;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * toolkit for enum
 *
 * @author wjm
 * @since 2021-05-10 17:46
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Enums {

    /**
     * a map with key as identify enum field as unique value and value as enum field value
     */
    private static final Map<String, Object> ENUM_VALUE_CACHE = Collections.newConcurrentHashMap(256);

    /**
     * return true if the checked element is assignable from {@link Enum}
     *
     * @param input the checked element
     * @return return true if the checked element is assignable from {@link Enum}
     */
    public static boolean isEnum(Class<?> input) {
        return Action.<Boolean>ifNull(input)
                .then(Functional.falseSupplier())
                .otherwise(input::isEnum)
                .get();
    }

    /**
     * return true if the checked element is assignable from {@link Enum}
     *
     * @param input the checked element
     * @return return true if the checked element is assignable from {@link Enum}
     */
    public static boolean isEnum(Object input) {
        return Action.<Boolean>ifNull(input)
                .then(Functional.falseSupplier())
                .otherwise(() -> input.getClass().isEnum())
                .get();
    }

    /**
     * return true if the enum field name is any one in {@link EnumConstant#INTERNAL_FIELD_NAMES}
     *
     * @param field the enum field
     * @return return true if the enum field name is any one in {@link EnumConstant#INTERNAL_FIELD_NAMES}
     */
    public static boolean isInternalFieldName(Field field) {
        return isInternalFieldName(field.getName());
    }

    /**
     * return true if the enum field name is any one in {@link EnumConstant#INTERNAL_FIELD_NAMES}
     *
     * @param fieldName the enum field name
     * @return return true if the enum field name is any one in {@link EnumConstant#INTERNAL_FIELD_NAMES}
     */
    public static boolean isInternalFieldName(String fieldName) {
        return EnumConstant.INTERNAL_FIELD_NAMES.stream().anyMatch(internalFieldName -> Comparators.equals(fieldName, internalFieldName));
    }

    /**
     * reverse {@link #isEnum(Class)}
     *
     * @param input the checked element
     * @return return true if the checked element is not assignable from {@link Enum}
     */
    public static boolean isNotEnum(Class<?> input) {
        return !isEnum(input);
    }

    /**
     * reverse {@link #isEnum(Object)}
     *
     * @param input the checked element
     * @return return true if the checked element is not assignable from {@link Enum}
     */
    public static boolean isNotEnum(Object input) {
        return !isEnum(input);
    }

    /**
     * reverse {@link #isInternalFieldName(Field)}
     *
     * @param field the enum field
     * @return return true if the enum field name is all not in {@link EnumConstant#INTERNAL_FIELD_NAMES}
     */
    public static boolean isNotInternalFieldName(Field field) {
        return !(isInternalFieldName(field));
    }

    /**
     * reverse {@link #isInternalFieldName(String)}
     *
     * @param fieldName the enum field name
     * @return return true if the enum field name is all not in {@link EnumConstant#INTERNAL_FIELD_NAMES}
     */
    public static boolean isNotInternalFieldName(String fieldName) {
        return !(isInternalFieldName(fieldName));
    }

    /**
     * <p>get all enum instances.</p>
     *
     * <p>example code:</p>
     * <pre>{@code
     * public enum GenderType {
     *     MAN,
     *     WOMAN,
     *     UNKNOWN;
     *
     *     public static void main(String[] args) {
     *         // returns [MAN, WOMAN, UNKNOWN]
     *         List<GenderType> genderTypes = Enums.getAllFields(GenderType.class);
     *     }
     * }
     * }</pre>
     *
     * @param input the enum class
     * @param <E>   the enum type
     * @return all enum instances
     */
    public static <E extends Enum<E>> List<E> getAllInstances(Class<E> input) {
        return Action.<List<E>>ifNull(input)
                .then(Collections::newArrayList)
                .otherwise(() -> Collections.ofArrayList(input.getEnumConstants()))
                .get();
    }

    /**
     * <p>get enum field value.</p>
     *
     * <p>usage examples:</p>
     * <ul>
     *     <li><b>Typical usage:</b>
     *         <pre>{@code
     * @Getter
     * @AllArgsConstructor
     * public enum GenderType {
     *     MAN(1, "man"),
     *     WOMAN(2, "woman"),
     *     UNKNOWN(3, "unknown");
     *
     *     private final int code;
     *     private final String description;
     *
     *     public static void main(String[] args) {
     *         // prints "woman"
     *         System.out.println(Enums.getValue(GenderType.WOMAN, String.class));
     *         // prints 2
     *         System.out.println(Enums.getValue(GenderType.WOMAN, Integer.class));
     *     }
     * }
     *         }</pre>
     *     </li>
     *
     *     <li><b>Multiple fields:</b> returns first matching type
     *         <pre>{@code
     * @Getter
     * @AllArgsConstructor
     * public enum GenderType {
     *     MAN(1, "man", "Man"),
     *     WOMAN(2, "woman", "Woman"),
     *     UNKNOWN(3, "unknown", "Unknown");
     *
     *     private final int code;
     *     private final String description1;
     *     private final String description2;
     *
     *     public static void main(String[] args) {
     *         // prints "woman" (first String field)
     *         System.out.println(Enums.getValue(GenderType.WOMAN, String.class));
     *     }
     * }
     *         }</pre>
     *     </li>
     *
     *     <li><b>Varargs field:</b> handles array types
     *         <pre>{@code
     * @Getter
     * @AllArgsConstructor
     * public enum GenderType {
     *     MAN("man"),
     *     WOMAN("woman", "Woman", "WOMAN"),
     *     UNKNOWN("unknown", "Unknown");
     *
     *     GenderType(String... names) {
     *         this.names = names;
     *     }
     *
     *     private final String[] names;
     *
     *     public static void main(String[] args) {
     *         // prints "woman" (first array element)
     *         System.out.println(Enums.getValue(GenderType.WOMAN, String.class));
     *     }
     * }
     *         }</pre>
     *     </li>
     *
     *     <li><b>No fields:</b> returns null
     *         <pre>{@code
     * public enum GenderType {
     *     MAN,
     *     WOMAN,
     *     UNKNOWN;
     *
     *     public static void main(String[] args) {
     *         // prints null (no fields)
     *         System.out.println(Enums.getValue(GenderType.WOMAN, String.class));
     *     }
     * }
     *         }</pre>
     *     </li>
     * </ul>
     *
     * @param input                  the enum instance to inspect
     * @param enumFieldDataTypeClass target field type to retrieve
     * @param <T>                    field data type generic
     * @return the first matching field value, or {@code null} if no fields exist
     */
    public static <T> T getFieldValue(Enum<?> input, Class<T> enumFieldDataTypeClass) {
        if (Nil.isAnyNull(input, enumFieldDataTypeClass)) {
            return null;
        }
        return enumFieldDataTypeClass.cast(ENUM_VALUE_CACHE.computeIfAbsent(
                Strings.format("{}-{}-{}", input.getClass().getName(), input.name(), enumFieldDataTypeClass.getName()),
                ignore -> {
                    for (Field inputClassField : Reflects.getFields(input.getClass())) {
                        String inputClassEnumFieldName = inputClassField.getName();
                        Class<?> inputClassEnumFieldType = inputClassField.getType();
                        // handle varargs condition
                        if (Collections.isArray(inputClassEnumFieldType) && Comparators.equals(enumFieldDataTypeClass, Types.getArrayGenericType(inputClassEnumFieldType))) {
                            return Collections.getFirst(Converts.toArrayList(Reflects.getFieldValue(input, inputClassField, inputClassEnumFieldType))).orElse(null);
                        }
                        // skip enum internal field
                        if (isEnum(inputClassEnumFieldType) || isInternalFieldName(inputClassEnumFieldName) || Classes.isNotAssignable(inputClassEnumFieldType, enumFieldDataTypeClass)) {
                            continue;
                        }
                        return Reflects.getFieldValue(input, inputClassField, enumFieldDataTypeClass);
                    }
                    return null;
                }));
    }

    /**
     * <p>convert enum collection to string by field data type, using {@link SymbolConstant#COMMA} as separator.</p>
     *
     * <p>usage examples:</p>
     * <ul>
     *     <li><b>Typical usage:</b>
     *         <pre>{@code
     * @Getter
     * @AllArgsConstructor
     * public enum GenderType {
     *     MAN(1, "man"),
     *     WOMAN(2, "woman"),
     *     UNKNOWN(3, "unknown");
     *
     *     private final int code;
     *     private final String description;
     *
     *     public static void main(String[] args) {
     *         // prints "man,woman"
     *         System.out.println(Enums.toString(List.of(MAN, WOMAN), String.class));
     *         // prints "1,2"
     *         System.out.println(Enums.toString(List.of(MAN, WOMAN), Integer.class));
     *     }
     * }
     *         }</pre>
     *     </li>
     *
     *     <li><b>Multiple fields:</b> selects first matching type
     *         <pre>{@code
     * @Getter
     * @AllArgsConstructor
     * public enum GenderType {
     *     MAN(1, "man", "Man"),
     *     WOMAN(2, "woman", "Woman"),
     *     UNKNOWN(3, "unknown", "Unknown");
     *
     *     private final int code;
     *     private final String description1;
     *     private final String description2;
     *
     *     public static void main(String[] args) {
     *         // prints "man,woman" (first String field)
     *         System.out.println(Enums.toString(List.of(MAN, WOMAN), String.class));
     *     }
     * }
     *         }</pre>
     *     </li>
     *
     *     <li><b>No fields:</b> returns null values
     *         <pre>{@code
     * public enum GenderType {
     *     MAN,
     *     WOMAN,
     *     UNKNOWN;
     *
     *     public static void main(String[] args) {
     *         // prints "null,null"
     *         System.out.println(Enums.toString(List.of(MAN, WOMAN), String.class));
     *     }
     * }
     *         }</pre>
     *     </li>
     * </ul>
     *
     * @param inputs                 enum collection to convert
     * @param enumFieldDataTypeClass target field type to extract
     * @param <U>                    field data type generic
     * @param <E>                    enum type generic
     * @return comma-separated string of field values, returns "null" entries when no fields exist
     * @see #getFieldValue(Enum, Class)
     */
    public static <U, E extends Enum<E>> String toStringByFieldDataType(Collection<E> inputs, Class<U> enumFieldDataTypeClass) {
        return Strings.joinWithComma(inputs.stream().map(input -> getFieldValue(input, enumFieldDataTypeClass)).toList());
    }

}