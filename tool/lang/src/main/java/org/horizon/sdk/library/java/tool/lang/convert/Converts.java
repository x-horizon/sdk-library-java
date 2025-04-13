package org.horizon.sdk.library.java.tool.lang.convert;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import io.vavr.Function3;
import io.vavr.Function4;
import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.dromara.hutool.core.array.ArrayUtil;
import org.dromara.hutool.core.convert.ConvertUtil;
import org.horizon.sdk.library.java.contract.constant.booleans.BooleanConstant;
import org.horizon.sdk.library.java.contract.constant.collection.CollectionConstant;
import org.horizon.sdk.library.java.contract.constant.number.NumberConstant;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.contract.model.throwable.LibraryJavaInternalException;
import org.horizon.sdk.library.java.tool.lang.booleans.Booleans;
import org.horizon.sdk.library.java.tool.lang.collection.BTreeNode;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.compare.Comparators;
import org.horizon.sdk.library.java.tool.lang.enums.Enums;
import org.horizon.sdk.library.java.tool.lang.functional.Action;
import org.horizon.sdk.library.java.tool.lang.number.Hexes;
import org.horizon.sdk.library.java.tool.lang.number.NumberType;
import org.horizon.sdk.library.java.tool.lang.object.Classes;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.reflect.Reflects;
import org.horizon.sdk.library.java.tool.lang.text.CharacterSequences;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     * the map with key as identify enum field value and value as enums
     */
    private static final Map ENUM_CACHE = Collections.newConcurrentHashMap();

    /**
     * the map with key as identify enum field value and value as enum and match by equal
     */
    private static final Map<String, Object> MATCH_BY_EQUAL_ENUM_CACHE = Collections.newConcurrentHashMap();

    /**
     * the map with key as identify enum field value and value as enum and match by most similar
     */
    private static final Map<String, Object> MATCH_BY_MOST_SIMILAR_ENUM_CACHE = Collections.newConcurrentHashMap();

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
        return ConvertUtil.toInt(input);
    }

    /**
     * convert object to number
     *
     * @param input the input element
     * @return after convert
     */
    public static Number toNumber(Object input) {
        return ConvertUtil.toNumber(input);
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
     * <p>split number string separated by separator to a numeric collection.</p>
     *
     * <p>example:</p>
     * <pre>{@code
     * public static void main(String[] args) {
     *     // Result: [1, 2, 3, 4]
     *     Converts.toNumbers("1,2,3,4", ",", Integer.class);
     * }
     * }</pre>
     *
     * @param input             input string containing numbers
     * @param separator         regex separator for splitting
     * @param outputNumberClass target numeric type class (e.g. {@code Integer.class})
     * @param <T>               type of the output numbers
     * @return unmodifiable collection of converted numbers
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
     * @see ConvertUtil#toStr(Object)
     */
    public static String toString(Object input) {
        return ConvertUtil.toStr(input);
    }

    /**
     * convert byte array to hex string
     *
     * @param inputs          the input elements
     * @param needToLowerCase need lower case or not
     * @return after convert
     * @see Hexes#toString(byte[], boolean)
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
     * @see Hexes#toString(byte[], boolean)
     */
    public static String toHexString(byte[] inputs, boolean needToLowerCase) {
        return Hexes.toString(inputs, needToLowerCase);
    }

    /**
     * <p>convert collection to type-specific array.</p>
     *
     * <p>example:</p>
     * <pre>{@code
     * List<Integer> inputs = List.of(1, 2, 3, 4, 5);
     * // Results: [1, 2, 3, 4, 5]
     * Integer[] outputs = Converts.toArray(inputs, Integer[]::new);
     * }</pre>
     *
     * @param inputs                source collection to be converted
     * @param outputConstructAction array generator function (e.g. {@code Integer[]::new})
     * @param <T>                   array component type
     * @return newly created array containing collection elements
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
                .otherwise(() -> ArrayUtil.ofArray(inputs, inputType))
                .get();
    }

    /**
     * <p>convert collection to object array.</p>
     *
     * <p>example:</p>
     * <pre>{@code
     * List<Integer> inputs = List.of(1, 2, 3, 4, 5);
     * // Results: [1, 2, 3, 4, 5]
     * Object[] outputs = Converts.toArray(inputs);
     * }</pre>
     *
     * @param inputs source collection to be converted
     * @return newly created object array containing collection elements
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
     * <p>extract specified fields from collection elements to create a new collection.</p>
     *
     * <p>example with {@code Person} class:</p>
     * <pre>{@code
     * List<Person> inputs = List.of(
     *     new Person("name1", 10),
     *     new Person("name2", 11),
     *     new Person("name3", 12)
     * );
     *
     * // Extracted age values: [10, 11, 12]
     * List<Integer> ages = Converts.toList(inputs, Person::getAge);
     * }</pre>
     *
     * @param inputs        source collection containing elements
     * @param mappingAction field extractor function (e.g. {@code Person::getAge})
     * @param <T>           type of elements in source collection
     * @param <R>           type of extracted field values
     * @return newly list containing extracted field values
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
     * <p>extract specified fields from array elements to create a new collection.</p>
     *
     * <p>example with {@code Person} array:</p>
     * <pre>{@code
     * Person[] inputs = Arrays.asList(
     *     new Person("name1", 10),
     *     new Person("name2", 11),
     *     new Person("name3", 12)
     * );
     *
     * // Extracted age values: [10, 11, 12]
     * List<Integer> ages = Converts.toList(inputs, Person::getAge);
     * }</pre>
     *
     * @param inputs        source array containing elements
     * @param mappingAction field extractor function (e.g. {@code Person::getAge})
     * @param <T>           type of elements in source array
     * @param <R>           type of extracted field values
     * @return new unmodifiable list containing extracted values
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
        return ConvertUtil.convert(List.class, input);
    }

    /**
     * <p>convert array to {@link java.util.Set} with unique elements.</p>
     *
     * <p>example with deduplication:</p>
     * <pre>{@code
     * Integer[] inputs = {1, 2, 3, 3, 4};
     * // Resulting set contains unique values: [1, 2, 3, 4]
     * Set<Integer> uniqueNumbers = Converts.toSet(inputs);
     * }</pre>
     *
     * @param inputs source array
     * @param <T>    type of array elements
     * @return newly created {@code HashSet} containing deduplicated elements
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
     * <p>extract unique field values from collection elements to create a {@code Set}.</p>
     *
     * <p>example with deduplication:</p>
     * <pre>{@code
     * List<Person> people = Arrays.asList(
     *     new Person("name1", 10),
     *     new Person("name2", 11),
     *     new Person("name3", 11),  // duplicate age
     *     new Person("name4", 12)
     * );
     *
     * // Unique age values: [10, 11, 12]
     * Set<Integer> uniqueAges = Converts.toSet(people, Person::getAge);
     * }</pre>
     *
     * @param inputs        source collection containing elements
     * @param mappingAction field value extractor (e.g. {@code Person::getAge})
     * @param <T>           type of elements in source collection
     * @param <R>           type of extracted field values
     * @return {@code Set} containing unique extracted values
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
        return ConvertUtil.convert(Set.class, input);
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
     * <p>convert collection to map using specified field as keys, with elements as values.</p>
     *
     * <p>example with {@code Person} mapping:</p>
     * <pre>{@code
     * List<Person> people = Arrays.asList(
     *     new Person("name1", 10),
     *     new Person("name2", 11),
     *     new Person("name3", 12)
     * );
     *
     * // Creates age-to-person mapping:
     * // {
     * //   10=Person[name="name1", age=10],
     * //   11=Person[name="name2", age=11],
     * //   12=Person[name="name3", age=12]
     * // }
     * Map<Integer, Person> ageMap = Converts.toMap(people, Person::getAge);
     * }</pre>
     *
     * @param inputs       source collection to convert
     * @param getKeyAction key extractor function (e.g. {@code Person::getAge})
     * @param <K>          type of map keys
     * @param <V>          type of collection elements (map values)
     * @return map where keys are extracted values, values are original elements
     */
    public static <K, V> Map<K, V> toMap(Iterable<V> inputs, Function<V, K> getKeyAction) {
        return Action.<Map<K, V>>infer(Nil.isEmpty(inputs))
                .then(Collections::newHashMap)
                .otherwise(() -> Collections.ofUnknownSizeStream(inputs).collect(Collectors.toMap(getKeyAction, item -> item)))
                .get();
    }

    /**
     * <p>convert collection to map by extracting key-value pairs from elements.</p>
     *
     * <p>example with bi-field mapping:</p>
     * <pre>{@code
     * List<Person> people = Arrays.asList(
     *     new Person("name1", 10),
     *     new Person("name2", 11),
     *     new Person("name3", 12)
     * );
     *
     * // Creates age-to-name mapping:
     * // {
     * //   10="name1",
     * //   11="name2",
     * //   12="name3"
     * // }
     * Map<Integer, String> ageNameMap = Converts.toMap(
     *     people,
     *     Person::getAge,
     *     Person::getName
     * );
     * }</pre>
     *
     * @param inputs         source collection to convert
     * @param getKeyAction   key extractor function (e.g. {@code Person::getAge})
     * @param getValueAction value extractor function (e.g. {@code Person::getName})
     * @param <T>            type of elements in source collection
     * @param <K>            type of map keys
     * @param <V>            type of map values
     * @return map where keys are extracted values, values are corresponding field values
     */
    public static <T, K, V> Map<K, V> toMap(Iterable<T> inputs, Function<T, K> getKeyAction, Function<T, V> getValueAction) {
        return Action.<Map<K, V>>infer(Nil.isEmpty(inputs))
                .then(Collections::newHashMap)
                .otherwise(() -> Collections.ofUnknownSizeStream(inputs).collect(Collectors.toMap(getKeyAction, getValueAction)))
                .get();
    }

    /**
     * <p>group collection elements into multimap by specified field, aggregating duplicates into lists.</p>
     *
     * <p>example with age grouping:</p>
     * <pre>{@code
     * List<Person> people = Arrays.asList(
     *     new Person("name1", 10),
     *     new Person("name2", 11),
     *     new Person("name3", 11),  // duplicate key
     *     new Person("name4", 12)
     * );
     *
     * // Resulting multimap structure:
     * // {
     * //   10=[Person[name="name1", age=10]],
     * //   11=[Person[name="name2", age=11], Person[name="name3", age=11]],
     * //   12=[Person[name="name4", age=12]]
     * // }
     * Map<Integer, List<Person>> ageGroups = Converts.toMultiMap(people, Person::getAge);
     * }</pre>
     *
     * @param inputs       source collection with potential duplicate keys
     * @param getKeyAction key extractor function (e.g. {@code Person::getAge})
     * @param <K>          type of grouping keys
     * @param <V>          type of collection elements
     * @return multimap where keys map to lists of associated elements
     */
    public static <K, V> Map<K, List<V>> toMultiMap(Iterable<V> inputs, Function<V, K> getKeyAction) {
        return Action.<Map<K, List<V>>>infer(Nil.isEmpty(inputs))
                .then(Collections::newHashMap)
                .otherwise(() -> Collections.ofUnknownSizeStream(inputs).collect(Collectors.groupingBy(getKeyAction, Collectors.mapping(item -> item, Collectors.toList()))))
                .get();
    }

    /**
     * <p>group collection elements into multimap with key-value pairs, aggregating duplicate keys into value lists.</p>
     *
     * <p>example with bi-field grouping:</p>
     * <pre>{@code
     * List<Person> people = Arrays.asList(
     *     new Person("name1", 10),
     *     new Person("name2", 11),
     *     new Person("name3", 11),  // duplicate key
     *     new Person("name4", 12)
     * );
     *
     * // Creates age-to-names mapping:
     * // {
     * //   10=["name1"],
     * //   11=["name2", "name3"],
     * //   12=["name4"]
     * // }
     * Map<Integer, List<String>> ageNameGroups = Converts.toMultiMap(
     *     people,
     *     Person::getAge,
     *     Person::getName
     * );
     * }</pre>
     *
     * @param inputs         source collection containing elements
     * @param getKeyAction   key extractor function (e.g. {@code Person::getAge})
     * @param getValueAction value extractor function (e.g. {@code Person::getName})
     * @param <T>            type of elements in source collection
     * @param <K>            type of grouping keys
     * @param <V>            type of aggregated values
     * @return multimap where keys map to lists of extracted values
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
     * <p>Converts to enum instance by enum constant name.</p>
     *
     * <p>Example:</p>
     * <pre>{@code
     * public enum GenderType {
     *     MAN,
     *     WOMAN,
     *     UNKNOWN;
     *
     *     public static void main(String[] args) {
     *         // Results: GenderType.WOMAN
     *         Converts.toEnumByName("WOMAN", GenderType.class);
     *     }
     * }
     * }</pre>
     *
     * @param enumFieldName the enum constant name (case-sensitive)
     * @param enumClass     the enum class type
     * @param <E>           the type of the enum
     * @return the corresponding enum constant
     */
    public static <E extends Enum<E>> E toEnumByName(String enumFieldName, Class<E> enumClass) {
        return Try.of(() -> Enum.valueOf(enumClass, enumFieldName)).getOrNull();
    }

    /**
     * <p>converts to enum instance by enum constant's field value.</p>
     *
     * <p>Usage notes:</p>
     * <ol>
     * <li><p>Typical usage with single match field:</p>
     * <pre>{@code
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
     *         // Outputs GenderType.WOMAN (field code match)
     *         Converts.toEnumByValue(2, GenderType.class);
     *         // Outputs GenderType.WOMAN (field description match)
     *         Converts.toEnumByValue("woman", GenderType.class);
     *
     *         // Outputs null (value mismatch)
     *         Converts.toEnumByValue("WOman", GenderType1.class);
     *         // Outputs null (type mismatch)
     *         Converts.toEnumByValue("2", GenderType.class);
     *     }
     * }
     * }</pre></li>
     *
     * <li><p>Supports varargs field types:</p>
     * <pre>{@code
     * @Getter
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
     *         // Outputs GenderType.WOMAN (any value of names match)
     *         Converts.toEnumByValue("woman", GenderType.class);
     *         // Outputs GenderType.WOMAN (any value of names match)
     *         Converts.toEnumByValue("Woman", GenderType.class);
     *         // Outputs GenderType.WOMAN (any value of names match)
     *         Converts.toEnumByValue("WOMAN", GenderType.class);
     *         // Outputs null (value mismatch)
     *         Converts.toEnumByValue("WOman", GenderType.class);
     *     }
     * }
     * }</pre></li>
     *
     * <li><p>Supports complex field types:</p>
     * <pre>{@code
     * @Getter
     * public enum GenderType {
     *     MAN(1, List.of(4, 5, 6), List.of("m"), "man"),
     *     WOMAN(2, List.of(7, 8, 9), List.of("wo", "Wo", "WO"), "woman", "Woman", "WOMAN"),
     *     UNKNOWN(3, List.of(10, 11, 12), List.of("un", "Un"), "unknown", "Unknown");
     *
     *     GenderType3(int code, List<Integer> codes, List<String> names1, String... names2) {
     *         this.code = code;
     *         this.codes = codes;
     *         this.names1 = names1;
     *         this.names2 = names2;
     *     }
     *
     *     private final String[] names;
     *
     *     public static void main(String[] args) {
     *         // Outputs GenderType.WOMAN (field code match)
     *         Converts.toEnumByValue(2, GenderType.class);
     *
     *         // Outputs GenderType.WOMAN (field codes match)
     *         Converts.toEnumByValue(8, GenderType.class);
     *
     *         // Outputs GenderType.WOMAN (field names1 match)
     *         Converts.toEnumByValue("wo", GenderType.class);
     *         // Outputs GenderType.WOMAN (field names1 match)
     *         Converts.toEnumByValue("Wo", GenderType.class);
     *         // Outputs GenderType.WOMAN (field names1 match)
     *         Converts.toEnumByValue("WO", GenderType.class);
     *
     *         // Outputs GenderType.WOMAN (field names2 match)
     *         Converts.toEnumByValue("woman", GenderType.class);
     *         // Outputs GenderType.WOMAN (field names2 match)
     *         Converts.toEnumByValue("Woman", GenderType.class);
     *         // Outputs GenderType.WOMAN (field names2 match)
     *         Converts.toEnumByValue("WOMAN", GenderType.class);
     *
     *         // Outputs null (value mismatch)
     *         Converts.toEnumByValue(18, GenderType.class);
     *         // Outputs null (value mismatch)
     *         Converts.toEnumByValue("wom", GenderType.class);
     *         // Outputs null (value mismatch)
     *         Converts.toEnumByValue("WOman", GenderType.class);
     *     }
     * }
     * }</pre></li>
     *
     * <li><p>Returns null for enums without fields:</p>
     * <pre>{@code
     * public enum GenderType {
     *     MAN,
     *     WOMAN,
     *     UNKNOWN;
     *
     *     public static void main(String[] args) {
     *         // Outputs null (no fields to match)
     *         Converts.toEnumByValue("WOMAN", GenderType.class);
     *     }
     * }
     * }</pre></li>
     * </ol>
     *
     * @param comparedEnumFieldValue the value to match against enum constants' fields
     * @param enumClass              the target enum class type
     * @param <E>                    the type of the enum
     * @return matching enum constant or {@code null} if not found
     * @see #getSupportedConvertEnumValueMappingEnumObjectsMap(Class)
     */
    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    public static <E extends Enum<E>> E toEnumByValue(Object comparedEnumFieldValue, Class<E> enumClass) {
        if (Nil.isAnyNull(comparedEnumFieldValue, enumClass)) {
            return null;
        }
        return (E) MATCH_BY_EQUAL_ENUM_CACHE.computeIfAbsent(
                getEnumCacheKey(comparedEnumFieldValue, enumClass),
                ignore -> getSupportedConvertEnumValueMappingEnumObjectsMap(enumClass).entrySet()
                        .stream()
                        .flatMap(entry -> {
                            if (Comparators.equals(entry.getKey(), comparedEnumFieldValue)
                                    ||
                                    (entry.getKey().getClass().isArray() && Collections.contains((Object[]) entry.getKey(), comparedEnumFieldValue))
                                    ||
                                    (entry.getKey() instanceof Iterable iterableEnumValue && Collections.contains(iterableEnumValue, comparedEnumFieldValue))
                            ) {
                                return Stream.of(entry.getValue());
                            }
                            return Stream.empty();
                        })
                        .flatMap(Collection::stream)
                        .findFirst()
                        .orElse(null)
        );
    }

    /**
     * <p>converts to enum instance by enum constant's field value.</p>
     *
     * <p>Usage notes:</p>
     * <ol>
     * <li><p>Typical usage with single match field:</p>
     * <pre>{@code
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
     *         // Outputs GenderType.WOMAN (the most similar field description match)
     *         Converts.toEnumByValueMostSimilar("woman", GenderType.class);
     *         // Outputs GenderType.WOMAN (the most similar field description match)
     *         Converts.toEnumByValueMostSimilar("WOman", GenderType1.class);
     *
     *         // Outputs GenderType.UNKNOWN (the most similar field description match , it seems unreasonable because of the most similar algorithm, you must know this)
     *         Converts.toEnumByValueMostSimilar("2", GenderType.class);
     *     }
     * }
     * }</pre></li>
     *
     * <li><p>Supports varargs field types:</p>
     * <pre>{@code
     * @Getter
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
     *         // Outputs GenderType.WOMAN (the most similar value of names match)
     *         Converts.toEnumByValueMostSimilar("woman", GenderType.class);
     *         // Outputs GenderType.WOMAN (the most similar value of names match)
     *         Converts.toEnumByValueMostSimilar("Woman", GenderType.class);
     *         // Outputs GenderType.WOMAN (the most similar value of names match)
     *         Converts.toEnumByValueMostSimilar("WOMAN", GenderType.class);
     *         // Outputs GenderType.WOMAN (the most similar value of names match)
     *         Converts.toEnumByValueMostSimilar("WOman", GenderType.class);
     *
     *         // Outputs GenderType.UNKNOWN (the most similar matches names, it seems unreasonable because of the most similar algorithm, you must know this)
     *         Converts.toEnumByValueMostSimilar("2", GenderType.class);
     *     }
     * }
     * }</pre></li>
     *
     * <li><p>Supports complex field types:</p>
     * <pre>{@code
     * @Getter
     * public enum GenderType {
     *     MAN(1, List.of(4, 5, 6), List.of("m"), "man"),
     *     WOMAN(2, List.of(7, 8, 9), List.of("wo", "Wo", "WO"), "woman", "Woman", "WOMAN"),
     *     UNKNOWN(3, List.of(10, 11, 12), List.of("un", "Un"), "unknown", "Unknown");
     *
     *     GenderType3(int code, List<Integer> codes, List<String> names1, String... names2) {
     *         this.code = code;
     *         this.codes = codes;
     *         this.names1 = names1;
     *         this.names2 = names2;
     *     }
     *
     *     private final String[] names;
     *
     *     public static void main(String[] args) {
     *         // Outputs GenderType.WOMAN (the most similar field names1 match)
     *         Converts.toEnumByValueMostSimilar("wo", GenderType.class);
     *         // Outputs GenderType.WOMAN (the most similar field names1 match)
     *         Converts.toEnumByValueMostSimilar("Wo", GenderType.class);
     *         // Outputs GenderType.WOMAN (the most similar field names1 match)
     *         Converts.toEnumByValueMostSimilar("WO", GenderType.class);
     *
     *         // Outputs GenderType.WOMAN (the most similar field names2 match)
     *         Converts.toEnumByValueMostSimilar("woman", GenderType.class);
     *         // Outputs GenderType.WOMAN (the most similar field names2 match)
     *         Converts.toEnumByValueMostSimilar("Woman", GenderType.class);
     *         // Outputs GenderType.WOMAN (the most similar field names2 match)
     *         Converts.toEnumByValueMostSimilar("WOMAN", GenderType.class);
     *         // Outputs GenderType.WOMAN (the most similar field names2 match)
     *         Converts.toEnumByValueMostSimilar("WOman", GenderType.class);
     *
     *         // Outputs GenderType.UNKNOWN (the most similar matches names1 or names2, it seems unreasonable because of the most similar algorithm, you must know this)
     *         Converts.toEnumByValueMostSimilar("2", GenderType.class);
     *     }
     * }
     * }</pre></li>
     *
     * <li><p>Returns null for enums without fields:</p>
     * <pre>{@code
     * public enum GenderType {
     *     MAN,
     *     WOMAN,
     *     UNKNOWN;
     *
     *     public static void main(String[] args) {
     *         // Outputs null (no fields to match)
     *         Converts.toEnumByValueMostSimilar("WOMAN", GenderType.class);
     *     }
     * }
     * }</pre></li>
     * </ol>
     *
     * @param comparedEnumFieldValue the value to match against enum constants' fields
     * @param enumClass              the target enum class type
     * @param <E>                    the type of the enum
     * @return matching enum constant or {@code null} if not found
     * @see Strings#getMostSimilar(String, Collection)
     * @see #getSupportedConvertEnumValueMappingEnumObjectsMap(Class)
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    @CanIgnoreReturnValue
    public static <E extends Enum<E>> E toEnumByValueMostSimilar(String comparedEnumFieldValue, Class<E> enumClass) {
        return (E) MATCH_BY_MOST_SIMILAR_ENUM_CACHE.computeIfAbsent(
                getEnumCacheKey(comparedEnumFieldValue, enumClass),
                ignore -> {
                    List<Map<String, List<E>>> stringEnumValueMappingEnumObjectsMaps = getSupportedConvertEnumValueMappingEnumObjectsMap(enumClass).entrySet()
                            .stream()
                            .flatMap(entry -> {
                                if (entry.getKey() instanceof String stringEnumValue) {
                                    return Stream.of(Collections.ofHashMap(stringEnumValue, entry.getValue()));
                                }
                                if (entry.getKey().getClass().isArray() && String.class == entry.getKey().getClass().getComponentType()) {
                                    return Stream.of(Arrays.stream(((String[]) entry.getKey()))
                                            .map(stringEnumValue -> Collections.ofPair(stringEnumValue, entry.getValue()))
                                            .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue))
                                    );
                                }
                                if (entry.getKey() instanceof Iterable<?> iterableEnumValue && Nil.isNotEmpty(iterableEnumValue) && Collections.getFirst(iterableEnumValue).orElseThrow() instanceof String) {
                                    return Stream.of(Collections.ofUnknownSizeStream(iterableEnumValue)
                                            .map(stringEnumValue -> Collections.ofPair((String) stringEnumValue, entry.getValue()))
                                            .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue))
                                    );
                                }
                                return Stream.empty();
                            })
                            .toList();
                    Set<String> stringEnumValues = stringEnumValueMappingEnumObjectsMaps.stream()
                            .map(Map::keySet)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toSet());
                    String theMostSimilarStringEnumValue = Strings.getMostSimilar(comparedEnumFieldValue, stringEnumValues);
                    return stringEnumValueMappingEnumObjectsMaps.stream()
                            .filter(stringEnumValueMappingEnumObjectsMap -> stringEnumValueMappingEnumObjectsMap.containsKey(theMostSimilarStringEnumValue))
                            .map(stringEnumValueMappingEnumObjectsMap -> stringEnumValueMappingEnumObjectsMap.get(theMostSimilarStringEnumValue))
                            .flatMap(Collection::stream)
                            .findFirst()
                            .orElse(null);
                }
        );
    }

    /**
     * <p>get the enum value mapping enums map.</p>
     *
     * <p>Usage notes:</p>
     * <pre>{@code
     * @Getter
     * public enum GenderType {
     *     MAN(1, List.of(4, 5, 6), List.of("m"), "man"),
     *     WOMAN(2, List.of(7, 8, 9), List.of("wo", "Wo", "WO"), "woman", "Woman", "WOMAN"),
     *     UNKNOWN(3, List.of(10, 11, 12), List.of("un", "Un"), "unknown", "Unknown");
     *
     *     GenderType3(int code, List<Integer> codes, List<String> names1, String... names2) {
     *         this.code = code;
     *         this.codes = codes;
     *         this.names1 = names1;
     *         this.names2 = names2;
     *     }
     *
     *     private final String[] names;
     *
     *     public static void main(String[] args) {
     *         // the output map likes:
     *         // {
     *         //   1 :                             [ GenderType.MAN ],
     *         //   2 :                             [ GenderType.WOMAN ],
     *         //   3 :                             [ GenderType.UNKNOWN ],
     *         //
     *         //   [4, 5, 6] :                     [ GenderType.MAN ],
     *         //   [7, 8, 9] :                     [ GenderType.WOMAN ],
     *         //   [10, 11, 12] :                  [ GenderType.UNKNOWN ],
     *         //
     *         //   [ "m" ] :                       [ GenderType.MAN ],
     *         //   [ "wo", "Wo", "WO" ] :          [ GenderType.WOMAN ]
     *         //   [ "un", "Un" ] :                [ GenderType.UNKNOWN ],
     *         //
     *         //   [ "man" ] :                     [ GenderType.MAN ],
     *         //   [ "woman", "Woman", "WOMAN" ] : [ GenderType.WOMAN ],
     *         //   [ "unknown", "Unknown" ] :      [ GenderType.UNKNOWN ],
     *         // }
     *         Converts.getSupportedConvertEnumValueMappingEnumObjectsMap(GenderType.class);
     *     }
     * }
     * }</pre>
     *
     * @param enumClass the enum class
     * @param <E>       the type of the enum
     * @return the enum value mapping enums map
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    private static <E extends Enum<E>> Map<Object, List<E>> getSupportedConvertEnumValueMappingEnumObjectsMap(Class<E> enumClass) {
        return (Map<Object, List<E>>) ENUM_CACHE.computeIfAbsent(
                enumClass,
                ignore -> {
                    List<String> enumObjectNames = Arrays.stream(enumClass.getEnumConstants())
                            .map(Enum::name)
                            .toList();
                    return Arrays.stream(Reflects.getFields(enumClass))
                            .filter(Enums::isNotInternalFieldName)
                            .filter(enumField -> Collections.notContains(enumObjectNames, enumField.getName()))
                            .filter(Converts::supportEnumConvert)
                            .map(enumField -> Arrays.stream(enumClass.getEnumConstants())
                                    .map(enumObject -> Collections.ofPair(Reflects.getFieldValueIgnoreThrowable(enumObject, enumField), enumObject))
                                    .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue))
                            )
                            .flatMap(enumValueMappingEnumObjectMap -> enumValueMappingEnumObjectMap.entrySet().stream())
                            .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
                }
        );
    }

    private static boolean supportEnumConvert(Field enumField) {
        return Classes.isNotAssignable(enumField.getType(), Map.class);
    }

    private static <E extends Enum<E>> String getEnumCacheKey(Object comparedEnumFieldValue, Class<E> enumClass) {
        return Strings.format("{}-{}-{}", enumClass.getName(), comparedEnumFieldValue.getClass(), comparedEnumFieldValue);
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
     * <p>Splits a numeric string by separator and converts to enum constants collection.</p>
     *
     * <p>Usage patterns:</p>
     * <ol>
     * <li><p>Basic usage with single matching field:</p>
     * <pre>{@code
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
     *         // Outputs [MAN, WOMAN, UNKNOWN]
     *         Converts.toEnumsByString("1, 2, 3", ",", GenderType.class);
     *     }
     * }
     * }</pre></li>
     *
     * <li><p>Supports multiple numeric fields:</p>
     * <pre>{@code
     * @Getter
     * @AllArgsConstructor
     * public enum GenderType {
     *     MAN(1, 10, "Man"),
     *     WOMAN(2, 11, "Woman"),
     *     UNKNOWN(3, 12, "Unknown");
     *
     *     private final int code1;
     *     private final int code2;
     *     private final String description;
     *
     *     public static void main(String[] args) {
     *         // Outputs [MAN, WOMAN, UNKNOWN] (matches code1)
     *         Converts.toEnumsByString("1, 2, 3", ",", GenderType.class);
     *         // Outputs [MAN, WOMAN, UNKNOWN] (matches code2)
     *         Converts.toEnumsByString("10, 11, 12", ",", GenderType.class);
     *     }
     * }
     * }</pre></li>
     *
     * <li><p>Returns null elements for enums without fields:</p>
     * <pre>{@code
     * public enum GenderType {
     *     MAN,
     *     WOMAN,
     *     UNKNOWN;
     *
     *     public static void main(String[] args) {
     *         // Outputs [null, null, null] (no fields to match)
     *         Converts.toEnumsByString("1, 2, 3", ",", GenderType.class);
     *     }
     * }
     * }</pre></li>
     * </ol>
     *
     * @param input       the input string containing numeric values
     * @param separator   the delimiter character/sequence
     * @param outputClass the target enum class
     * @param <E>         the enum type
     * @return list of matching enum constants (may contain {@code null} elements)
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