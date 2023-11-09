// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.enums;

import cn.hutool.core.util.EnumUtil;
import cn.srd.library.java.contract.constant.enums.EnumConstant;
import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.functional.Action;
import cn.srd.library.java.tool.lang.functional.Functional;
import cn.srd.library.java.tool.lang.object.Classes;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.object.Types;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import cn.srd.library.java.tool.lang.text.Strings;
import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
     * a map with key as identify enum field value and value as enum
     */
    private static final Map<String, Object> ENUM_CACHE = Collections.newConcurrentHashMap(256);

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
     * <pre>
     * get all enum instances.
     *
     * example code:
     *     {@code
     *        public enum GenderType {
     *
     *            MAN,
     *            WOMAN,
     *            UNKNOWN,
     *
     *            ;
     *
     *            public static void main(String[] args) {
     *                // the output is [MAN, WOMAN, UNKNOWN]
     *                List<GenderType> genderTypes = Enums.getAllFields(GenderType.class);
     *            }
     *
     *        }
     *     }
     * </pre>
     *
     * @param input the enum class
     * @param <E>   the enum type
     * @return all enum instances
     */
    public static <E extends Enum<E>> List<E> getAllInstances(Class<E> input) {
        return Action.<List<E>>ifNull(input)
                .then(() -> Collections.newArrayList())
                .otherwise(() -> Collections.ofArrayList(input.getEnumConstants()))
                .get();
    }

    /**
     * <pre>
     * get enum field value.
     *
     *  note 1. the most usually condition:
     *
     *     {@code
     *        @Getter
     *        @AllArgsConstructor
     *        public enum GenderType {
     *
     *            MAN(1, "man"),
     *            WOMAN(2, "woman"),
     *            UNKNOWN(3, "unknown"),
     *
     *            ;
     *
     *            private final int code;
     *            private final String description;
     *
     *            public static void main(String[] args) {
     *                // it will print "woman"
     *                System.out.println(Enums.getValue(GenderType.WOMAN, String.class));
     *                // it will print 2
     *                System.out.println(Enums.getValue(GenderType.WOMAN, Integer.class));
     *            }
     *
     *        }
     *     }
     *
     *  note 2. if there are multiple field data type, it will always get the first one:
     *
     *     {@code
     *        @Getter
     *        @AllArgsConstructor
     *        public enum GenderType {
     *
     *            MAN(1, "man", "Man"),
     *            WOMAN(2, "woman", "Woman"),
     *            UNKNOWN(3, "unknown", "Unknown"),
     *
     *            ;
     *
     *            private final int code;
     *            private final String description1;
     *            private final String description2;
     *
     *            public static void main(String[] args) {
     *                // it will print "woman"
     *                System.out.println(Enums.getValue(GenderType.WOMAN, String.class));
     *                // it will print 2
     *                System.out.println(Enums.getValue(GenderType.WOMAN, Integer.class));
     *            }
     *
     *        }
     *     }
     *
     *  note 3. if there are varargs field data type, it will always get the first one:
     *
     *     {@code
     *        @Getter
     *        @AllArgsConstructor
     *        public enum GenderType {
     *
     *            MAN("man"),
     *            WOMAN("woman", "Woman", "WOMAN"),
     *            UNKNOWN("unknown", "Unknown"),
     *
     *            ;
     *
     *            TimeUnitType(String... names) {
     *                this.names = names;
     *            }
     *
     *            private final String[] names;
     *
     *            public static void main(String[] args) {
     *                // it will print "woman"
     *                System.out.println(Enums.getValue(GenderType.WOMAN, String.class));
     *                // it will print "unknown"
     *                System.out.println(Enums.getValue(GenderType.UNKNOWN, Integer.class));
     *            }
     *
     *        }
     *     }
     *
     *  note 4. it will always return null if the enum does not have additional fields.
     *
     *     {@code
     *        public enum GenderType {
     *
     *            MAN,
     *            WOMAN,
     *            UNKNOWN,
     *
     *            ;
     *
     *            public static void main(String[] args) {
     *                // it will print null
     *                System.out.println(Enums.getValue(GenderType.WOMAN, String.class));
     *                // it will print null
     *                System.out.println(Enums.getValue(GenderType.WOMAN, Integer.class));
     *            }
     *
     *        }
     *     }
     * </pre>
     *
     * @param input                  the enum instance
     * @param enumFieldDataTypeClass the enum field data type class
     * @param <T>                    the enum field data type
     * @return the enum field value
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
                            return Collections.getFirst(Collections.toList(Reflects.getFieldValue(input, inputClassField, inputClassEnumFieldType))).orElse(null);
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
     * <pre>
     * convert enum collection to string by field data type, using {@link SymbolConstant#COMMA} as separator
     *
     *  note 1. the most usually condition:
     *
     *     {@code
     *        @Getter
     *        @AllArgsConstructor
     *        public enum GenderType {
     *
     *            MAN(1, "man"),
     *            WOMAN(2, "woman"),
     *            UNKNOWN(3, "unknown"),
     *
     *            ;
     *
     *            private final int code;
     *            private final String description;
     *
     *            public static void main(String[] args) {
     *                // it will print "man,woman"
     *                System.out.println(Enums.toString(List.of(GenderType.MAN, GenderType.WOMAN), String.class));
     *                // it will print "1,2"
     *                System.out.println(Enums.toString(List.of(GenderType.MAN, GenderType.WOMAN), Integer.class));
     *            }
     *
     *        }
     *     }
     *
     *  note 2. if there are multiple field data type, it will always get the first one:
     *
     *     {@code
     *        @Getter
     *        @AllArgsConstructor
     *        public enum GenderType {
     *
     *            MAN(1, "man", "Man"),
     *            WOMAN(2, "woman", "Woman"),
     *            UNKNOWN(3, "unknown", "Unknown"),
     *
     *            ;
     *
     *            private final int code;
     *            private final String description1;
     *            private final String description2;
     *
     *            public static void main(String[] args) {
     *                // it will print "man,woman"
     *                System.out.println(Enums.toString(List.of(GenderType.MAN, GenderType.WOMAN), String.class));
     *                // it will print "1,2"
     *                System.out.println(Enums.toString(List.of(GenderType.MAN, GenderType.WOMAN), Integer.class));
     *            }
     *
     *        }
     *     }
     *
     *  note 3. it will always return null if the enum does not have additional fields.
     *
     *     {@code
     *        public enum GenderType {
     *
     *            MAN,
     *            WOMAN,
     *            UNKNOWN,
     *
     *            ;
     *
     *            public static void main(String[] args) {
     *                // it will print "null,null"
     *                System.out.println(Enums.toString(List.of(GenderType.MAN, GenderType.WOMAN), String.class));
     *                // it will print "null,null"
     *                System.out.println(Enums.toString(List.of(GenderType.MAN, GenderType.WOMAN), Integer.class));
     *            }
     *
     *        }
     *     }
     * </pre>
     *
     * @param inputs                 the enum collection
     * @param enumFieldDataTypeClass the enum field data type class
     * @param <U>                    the enum field data type
     * @param <E>                    the enum type
     * @return string after convert
     * @see #getFieldValue(Enum, Class)
     */
    public static <U, E extends Enum<E>> String toStringByFieldDataType(Collection<E> inputs, Class<U> enumFieldDataTypeClass) {
        return Strings.joinWithComma(inputs.stream().map(input -> getFieldValue(input, enumFieldDataTypeClass)).toList());
    }

    /**
     * <pre>
     * convert to enum instance by enum field name.
     *
     *  example:
     *
     *     {@code
     *        public enum GenderType {
     *
     *            MAN,
     *            WOMAN,
     *            UNKNOWN,
     *
     *            ;
     *
     *            public static void main(String[] args) {
     *                // the output is GenderType.WOMAN
     *                Enums.toEnumByFieldName("WOMAN", GenderType.class);
     *            }
     *
     *        }
     *     }
     * </pre>
     *
     * @param enumFiledName the enum field name
     * @param enumClass     the enum class
     * @param <E>           the enum type
     * @return enum instance
     */
    public static <E extends Enum<E>> E toEnumByFieldName(String enumFiledName, Class<E> enumClass) {
        return Try.of(() -> Enum.valueOf(enumClass, enumFiledName)).getOrNull();
    }

    /**
     * <pre>
     * convert to enum instance by enum field value.
     *
     *  note 1. the most usually condition:
     *
     *     {@code
     *        @Getter
     *        @AllArgsConstructor
     *        public enum GenderType {
     *
     *            MAN(1, "man"),
     *            WOMAN(2, "woman"),
     *            UNKNOWN(3, "unknown"),
     *
     *            ;
     *
     *            private final int code;
     *            private final String description;
     *
     *            public static void main(String[] args) {
     *                // the output is GenderType.WOMAN
     *                Enums.toEnumByFieldValue(2, GenderType.class);
     *                // the output is GenderType.WOMAN
     *                Enums.toEnumByFieldValue("woman", GenderType.class);
     *                // the output is null
     *                Enums.toEnumByFieldValue("2", GenderType.class);
     *                // the output is null
     *                Enums.toEnumByFieldValue("WOMAN", GenderType.class);
     *            }
     *
     *        }
     *     }
     *
     *  note 2. still valid if there are multiple field data type.
     *
     *     {@code
     *        @Getter
     *        @AllArgsConstructor
     *        public enum GenderType {
     *
     *            MAN(1, "man", "Man"),
     *            WOMAN(2, "woman", "Woman"),
     *            UNKNOWN(3, "unknown", "Unknown"),
     *
     *            ;
     *
     *            private final int code;
     *            private final String description1;
     *            private final String description2;
     *
     *            public static void main(String[] args) {
     *                // the output is GenderType.WOMAN
     *                Enums.toEnumByFieldValue(2, GenderType.class);
     *                // the output is GenderType.WOMAN
     *                Enums.toEnumByFieldValue("woman", GenderType.class);
     *                // the output is GenderType.WOMAN
     *                Enums.toEnumByFieldValue("Woman", GenderType.class);
     *                // the output is null
     *                Enums.toEnumByFieldValue("WOMAN", GenderType.class);
     *                // the output is null
     *                Enums.toEnumByFieldValue("2", GenderType.class);
     *            }
     *
     *        }
     *     }
     *
     *  note 3. still valid if there are varargs field data type.
     *
     *     {@code
     *        @Getter
     *        @AllArgsConstructor
     *        public enum GenderType {
     *
     *            MAN("man"),
     *            WOMAN("woman", "Woman", "WOMAN"),
     *            UNKNOWN("unknown", "Unknown"),
     *
     *            ;
     *
     *            TimeUnitType(String... names) {
     *                this.names = names;
     *            }
     *
     *            private final String[] names;
     *
     *            public static void main(String[] args) {
     *                // the output is GenderType.WOMAN
     *                Enums.toEnumByFieldValue("Woman", GenderType.class);
     *                // the output is GenderType.UNKNOWN
     *                Enums.toEnumByFieldValue("unknown", GenderType.class);
     *            }
     *
     *        }
     *     }
     *
     *  note 4. it will always return null if the enum does not have additional fields.
     *
     *     {@code
     *        public enum GenderType {
     *
     *            MAN,
     *            WOMAN,
     *            UNKNOWN,
     *
     *            ;
     *
     *            public static void main(String[] args) {
     *                // the output is null
     *                Enums.toEnumByFieldValue("WOMAN", GenderType.class);
     *            }
     *
     *        }
     *     }
     * </pre>
     *
     * @param enumFiledValue the enum field value
     * @param enumClass      the enum class
     * @param <E>            the enum type
     * @return enum instance
     * @see #getFieldValue(Enum, Class)
     * @see EnumUtil#likeValueOf(Class, Object)
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public static <E extends Enum<E>> E toEnumByFieldValue(Object enumFiledValue, Class<E> enumClass) {
        if (Nil.isAnyNull(enumFiledValue, enumClass)) {
            return null;
        }
        return (E) ENUM_CACHE.computeIfAbsent(
                Strings.format("{}-{}", enumClass.getName(), enumFiledValue),
                ignore -> {
                    for (Field field : Reflects.getFields(enumClass)) {
                        // skip enum internal field
                        if (isEnum(field.getType()) || isInternalFieldName(field)) {
                            continue;
                        }
                        for (E enumObj : enumClass.getEnumConstants()) {
                            Object fieldValue = Reflects.getFieldValueIgnoreThrowable(enumObj, field);
                            if (Comparators.equals(fieldValue, enumFiledValue)) {
                                return enumObj;
                            }
                            // handle varargs condition
                            if (Collections.isArray(fieldValue) && Collections.contains((Object[]) fieldValue, enumFiledValue)) {
                                return enumObj;
                            }
                        }
                    }
                    return null;
                });
    }

}
