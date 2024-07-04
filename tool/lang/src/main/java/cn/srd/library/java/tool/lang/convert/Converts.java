// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.convert;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.HexUtil;
import cn.srd.library.java.contract.constant.booleans.BooleanConstant;
import cn.srd.library.java.contract.constant.collection.CollectionConstant;
import cn.srd.library.java.contract.constant.number.NumberConstant;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.tool.lang.booleans.Booleans;
import cn.srd.library.java.tool.lang.collection.BTreeNode;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.enums.Enums;
import cn.srd.library.java.tool.lang.functional.Action;
import cn.srd.library.java.tool.lang.number.NumberType;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import cn.srd.library.java.tool.lang.text.CharacterSequences;
import cn.srd.library.java.tool.lang.text.Strings;
import io.vavr.Function3;
import io.vavr.Function4;
import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * toolkit for convert
 *
 * @author wjm
 * @since 2021-05-01 14:13
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Converts {

    public static final boolean DEFAULT_CONVERT_QUIETLY = false;

    /**
     * a map with key as identify enum field value and value as enum
     */
    private static final Map<String, Object> ENUM_CACHE = Collections.newConcurrentHashMap(256);

    /**
     * convert number to boolean.
     * <ul>
     *   <li>the input is null, will be converted to false.</li>
     *   <li>the input <= 0, will be converted to false.</li>
     *   <li>the input > 0, will be converted to true.</li>
     * </ul>
     *
     * @param input the input element
     * @return after convert
     */
    public static Boolean toBoolean(Number input) {
        return Nil.isNotNull(input) && input.doubleValue() > NumberConstant.ZERO_DOUBLE_VALUE;
    }

    /**
     * convert object to boolean
     *
     * @param input the input element
     * @return after convert
     */
    @SuppressWarnings(SuppressWarningConstant.ALL)
    public static Boolean toBoolean(Object input) {
        if (input instanceof Boolean inputBoolean) {
            return inputBoolean;
        } else if (input instanceof Integer inputInteger) {
            if (Comparators.equals(BooleanConstant.TRUE_NUMBER, inputInteger)) {
                return true;
            }
            if (Comparators.equals(BooleanConstant.FALSE_NUMBER, inputInteger)) {
                return false;
            }
        } else if (input instanceof String inputString) {
            if (Collections.contains(BooleanConstant.TRUE_STRINGS, inputString)) {
                return true;
            }
            if (Collections.contains(BooleanConstant.FALSE_STRINGS, inputString)) {
                return false;
            }
        }
        return null;
    }

    /**
     * convert long to integer
     *
     * @param input the input element
     * @return after convert
     */
    public static Integer toInteger(Long input) {
        return Math.toIntExact(input);
    }

    /**
     * convert object to integer
     *
     * @param input the input element
     * @return after convert
     */
    public static Integer toInteger(Object input) {
        return Convert.toInt(input);
    }

    /**
     * convert object to number
     *
     * @param input the input element
     * @return after convert
     */
    public static Number toNumber(Object input) {
        return Convert.toNumber(input);
    }

    /**
     * convert object to number  // TODO wjm unsupported all extend Number
     *
     * @param input       the input element
     * @param outputClass the output number class, only support {@link NumberType}
     * @param <T>         the output number type
     * @return after convert
     */
    public static <T extends Number> T toNumber(Object input, Class<T> outputClass) {
        return outputClass.cast(Arrays.stream(NumberType.values())
                .filter(numberType -> numberType.getHandler().isAssignable(outputClass))
                .findFirst()
                .map(numberType -> numberType.getHandler().getValue(toNumber(input)))
                .orElseThrow(() -> new LibraryJavaInternalException(Strings.format("could not convert [{}] to data type [{}.class]", input, outputClass.getSimpleName())))
        );
    }

    /**
     * split a number string separated by {@link SymbolConstant#COMMA} to number collection.
     *
     * @param input             the input element
     * @param outputNumberClass the output number class
     * @param <T>               the output number type
     * @return after split
     * @see #toNumbers(CharSequence, CharSequence, Class)
     * @see Converts#toNumber(Object, Class)
     */
    public static <T extends Number> List<T> toNumbers(CharSequence input, Class<T> outputNumberClass) {
        return toNumbers(input, SymbolConstant.COMMA, outputNumberClass);
    }

    /**
     * <pre>
     * split a number string separated by separator to number collection.
     *
     *  example:
     *
     *     {@code
     *        public static void main(String[] args) {
     *            // the output is [1, 2, 3, 4], data type is {@link Integer}
     *           Converts.toNumbers("1,2,3,4", ",", Integer.class);
     *        }
     *     }
     * </pre>
     *
     * @param input             the input element
     * @param separator         the separator
     * @param outputNumberClass the output number class
     * @param <T>               the output number type
     * @return after split
     * @see Converts#toNumber(Object, Class)
     */
    public static <T extends Number> List<T> toNumbers(CharSequence input, CharSequence separator, Class<T> outputNumberClass) {
        return Nil.isBlank(input) ?
                Collections.newArrayList() :
                CharacterSequences.split(input, separator).stream().map(value -> toNumber(value, outputNumberClass)).collect(Collectors.toList());
    }

    /**
     * convert byte array to string
     *
     * @param input the input element
     * @return after convert
     */
    public static String toString(byte[] input) {
        return Nil.isNull(input) ? SymbolConstant.EMPTY : new String(input);
    }

    /**
     * convert boolean to string
     *
     * @param input the input element
     * @return string
     */
    public static String toString(Boolean input) {
        return Boolean.toString(input);
    }

    /**
     * convert boolean to string
     *
     * @param input         the input element
     * @param outputIfTrue  the string value if true
     * @param outputIfFalse the string value if false or null
     * @return string
     */
    public static String toString(Boolean input, String outputIfTrue, String outputIfFalse) {
        return Booleans.isTrue(input) ? outputIfTrue : outputIfFalse;
    }

    /**
     * convert number to string
     *
     * @param input the input element
     * @return after convert
     */
    public static String toString(Number input) {
        if (Nil.isNull(input)) {
            return null;
        }
        return input.toString();
    }

    /**
     * convert object to string
     *
     * @param input the input element
     * @return after convert
     * @see Convert#toStr(Object)
     */
    public static String toString(Object input) {
        return Convert.toStr(input);
    }

    /**
     * convert byte array to hex string
     *
     * @param inputs          the input elements
     * @param needToLowerCase need lower case or not
     * @return after convert
     * @see HexUtil#encodeHexStr(byte[], boolean)
     */
    public static String toHexString(Byte[] inputs, boolean needToLowerCase) {
        return Nil.isNull(inputs) ? SymbolConstant.EMPTY : toHexString(Collections.unWrap(inputs), needToLowerCase);
    }

    /**
     * convert byte array to hex string
     *
     * @param inputs          the input elements
     * @param needToLowerCase need lower case or not
     * @return after convert
     * @see HexUtil#encodeHexStr(byte[], boolean)
     */
    public static String toHexString(byte[] inputs, boolean needToLowerCase) {
        return HexUtil.encodeHexStr(inputs, needToLowerCase);
    }

    /**
     * <pre>
     * convert collection to array.
     *
     *  example:
     *
     *     {@code
     *        public static void main(String[] args) {
     *            List<Integer> inputs = List.of(1, 2, 3, 4, 5);
     *            // the output is [1, 2, 3, 4, 5]
     *            Integer[] outputs = Converts.toArray(inputs, Integer[]::new);
     *        }
     *     }
     * </pre>
     *
     * @param inputs                the input elements
     * @param outputConstructAction the array construct
     * @param <T>                   the element type
     * @return after convert
     */
    public static <T> T[] toArray(Iterable<T> inputs, IntFunction<T[]> outputConstructAction) {
        return Action.<T[]>ifEmpty(inputs)
                .then(() -> outputConstructAction.apply(CollectionConstant.CAPACITY_EMPTY))
                .otherwise(() -> Collections.ofUnknownSizeStream(inputs).toArray(outputConstructAction))
                .get();
    }

    /**
     * convert collection to array.
     *
     * @param inputs    the input elements
     * @param inputType the input element class
     * @param <T>       the element type
     * @return after convert
     */
    public static <T> T[] toArray(Collection<T> inputs, Class<T> inputType) {
        return Action.<T[]>ifEmpty(inputs)
                .then(() -> Collections.newArray(inputType))
                .otherwise(() -> ArrayUtil.toArray(inputs, inputType))
                .get();
    }

    /**
     * <pre>
     * convert collection to array.
     *
     *  example:
     *
     *     {@code
     *        public static void main(String[] args) {
     *            List<Integer> inputs = List.of(1, 2, 3, 4, 5);
     *            // the output is [1, 2, 3, 4, 5]
     *            Object[] outputs = Converts.toArray(inputs);
     *        }
     *     }
     * </pre>
     *
     * @param inputs the input elements
     * @return after convert
     */
    public static Object[] toArray(Iterable<?> inputs) {
        return Action.<Object[]>ifEmpty(inputs)
                .then(() -> Collections.newArray(Object.class))
                .otherwise(() -> Collections.ofUnknownSizeStream(inputs).toArray())
                .get();
    }

    /**
     * convert iterable to list
     *
     * @param inputs the input element
     * @return after convert
     */
    public static <T> List<T> toList(Iterable<T> inputs) {
        return Action.<List<T>>ifEmpty(inputs)
                .then(Collections::newArrayList)
                .otherwise(() -> Collections.ofUnknownSizeStream(inputs).collect(Collectors.toList()))
                .get();
    }

    /**
     * convert iterable to list
     *
     * @param inputs the input element
     * @return after convert
     */
    public static <T> List<T> toList(Iterator<T> inputs) {
        return Action.<List<T>>ifEmpty(inputs)
                .then(Collections::newArrayList)
                .otherwise(() -> Collections.ofUnknownSizeStream(inputs).collect(Collectors.toList()))
                .get();
    }

    /**
     * convert collection to list
     *
     * @param inputs the input element
     * @return after convert
     */
    public static <T> List<T> toList(Collection<T> inputs) {
        return Action.<List<T>>ifEmpty(inputs)
                .then(Collections::newArrayList)
                .otherwise(() -> Collections.ofArrayList(inputs))
                .get();
    }

    /**
     * <pre>
     * extract the specified field in collection element to become a new collection.
     *
     *  example:
     *
     *     {@code
     *        @Data
     *        @SuperBuilder(toBuilder = true)
     *        public class Person {
     *
     *            private String name;
     *
     *            private Integer age;
     *
     *            public static void main(String[] args) {
     *                List<Person> inputs = List.of(
     *                        Person.builder().name("name1").age(10).build(),
     *                        Person.builder().name("name2").age(11).build(),
     *                        Person.builder().name("name3").age(12).build(),
     *                        Person.builder().name("name4").age(13).build()
     *                );
     *                // the output is [10, 11, 12, 13]
     *                List<Integer> outputs = Converts.toList(inputs, Person::getAge);
     *            }
     *
     *        }
     *     }
     * </pre>
     *
     * @param inputs        the input elements
     * @param mappingAction the specified field in collection element
     * @param <T>           the element type
     * @param <R>           the field in collection element type
     * @return after convert
     */
    public static <T, R> List<R> toList(Iterable<T> inputs, Function<T, R> mappingAction) {
        return Action.<List<R>>ifEmpty(inputs)
                .then(Collections::newArrayList)
                .otherwise(() -> Collections.ofUnknownSizeStream(inputs).map(mappingAction).collect(Collectors.toList()))
                .get();
    }

    /**
     * convert array to list.
     *
     * @param inputs the input elements
     * @param <T>    the element type
     * @return after convert
     */
    public static <T> List<T> toList(T[] inputs) {
        return Action.<List<T>>infer(Nil.isEmpty(inputs))
                .then(Collections::newArrayList)
                .otherwise(() -> Arrays.stream(inputs).collect(Collectors.toList()))
                .get();
    }

    /**
     * <pre>
     * extract the specified field in array element to become a new collection.
     *
     *  example:
     *
     *     {@code
     *        @Data
     *        @SuperBuilder(toBuilder = true)
     *        public class Person {
     *
     *            private String name;
     *
     *            private Integer age;
     *
     *            public static void main(String[] args) {
     *                Person[] inputs = ofArray(
     *                        Person.builder().name("name1").age(10).build(),
     *                        Person.builder().name("name2").age(11).build(),
     *                        Person.builder().name("name3").age(12).build(),
     *                        Person.builder().name("name4").age(13).build()
     *                );
     *                // the output is [10, 11, 12, 13]
     *                List<Integer> outputs = Converts.toList(inputs, Person::getAge);
     *            }
     *
     *        }
     *     }
     * </pre>
     *
     * @param inputs        the input elements
     * @param mappingAction the specified field in array element
     * @param <T>           the element type
     * @param <R>           the new element type
     * @return after convert
     */
    public static <T, R> List<R> toList(T[] inputs, Function<T, R> mappingAction) {
        return Action.<List<R>>infer(Nil.isEmpty(inputs))
                .then(Collections::newArrayList)
                .otherwise(() -> Arrays.stream(inputs).map(mappingAction).collect(Collectors.toList()))
                .get();
    }

    /**
     * convert anything to list
     *
     * @param input the input element
     * @return after convert
     */
    @SuppressWarnings(SuppressWarningConstant.ALL)
    public static List<?> toList(Object input) {
        return Convert.convert(List.class, input);
    }

    /**
     * <pre>
     * convert array to hash set.
     *
     *  example:
     *
     *     {@code
     *        public static void main(String[] args) {
     *            Integer[] inputs = ofArray(1, 2, 3, 3, 4);
     *            // the output is [1, 2, 3, 4]
     *            Set<Integer> outputs = Converts.toSet(inputs);
     *        }
     *     }
     * </pre>
     *
     * @param inputs the input elements
     * @param <T>    the element type
     * @return after convert
     */
    public static <T> Set<T> toSet(T[] inputs) {
        return Action.<Set<T>>infer(Nil.isEmpty(inputs))
                .then(Collections::newHashSet)
                .otherwise(() -> Arrays.stream(inputs).collect(Collectors.toSet()))
                .get();
    }

    /**
     * convert iterable to set
     *
     * @param inputs the input element
     * @return after convert
     */
    public static <T> Set<T> toSet(Iterable<T> inputs) {
        return Action.<Set<T>>ifEmpty(inputs)
                .then(Collections::newHashSet)
                .otherwise(() -> Collections.ofUnknownSizeStream(inputs).collect(Collectors.toSet()))
                .get();
    }

    /**
     * convert collection to set
     *
     * @param inputs the input element
     * @return after convert
     */
    public static <T> Set<T> toSet(Collection<T> inputs) {
        return Action.<Set<T>>ifEmpty(inputs)
                .then(Collections::newHashSet)
                .otherwise(() -> Collections.ofHashSet(inputs))
                .get();
    }

    /**
     * <pre>
     * extract the specified field in collection element to become a new collection.
     *
     *  example:
     *
     *     {@code
     *        @Data
     *        @SuperBuilder(toBuilder = true)
     *        public class Person {
     *
     *            private String name;
     *
     *            private Integer age;
     *
     *            public static void main(String[] args) {
     *                List<Person> inputs = List.of(
     *                        Person.builder().name("name1").age(10).build(),
     *                        Person.builder().name("name2").age(11).build(),
     *                        Person.builder().name("name3").age(11).build(),
     *                        Person.builder().name("name4").age(12).build()
     *                );
     *                // the output is [10, 11, 12]
     *                Set<Integer> outputs = Converts.toSet(inputs, Person::getAge);
     *            }
     *
     *        }
     *     }
     * </pre>
     *
     * @param inputs        the input elements
     * @param mappingAction the specified field in collection element
     * @param <T>           the element type
     * @param <R>           the new element type
     * @return after convert
     */
    public static <T, R> Set<R> toSet(Iterable<T> inputs, Function<T, R> mappingAction) {
        return Action.<Set<R>>infer(Nil.isEmpty(inputs))
                .then(Collections::newHashSet)
                .otherwise(() -> Collections.ofUnknownSizeStream(inputs).map(mappingAction).collect(Collectors.toSet()))
                .get();
    }

    /**
     * convert anything to set
     *
     * @param input the input element
     * @return after convert
     */
    @SuppressWarnings(SuppressWarningConstant.ALL)
    public static Set<?> toSet(Object input) {
        return Convert.convert(Set.class, input);
    }

    /**
     * convert map all keys to array list
     *
     * @param inputs the input elements
     * @param <K>    the key type of map
     * @param <V>    the value type of map
     * @return after convert
     */
    public static <K, V> List<K> toMapKeys(Map<K, V> inputs) {
        return Action.<List<K>>ifEmpty(inputs)
                .then(Collections::newArrayList)
                .otherwise(() -> Collections.ofArrayList(inputs.keySet()))
                .get();
    }

    /**
     * convert map all values to array list
     *
     * @param inputs the input elements
     * @param <K>    the key type of map
     * @param <V>    the value type of map
     * @return after convert
     */
    public static <K, V> List<V> toMapValues(Map<K, V> inputs) {
        return Action.<List<V>>ifEmpty(inputs)
                .then(Collections::newArrayList)
                .otherwise(() -> Collections.ofArrayList(inputs.values()))
                .get();
    }

    /**
     * <pre>
     * extract the specified field in collection element to become a new map, the key is the specified field, the value is the element self.
     *
     *  example:
     *
     *     {@code
     *        @Data
     *        @SuperBuilder(toBuilder = true)
     *        public class Person {
     *
     *            private String name;
     *
     *            private Integer age;
     *
     *            public static void main(String[] args) {
     *                List<Person> inputs = List.of(
     *                        Person.builder().name("name1").age(10).build(),
     *                        Person.builder().name("name2").age(11).build(),
     *                        Person.builder().name("name3").age(12).build(),
     *                        Person.builder().name("name4").age(13).build()
     *                );
     *                // the output is {10:Person(name="name1", age=10), 11:Person(name="name2", age=11), 12:Person(name="name3", age=12), 13:Person(name="name4", age=13)}
     *                Map<Integer, Person> outputs = Converts.toMap(inputs, Person::getAge);
     *            }
     *
     *        }
     *     }
     * </pre>
     *
     * @param inputs       the input elements
     * @param getKeyAction the specified field to be map key in collection element
     * @param <K>          the key type of map
     * @param <V>          the value type of map
     * @return after convert
     */
    public static <K, V> Map<K, V> toMap(Iterable<V> inputs, Function<V, K> getKeyAction) {
        return Action.<Map<K, V>>infer(Nil.isEmpty(inputs))
                .then(Collections::newHashMap)
                .otherwise(() -> Collections.ofUnknownSizeStream(inputs).collect(Collectors.toMap(getKeyAction, item -> item)))
                .get();
    }

    /**
     * <pre>
     * extract the specified field in collection element to become a new map, the key is the specified field, the value is the other specified field.
     *
     *  example:
     *
     *     {@code
     *        @Data
     *        @SuperBuilder(toBuilder = true)
     *        public class Person {
     *
     *            private String name;
     *
     *            private Integer age;
     *
     *            public static void main(String[] args) {
     *                List<Person> inputs = List.of(
     *                        Person.builder().name("name1").age(10).build(),
     *                        Person.builder().name("name2").age(11).build(),
     *                        Person.builder().name("name3").age(12).build(),
     *                        Person.builder().name("name4").age(13).build()
     *                );
     *                // the output is {10:"name1", 11:"name2", 12:"name3", 13:"name4"}
     *                Map<Integer, String> outputs = Converts.toMap(inputs, Person::getAge, Person::getName);
     *            }
     *
     *        }
     *     }
     * </pre>
     *
     * @param inputs         the input elements
     * @param getKeyAction   the specified field to be map key in collection element
     * @param getValueAction the specified field to be map value in collection element
     * @param <T>            the element type
     * @param <K>            the key type of map
     * @param <V>            the value type of map
     * @return after convert
     */
    public static <T, K, V> Map<K, V> toMap(Iterable<T> inputs, Function<T, K> getKeyAction, Function<T, V> getValueAction) {
        return Action.<Map<K, V>>infer(Nil.isEmpty(inputs))
                .then(Collections::newHashMap)
                .otherwise(() -> Collections.ofUnknownSizeStream(inputs).collect(Collectors.toMap(getKeyAction, getValueAction)))
                .get();
    }

    /**
     * <pre>
     * extract the specified field in collection element to become a new map, the key is the specified field, the value is the duplicate key and convert element self to array list.
     *
     *  example:
     *
     *     {@code
     *        @Data
     *        @SuperBuilder(toBuilder = true)
     *        public class Person {
     *
     *            private String name;
     *
     *            private Integer age;
     *
     *            public static void main(String[] args) {
     *                List<Person> inputs = List.of(
     *                        Person.builder().name("name1").age(10).build(),
     *                        Person.builder().name("name2").age(11).build(),
     *                        Person.builder().name("name3").age(11).build(),
     *                        Person.builder().name("name4").age(12).build()
     *                );
     *                // the output is {10:[Person(name="name1", age=10)], 11:[Person(name="name2", age=11), Person(name="name3", age=11)], 12:[Person(name="name4", age=12)]}
     *                Map<Integer, List<Person>> outputs = Converts.toMultiMap(inputs, Person::getAge);
     *            }
     *
     *        }
     *     }
     * </pre>
     *
     * @param inputs       the input elements
     * @param getKeyAction the specified field to be map key in collection element
     * @param <K>          the key type of map
     * @param <V>          the value type of map
     * @return after convert
     */
    public static <K, V> Map<K, List<V>> toMultiMap(Iterable<V> inputs, Function<V, K> getKeyAction) {
        return Action.<Map<K, List<V>>>infer(Nil.isEmpty(inputs))
                .then(Collections::newHashMap)
                .otherwise(() -> Collections.ofUnknownSizeStream(inputs).collect(Collectors.groupingBy(getKeyAction, Collectors.mapping(item -> item, Collectors.toList()))))
                .get();
    }

    /**
     * <pre>
     * extract the specified field in collection element to become a new map, the key is the specified field, the value is the duplicate key and convert the other specified field to array list.
     *
     *  example:
     *
     *     {@code
     *        @Data
     *        @SuperBuilder(toBuilder = true)
     *        public class Person {
     *
     *            private String name;
     *
     *            private Integer age;
     *
     *            public static void main(String[] args) {
     *                List<Person> inputs = List.of(
     *                        Person.builder().name("name1").age(10).build(),
     *                        Person.builder().name("name2").age(11).build(),
     *                        Person.builder().name("name3").age(11).build(),
     *                        Person.builder().name("name4").age(12).build()
     *                );
     *                // the output is {10:["name1"], 11:["name2", "name3"], 12:["name4"]}
     *                Map<Integer, List<String>> outputs = Converts.toMultiMap(inputs, Person::getAge, Person::getName);
     *            }
     *
     *        }
     *     }
     * </pre>
     *
     * @param inputs         the input elements
     * @param getKeyAction   the specified field to be map key in collection element
     * @param getValueAction the specified field to be map value in collection element
     * @param <T>            the element type
     * @param <K>            the key type of map
     * @param <V>            the value type of map
     * @return after convert
     */
    public static <T, K, V> Map<K, List<V>> toMultiMap(Iterable<T> inputs, Function<T, K> getKeyAction, Function<T, V> getValueAction) {
        return Action.<Map<K, List<V>>>infer(Nil.isEmpty(inputs))
                .then(Collections::newHashMap)
                .otherwise(() -> Collections.ofUnknownSizeStream(inputs).collect(Collectors.groupingBy(getKeyAction, Collectors.mapping(getValueAction, Collectors.toList()))))
                .get();
    }

    /**
     * convert the element implement {@link BTreeNode} list to tree list
     *
     * @param nodes the element implement {@link BTreeNode} list
     * @param <Key> the {@link BTreeNode#getId()}, {@link BTreeNode#getParentId()} type
     * @param <T>   the element type
     * @return tree list
     */
    public static <Key, T extends BTreeNode<Key, T>> List<T> toTree(List<T> nodes) {
        Map<Key, T> nodeIdMappingNodeMap = toMap(nodes, T::getId);
        List<T> treeNodes = Collections.newArrayList();
        nodes.forEach(node -> {
            if (Nil.isZeroValue(node.getParentId()) || Collections.notContainsKey(nodeIdMappingNodeMap, node.getParentId())) {
                treeNodes.add(node);
            } else {
                T parentNode = nodeIdMappingNodeMap.get(node.getParentId());
                if (Nil.isNull(parentNode.getChildren())) {
                    parentNode.setChildren(Collections.newArrayList());
                }
                parentNode.getChildren().add(node);
            }
        });
        return treeNodes;
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
     *                Converts.toEnumByName("WOMAN", GenderType.class);
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
    public static <E extends Enum<E>> E toEnumByName(String enumFiledName, Class<E> enumClass) {
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
     *                Converts.toEnumByValue(2, GenderType.class);
     *                // the output is GenderType.WOMAN
     *                Converts.toEnumByValue("woman", GenderType.class);
     *                // the output is null
     *                Converts.toEnumByValue("2", GenderType.class);
     *                // the output is null
     *                Converts.toEnumByValue("WOMAN", GenderType.class);
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
     *                Converts.toEnumByValue(2, GenderType.class);
     *                // the output is GenderType.WOMAN
     *                Converts.toEnumByValue("woman", GenderType.class);
     *                // the output is GenderType.WOMAN
     *                Converts.toEnumByValue("Woman", GenderType.class);
     *                // the output is null
     *                Converts.toEnumByValue("WOMAN", GenderType.class);
     *                // the output is null
     *                Converts.toEnumByValue("2", GenderType.class);
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
     *                Converts.toEnumByValue("Woman", GenderType.class);
     *                // the output is GenderType.UNKNOWN
     *                Converts.toEnumByValue("unknown", GenderType.class);
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
     *                Converts.toEnumByValue("WOMAN", GenderType.class);
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
     * @see Enums#getFieldValue(Enum, Class)
     * @see EnumUtil#likeValueOf(Class, Object)
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public static <E extends Enum<E>> E toEnumByValue(Object enumFiledValue, Class<E> enumClass) {
        if (Nil.isAnyNull(enumFiledValue, enumClass)) {
            return null;
        }
        return (E) ENUM_CACHE.computeIfAbsent(
                Strings.format("{}-{}", enumClass.getName(), enumFiledValue),
                ignore -> {
                    for (Field field : Reflects.getFields(enumClass)) {
                        // skip enum internal field
                        if (Enums.isEnum(field.getType()) || Enums.isInternalFieldName(field)) {
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

    /**
     * split a number string separated by {@link SymbolConstant#COMMA} to enum collection.
     *
     * @param input       the input element
     * @param outputClass the output enum class
     * @param <E>         the output enum type
     * @return after split
     * @see #toNumbers(CharSequence, CharSequence, Class)
     * @see #toEnumsByString(CharSequence, CharSequence, Class)
     * @see Converts#toEnumByValue(Object, Class)
     * @see Converts#toNumber(Object, Class)
     */
    public static <E extends Enum<E>> List<E> toEnumsByString(CharSequence input, Class<E> outputClass) {
        return toEnumsByString(input, SymbolConstant.COMMA, outputClass);
    }

    /**
     * <pre>
     * split a number string separated by separator to enum collection.
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
     *
     *            private final String description;
     *
     *            public static void main(String[] args) {
     *                // the output is [GenderType.MAN, GenderType.WOMAN, GenderType.UNKNOWN]
     *                Converts.toEnumsByString("1, 2, 3", ",", GenderType.class);
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
     *            MAN(1, 10, "Man"),
     *            WOMAN(2, 11, "Woman"),
     *            UNKNOWN(3, 12, "Unknown"),
     *
     *            ;
     *
     *            private final int code1;
     *
     *            private final int code2;
     *
     *            private final String description;
     *
     *            public static void main(String[] args) {
     *                // the output is [GenderType.MAN, GenderType.WOMAN, GenderType.UNKNOWN]
     *                Converts.toEnumsByString("1, 2, 3", ",", GenderType.class);
     *                // the output is [GenderType.MAN, GenderType.WOMAN, GenderType.UNKNOWN]
     *                Converts.toEnumsByString("10, 11, 12", ",", GenderType.class);
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
     *                // the output is [null, null, null]
     *                Converts.toEnumsByString("1, 2, 3", ",", GenderType.class);
     *            }
     *
     *        }
     *     }
     * </pre>
     *
     * @param input       the input element
     * @param separator   the separator
     * @param outputClass the output enum class
     * @param <E>         the output enum type
     * @return after split
     * @see #toNumbers(CharSequence, CharSequence, Class)
     * @see Converts#toEnumByValue(Object, Class)
     * @see Converts#toNumber(Object, Class)
     */
    public static <E extends Enum<E>> List<E> toEnumsByString(CharSequence input, CharSequence separator, Class<E> outputClass) {
        return Nil.isBlank(input) ?
                Collections.newArrayList() :
                toNumbers(input, separator, Integer.class).stream().map(value -> toEnumByValue(value, outputClass)).collect(Collectors.toList());
    }

    public static <T> Function<Void, T> toFunction(Supplier<T> action) {
        return ignore -> action.get();
    }

    public static <T> Function<T, Void> toFunction(Consumer<T> action) {
        return input -> {
            action.accept(input);
            return null;
        };
    }

    public static <T> Function<T, Boolean> toFunction(Predicate<T> action) {
        return action::test;
    }

    public static <T, R> BiFunction<T, Void, R> toBiFunction(Function<T, R> action) {
        return (input, ignore) -> action.apply(input);
    }

    public static <T1, T2, T3, R> Function3<T1, T2, T3, R> toFunction3(Function<T1, R> action) {
        return (input, ignore1, ignore2) -> action.apply(input);
    }

    public static <T1, T2, T3, T4, R> Function4<T1, T2, T3, T4, R> toFunction4(Function<T1, R> action) {
        return (input, ignore1, ignore2, ignore3) -> action.apply(input);
    }

    public static <T1, T2, T3, T4, R> Function4<T1, T2, T3, T4, R> toFunction4(Function3<T1, T2, T3, R> action) {
        return (input1, input2, input3, ignore) -> action.apply(input1, input2, input3);
    }

}