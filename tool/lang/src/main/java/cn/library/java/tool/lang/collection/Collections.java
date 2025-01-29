package cn.library.java.tool.lang.collection;

import cn.library.java.contract.constant.collection.CollectionConstant;
import cn.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.library.java.tool.lang.compare.Comparators;
import cn.library.java.tool.lang.convert.Converts;
import cn.library.java.tool.lang.functional.Action;
import cn.library.java.tool.lang.object.Nil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.dromara.hutool.core.array.ArrayUtil;
import org.dromara.hutool.core.collection.CollUtil;
import org.dromara.hutool.core.collection.ListUtil;
import org.dromara.hutool.core.collection.iter.IterUtil;
import org.dromara.hutool.core.collection.set.ConcurrentHashSet;
import org.dromara.hutool.core.collection.set.SetUtil;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * toolkit for collection
 *
 * @author wjm
 * @since 2020-12-15 12:40
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Collections {

    /**
     * create an empty array
     *
     * @param arrayType the array type
     * @param <T>       the output type
     * @return an empty array
     */
    public static <T> T[] newArray(Class<T> arrayType) {
        return newArray(arrayType, CollectionConstant.CAPACITY_EMPTY);
    }

    /**
     * create a specified init capacity array
     *
     * @param arrayType    the array type
     * @param initCapacity the specified capacity
     * @param <T>          the output type
     * @return a specified init capacity array
     */
    public static <T> T[] newArray(Class<T> arrayType, int initCapacity) {
        return ArrayUtil.newArray(arrayType, initCapacity);
    }

    /**
     * create an empty array list
     *
     * @param <T> the output type
     * @return an empty array list
     */
    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    /**
     * create a specified init capacity array list
     *
     * @param initCapacity the specified capacity
     * @param <T>          the output type
     * @return a specified init capacity array list
     */
    public static <T> List<T> newArrayList(int initCapacity) {
        return new ArrayList<>(initCapacity);
    }

    /**
     * create an empty linked list
     *
     * @param <T> the output type
     * @return an empty linked list
     */
    public static <T> List<T> newLinkedList() {
        return new LinkedList<>();
    }

    /**
     * create an empty immutable list
     *
     * @param <T> the output type
     * @return an empty immutable list
     */
    public static <T> List<T> newImmutableList() {
        return List.of();
    }

    /**
     * create an empty hash set
     *
     * @param <T> the output type
     * @return an empty hash set
     */
    public static <T> Set<T> newHashSet() {
        return new HashSet<>();
    }

    /**
     * create a specified init capacity hash set
     *
     * @param initCapacity the specified capacity
     * @param <T>          the output type
     * @return a specified init capacity hash set
     */
    public static <T> Set<T> newHashSet(int initCapacity) {
        return HashSet.newHashSet(initCapacity);
    }

    /**
     * create an empty linked hash set
     *
     * @param <T> the output type
     * @return an empty linked hash set
     */
    public static <T> Set<T> newLinkedHashSet() {
        return new LinkedHashSet<>();
    }

    /**
     * create a specified init capacity linked hash set
     *
     * @param initCapacity the specified capacity
     * @param <T>          the output type
     * @return a specified init capacity linked hash set
     */
    public static <T> Set<T> newLinkedHashSet(int initCapacity) {
        return LinkedHashSet.newLinkedHashSet(initCapacity);
    }

    /**
     * create an empty {@link CopyOnWriteArraySet}
     *
     * @param <T> the output type
     * @return an empty linked {@link CopyOnWriteArraySet}
     */
    public static <T> Set<T> newCopyOnWriteArraySet() {
        return new CopyOnWriteArraySet<>();
    }

    /**
     * create an empty concurrent hash set
     *
     * @param <T> the output type
     * @return an empty concurrent hash set
     */
    public static <T> Set<T> newConcurrentHashSet() {
        return new ConcurrentHashSet<>(CollectionConstant.CAPACITY_DEFAULT_INITIALIZE_MAP);
    }

    /**
     * create a specified init capacity concurrent hash set
     *
     * @param initCapacity the specified capacity
     * @param <T>          the output type
     * @return a specified init capacity concurrent hash set
     */
    public static <T> Set<T> newConcurrentHashSet(int initCapacity) {
        return new ConcurrentHashSet<>(initCapacity);
    }

    /**
     * create an empty immutable set
     *
     * @param <T> the output type
     * @return an empty immutable set
     */
    public static <T> Set<T> newImmutableSet() {
        return Set.of();
    }

    /**
     * create a {@link CollectionConstant#CAPACITY_DEFAULT_INITIALIZE_MAP default capacity} hash map
     *
     * @param <K> the key type of map
     * @param <V> the value type of map
     * @return a {@link CollectionConstant#CAPACITY_DEFAULT_INITIALIZE_MAP default capacity} hash map
     */
    public static <K, V> Map<K, V> newHashMap() {
        return newHashMap(CollectionConstant.CAPACITY_DEFAULT_INITIALIZE_MAP);
    }

    /**
     * create a specified init capacity hash map
     *
     * @param initCapacity the specified capacity
     * @param <K>          the key type of map
     * @param <V>          the value type of map
     * @return a specified init capacity hash map
     */
    public static <K, V> Map<K, V> newHashMap(int initCapacity) {
        return HashMap.newHashMap(initCapacity);
    }

    /**
     * create a {@link CollectionConstant#CAPACITY_DEFAULT_INITIALIZE_MAP default capacity} concurrent hash map
     *
     * @param <K> the key type of map
     * @param <V> the value type of map
     * @return a {@link CollectionConstant#CAPACITY_DEFAULT_INITIALIZE_MAP default capacity} concurrent hash map
     */
    public static <K, V> ConcurrentMap<K, V> newConcurrentHashMap() {
        return newConcurrentHashMap(CollectionConstant.CAPACITY_DEFAULT_INITIALIZE_MAP);
    }

    /**
     * create a specified init capacity concurrent hash map
     *
     * @param initCapacity the specified capacity
     * @param <K>          the key type of map
     * @param <V>          the value type of map
     * @return a specified init capacity concurrent hash map
     */
    public static <K, V> ConcurrentMap<K, V> newConcurrentHashMap(int initCapacity) {
        return new ConcurrentHashMap<>(initCapacity);
    }

    /**
     * create an empty immutable map
     *
     * @param <K> the key type of map
     * @param <V> the value type of map
     * @return an empty immutable map
     */
    public static <K, V> Map<K, V> newImmutableMap() {
        return Map.of();
    }

    /**
     * create a containing the specified elements array.
     *
     * @param <T> the output type
     * @return a containing the specified elements array
     * @apiNote low performance to create an array because of the array generic special case.
     */
    @SafeVarargs
    public static <T> T[] ofArray(Class<T> arrayType, T... inputs) {
        return Converts.toArray(Arrays.stream(inputs).toList(), arrayType);
    }

    /**
     * create an empty array list
     *
     * @param <T> the output type
     * @return an empty array list
     */
    public static <T> List<T> ofSingletonList(T input) {
        return java.util.Collections.singletonList(input);
    }

    /**
     * create a containing the specified elements array list
     *
     * @param inputs the specified elements
     * @param <T>    the specified element type
     * @return a containing the specified elements array list
     */
    @SafeVarargs
    public static <T> List<T> ofArrayList(T... inputs) {
        return ListUtil.of(inputs);
    }

    /**
     * create a containing the specified elements array list
     *
     * @param inputs the specified elements
     * @param <T>    the specified element type
     * @return a containing the specified elements array list
     */
    public static <T> List<T> ofArrayList(Collection<? extends T> inputs) {
        return new ArrayList<>(inputs);
    }

    /**
     * create a containing the specified elements linked list
     *
     * @param inputs the specified elements
     * @param <T>    the specified element type
     * @return a containing the specified elements linked list
     */
    @SafeVarargs
    public static <T> List<T> ofLinkedList(T... inputs) {
        return ListUtil.ofLinked(inputs);
    }

    /**
     * create a containing the specified elements linked list
     *
     * @param inputs the specified elements
     * @param <T>    the specified element type
     * @return a containing the specified elements linked list
     */
    public static <T> List<T> ofLinkedList(Collection<? extends T> inputs) {
        return new LinkedList<>(inputs);
    }

    /**
     * create a containing the specified elements immutable list
     *
     * @param inputs the specified elements
     * @param <T>    the specified element type
     * @return a containing the specified elements immutable list
     */
    @SafeVarargs
    public static <T> List<T> ofImmutableList(T... inputs) {
        return List.of(inputs);
    }

    /**
     * create a containing the specified elements immutable list
     *
     * @param inputs the specified elements
     * @param <T>    the specified element type
     * @return a containing the specified elements immutable list
     */
    public static <T> List<T> ofImmutableList(Iterator<T> inputs) {
        return Action.<List<T>>ifEmpty(inputs)
                .then(Collections::newImmutableList)
                .otherwise(() -> ofUnknownSizeStream(inputs).toList())
                .get();
    }

    /**
     * create a containing the specified elements immutable list
     *
     * @param inputs the specified elements
     * @param <T>    the specified element type
     * @return a containing the specified elements immutable list
     */
    public static <T> List<T> ofImmutableList(Iterable<T> inputs) {
        return Action.<List<T>>ifEmpty(inputs)
                .then(Collections::newImmutableList)
                .otherwise(() -> ofUnknownSizeStream(inputs).toList())
                .get();
    }

    /**
     * create a containing the specified elements hash set
     *
     * @param inputs the specified elements
     * @param <T>    the specified element type
     * @return a containing the specified elements hash set
     */
    @SafeVarargs
    public static <T> Set<T> ofHashSet(T... inputs) {
        return SetUtil.of(inputs);
    }

    /**
     * create a containing the specified elements hash set
     *
     * @param inputs the specified elements
     * @param <T>    the specified element type
     * @return a containing the specified elements hash set
     */
    public static <T> Set<T> ofHashSet(Collection<? extends T> inputs) {
        return new HashSet<>(inputs);
    }

    /**
     * create a containing the specified elements linked hash set
     *
     * @param inputs the specified elements
     * @param <T>    the specified element type
     * @return a containing the specified elements linked hash set
     */
    @SafeVarargs
    public static <T> Set<T> ofLinkedHashSet(T... inputs) {
        return SetUtil.ofLinked(inputs);
    }

    /**
     * create a containing the specified elements linked hash set
     *
     * @param inputs the specified elements
     * @param <T>    the specified element type
     * @return a containing the specified elements linked hash set
     */
    public static <T> Set<T> ofLinkedHashSet(Collection<? extends T> inputs) {
        return new LinkedHashSet<>(inputs);
    }

    /**
     * create a containing the specified elements immutable set
     *
     * @param inputs the specified elements
     * @param <T>    the specified element type
     * @return a containing the specified elements immutable set
     */
    @SafeVarargs
    public static <T> Set<T> ofImmutableSet(T... inputs) {
        return Set.of(inputs);
    }

    /**
     * create a containing the specified elements immutable set
     *
     * @param inputs the specified elements
     * @param <T>    the specified element type
     * @return a containing the specified elements immutable set
     */
    public static <T> Set<T> ofImmutableSet(Iterator<T> inputs) {
        return Action.<Set<T>>ifEmpty(inputs)
                .then(Collections::newImmutableSet)
                .otherwise(() -> ofUnknownSizeStream(inputs).collect(Collectors.toUnmodifiableSet()))
                .get();
    }

    /**
     * create a containing single key and value pair
     *
     * @param key   the key
     * @param value the value
     * @param <K>   the key type
     * @param <V>   the value type
     * @return a containing single key and value pair
     */
    public static <K, V> AbstractMap.SimpleEntry<K, V> ofPair(K key, V value) {
        return new AbstractMap.SimpleEntry<>(key, value);
    }

    /**
     * create a containing the specified elements hash map
     *
     * @param key   the key of map
     * @param value the value of map
     * @param <K>   the key type of map
     * @param <V>   the value type of map
     * @return a containing the specified elements hash map
     */
    public static <K, V> Map<K, V> ofHashMap(K key, V value) {
        return com.vip.vjtools.vjkit.collection.MapUtil.newHashMap(key, value);
    }

    /**
     * create a containing the specified elements hash map
     *
     * @param keys   the key of map
     * @param values the value of map
     * @param <K>    the key type of map
     * @param <V>    the value type of map
     * @return a containing the specified elements hash map
     */
    public static <K, V> Map<K, V> ofHashMap(K[] keys, V[] values) {
        return com.vip.vjtools.vjkit.collection.MapUtil.newHashMap(keys, values);
    }

    /**
     * create a containing the specified elements hash map
     *
     * @param keys   the key of map
     * @param values the value of map
     * @param <K>    the key type of map
     * @param <V>    the value type of map
     * @return a containing the specified elements hash map
     */
    public static <K, V> Map<K, V> ofHashMap(List<K> keys, List<V> values) {
        return com.vip.vjtools.vjkit.collection.MapUtil.newHashMap(keys, values);
    }

    /**
     * create a containing the specified elements hash map
     *
     * @param inputs the specified elements
     * @param <K>    the key type of map
     * @param <V>    the value type of map
     * @return a containing the specified elements hash map
     */
    public static <K, V> Map<K, V> ofHashMap(Map<? extends K, ? extends V> inputs) {
        return new HashMap<>(inputs);
    }

    /**
     * create a containing the specified elements concurrent hash map
     *
     * @param inputs the specified elements
     * @param <K>    the key type of map
     * @param <V>    the value type of map
     * @return a containing the specified elements concurrent hash map
     */
    public static <K, V> ConcurrentMap<K, V> ofConcurrentHashMap(Map<? extends K, ? extends V> inputs) {
        return new ConcurrentHashMap<>(inputs);
    }

    /**
     * create a containing the specified elements immutable map
     *
     * @param key   the key of map
     * @param value the value of map
     * @param <K>   the key type of map
     * @param <V>   the value type of map
     * @return a containing the specified elements immutable map
     */
    public static <K, V> Map<K, V> ofImmutableMap(K key, V value) {
        return Map.of(key, value);
    }

    /**
     * create a containing the specified elements immutable map
     *
     * @param key1   the key1 of map
     * @param value1 the value1 of map
     * @param key2   the key2 of map
     * @param value2 the value2 of map
     * @param <K>    the key type of map
     * @param <V>    the value type of map
     * @return a containing the specified elements immutable map
     */
    public static <K, V> Map<K, V> ofImmutableMap(K key1, V value1, K key2, V value2) {
        return Map.of(key1, value1, key2, value2);
    }

    /**
     * create a containing the specified elements immutable map
     *
     * @param key1   the key1 of map
     * @param value1 the value1 of map
     * @param key2   the key2 of map
     * @param value2 the value2 of map
     * @param key3   the key3 of map
     * @param value3 the value3 of map
     * @param <K>    the key type of map
     * @param <V>    the value type of map
     * @return a containing the specified elements immutable map
     */
    public static <K, V> Map<K, V> ofImmutableMap(K key1, V value1, K key2, V value2, K key3, V value3) {
        return Map.of(key1, value1, key2, value2, key3, value3);
    }

    /**
     * create a containing the specified elements immutable map
     *
     * @param key1   the key1 of map
     * @param value1 the value1 of map
     * @param key2   the key2 of map
     * @param value2 the value2 of map
     * @param key3   the key3 of map
     * @param value3 the value3 of map
     * @param key4   the key4 of map
     * @param value4 the value4 of map
     * @param <K>    the key type of map
     * @param <V>    the value type of map
     * @return a containing the specified elements immutable map
     */
    public static <K, V> Map<K, V> ofImmutableMap(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4) {
        return Map.of(key1, value1, key2, value2, key3, value3, key4, value4);
    }

    /**
     * create a containing the specified elements immutable map
     *
     * @param key1   the key1 of map
     * @param value1 the value1 of map
     * @param key2   the key2 of map
     * @param value2 the value2 of map
     * @param key3   the key3 of map
     * @param value3 the value3 of map
     * @param key4   the key4 of map
     * @param value4 the value4 of map
     * @param key5   the key5 of map
     * @param value5 the value5 of map
     * @param <K>    the key type of map
     * @param <V>    the value type of map
     * @return a containing the specified elements immutable map
     */
    public static <K, V> Map<K, V> ofImmutableMap(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4, K key5, V value5) {
        return Map.of(key1, value1, key2, value2, key3, value3, key4, value4, key5, value5);
    }

    /**
     * create a containing the specified elements immutable map
     *
     * @param key1   the key1 of map
     * @param value1 the value1 of map
     * @param key2   the key2 of map
     * @param value2 the value2 of map
     * @param key3   the key3 of map
     * @param value3 the value3 of map
     * @param key4   the key4 of map
     * @param value4 the value4 of map
     * @param key5   the key5 of map
     * @param value5 the value5 of map
     * @param key6   the key6 of map
     * @param value6 the value6 of map
     * @param <K>    the key type of map
     * @param <V>    the value type of map
     * @return a containing the specified elements immutable map
     */
    public static <K, V> Map<K, V> ofImmutableMap(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4, K key5, V value5, K key6, V value6) {
        return Map.of(key1, value1, key2, value2, key3, value3, key4, value4, key5, value5, key6, value6);
    }

    /**
     * create a containing the specified elements immutable map
     *
     * @param key1   the key1 of map
     * @param value1 the value1 of map
     * @param key2   the key2 of map
     * @param value2 the value2 of map
     * @param key3   the key3 of map
     * @param value3 the value3 of map
     * @param key4   the key4 of map
     * @param value4 the value4 of map
     * @param key5   the key5 of map
     * @param value5 the value5 of map
     * @param key6   the key6 of map
     * @param value6 the value6 of map
     * @param key7   the key7 of map
     * @param value7 the value7 of map
     * @param <K>    the key type of map
     * @param <V>    the value type of map
     * @return a containing the specified elements immutable map
     */
    public static <K, V> Map<K, V> ofImmutableMap(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4, K key5, V value5, K key6, V value6, K key7, V value7) {
        return Map.of(key1, value1, key2, value2, key3, value3, key4, value4, key5, value5, key6, value6, key7, value7);
    }

    /**
     * create a containing the specified elements immutable map
     *
     * @param key1   the key1 of map
     * @param value1 the value1 of map
     * @param key2   the key2 of map
     * @param value2 the value2 of map
     * @param key3   the key3 of map
     * @param value3 the value3 of map
     * @param key4   the key4 of map
     * @param value4 the value4 of map
     * @param key5   the key5 of map
     * @param value5 the value5 of map
     * @param key6   the key6 of map
     * @param value6 the value6 of map
     * @param key7   the key7 of map
     * @param value7 the value7 of map
     * @param key8   the key8 of map
     * @param value8 the value8 of map
     * @param <K>    the key type of map
     * @param <V>    the value type of map
     * @return a containing the specified elements immutable map
     */
    public static <K, V> Map<K, V> ofImmutableMap(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4, K key5, V value5, K key6, V value6, K key7, V value7, K key8, V value8) {
        return Map.of(key1, value1, key2, value2, key3, value3, key4, value4, key5, value5, key6, value6, key7, value7, key8, value8);
    }

    /**
     * create a containing the specified elements immutable map
     *
     * @param key1   the key1 of map
     * @param value1 the value1 of map
     * @param key2   the key2 of map
     * @param value2 the value2 of map
     * @param key3   the key3 of map
     * @param value3 the value3 of map
     * @param key4   the key4 of map
     * @param value4 the value4 of map
     * @param key5   the key5 of map
     * @param value5 the value5 of map
     * @param key6   the key6 of map
     * @param value6 the value6 of map
     * @param key7   the key7 of map
     * @param value7 the value7 of map
     * @param key8   the key8 of map
     * @param value8 the value8 of map
     * @param key9   the key9 of map
     * @param value9 the value9 of map
     * @param <K>    the key type of map
     * @param <V>    the value type of map
     * @return a containing the specified elements immutable map
     */
    public static <K, V> Map<K, V> ofImmutableMap(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4, K key5, V value5, K key6, V value6, K key7, V value7, K key8, V value8, K key9, V value9) {
        return Map.of(key1, value1, key2, value2, key3, value3, key4, value4, key5, value5, key6, value6, key7, value7, key8, value8, key9, value9);
    }

    /**
     * create a containing the specified elements immutable map
     *
     * @param key1    the key1 of map
     * @param value1  the value1 of map
     * @param key2    the key2 of map
     * @param value2  the value2 of map
     * @param key3    the key3 of map
     * @param value3  the value3 of map
     * @param key4    the key4 of map
     * @param value4  the value4 of map
     * @param key5    the key5 of map
     * @param value5  the value5 of map
     * @param key6    the key6 of map
     * @param value6  the value6 of map
     * @param key7    the key7 of map
     * @param value7  the value7 of map
     * @param key8    the key8 of map
     * @param value8  the value8 of map
     * @param key9    the key9 of map
     * @param value9  the value9 of map
     * @param key10   the key10 of map
     * @param value10 the value10 of map
     * @param <K>     the key type of map
     * @param <V>     the value type of map
     * @return a containing the specified elements immutable map
     */
    public static <K, V> Map<K, V> ofImmutableMap(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4, K key5, V value5, K key6, V value6, K key7, V value7, K key8, V value8, K key9, V value9, K key10, V value10) {
        return Map.of(key1, value1, key2, value2, key3, value3, key4, value4, key5, value5, key6, value6, key7, value7, key8, value8, key9, value9, key10, value10);
    }

    /**
     * create a containing the specified elements immutable map
     *
     * @param inputs the specified elements
     * @param <K>    the key type of map
     * @param <V>    the value type of map
     * @return a containing the specified elements immutable map
     */
    public static <K, V> Map<K, V> ofImmutableMap(Map<? extends K, ? extends V> inputs) {
        return java.util.Collections.unmodifiableMap(inputs);
    }

    /**
     * create an unknown size stream of collection
     *
     * @param inputs the specified elements
     * @param <T>    the specified element type
     * @return an unknown size stream of collection
     */
    public static <T> Stream<T> ofUnknownSizeStream(Iterator<T> inputs) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(inputs, Spliterator.ORDERED), CollectionConstant.DEFAULT_PARALLEL_STREAM_ENABLE);
    }

    /**
     * create an unknown size stream of collection
     *
     * @param inputs the specified elements
     * @param <T>    the specified element type
     * @return an unknown size stream of collection
     */
    public static <T> Stream<T> ofUnknownSizeStream(Iterable<T> inputs) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(inputs.iterator(), Spliterator.ORDERED), CollectionConstant.DEFAULT_PARALLEL_STREAM_ENABLE);
    }

    /**
     * return {@link #newArrayList()} if the provide collection is null
     *
     * @param inputs the specified elements
     * @param <T>    the specified element type
     * @return return {@link #newArrayList()} if the provide collection is null
     */
    public static <T> Collection<T> emptyIfNull(Collection<T> inputs) {
        return Nil.isNull(inputs) ? newArrayList() : inputs;
    }

    /**
     * return {@link #newHashMap()} if the provide map is null
     *
     * @param input the specified map
     * @param <K>   the key type of map
     * @param <V>   the value type of map
     * @return return {@link #newHashMap()} if the provide collection is null
     */
    public static <K, V> Map<K, V> emptyIfNull(Map<K, V> input) {
        return Nil.isNull(input) ? newHashMap() : input;
    }

    /**
     * return true if the checked element is {@link CollectionConstant#EMPTY_ARRAY_STRING}
     *
     * @param input the checked element
     * @return return true if the checked element is {@link CollectionConstant#EMPTY_ARRAY_STRING}
     */
    public static boolean isEmptyArrayString(CharSequence input) {
        return Comparators.equals(CollectionConstant.EMPTY_ARRAY_STRING, input);
    }

    /**
     * return true if the checked element is {@link CollectionConstant#EMPTY_MAP_STRING}
     *
     * @param input the checked element
     * @return return true if the checked element is {@link CollectionConstant#EMPTY_MAP_STRING}
     */
    public static boolean isEmptyMapString(CharSequence input) {
        return Comparators.equals(CollectionConstant.EMPTY_MAP_STRING, input);
    }

    /**
     * return true if the checked element is {@link Nil#isBlank(CharSequence)} or {@link CollectionConstant#EMPTY_ARRAY_STRING}
     *
     * @param input the checked element
     * @return return true if the checked element is {@link Nil#isBlank(CharSequence)} or {@link CollectionConstant#EMPTY_ARRAY_STRING}
     */
    public static boolean isBlankOrEmptyArrayString(CharSequence input) {
        return Nil.isBlank(input) || isEmptyArrayString(input);
    }

    /**
     * return true if the checked element is {@link Nil#isBlank(CharSequence)} or {@link CollectionConstant#EMPTY_MAP_STRING}
     *
     * @param input the checked element
     * @return return true if the checked element is {@link Nil#isBlank(CharSequence)} or {@link CollectionConstant#EMPTY_MAP_STRING}
     */
    public static boolean isBlankOrEmptyMapString(CharSequence input) {
        return Nil.isBlank(input) || isEmptyMapString(input);
    }

    /**
     * reverse {@link #isEmptyArrayString(CharSequence)}
     *
     * @param input the checked element
     * @return return true if the checked element is not {@link CollectionConstant#EMPTY_ARRAY_STRING}
     */
    public static boolean isNotEmptyArrayString(CharSequence input) {
        return !isEmptyArrayString(input);
    }

    /**
     * reverse {@link #isEmptyMapString(CharSequence)}
     *
     * @param input the checked element
     * @return return true if the checked element is not {@link CollectionConstant#EMPTY_MAP_STRING}
     */
    public static boolean isNotEmptyMapString(CharSequence input) {
        return !isEmptyMapString(input);
    }

    /**
     * return true if the checked element is an array
     *
     * @param input the checked element
     * @return return true if the checked element is an array
     */
    public static boolean isArray(Class<?> input) {
        return Nil.isNotNull(input) && input.isArray();
    }

    /**
     * see {@link ArrayUtil#isArray(Object)}
     *
     * @param input the checked element
     * @return return true if the checked element is an array
     */
    public static boolean isArray(Object input) {
        return ArrayUtil.isArray(input);
    }

    /**
     * return true if the field data type is an iterable type.
     *
     * @param input the field
     * @return return true if the field data type is an iterable type.
     * @see #isIterable(Class)
     */
    public static boolean isIterable(Field input) {
        if (Nil.isNull(input)) {
            return false;
        }
        return isIterable(input.getType());
    }

    /**
     * <pre>
     * return true if the field data type is an iterable type.
     * 1. the class type is {@link Iterable}
     * 2. the class type is {@link Iterator}
     * 3. the class type is {@link Map}
     * 4. the class type is {@link Array}
     * </pre>
     *
     * @param input the class
     * @return return true if the class type is an iterable type.
     */
    public static boolean isIterable(Class<?> input) {
        if (Nil.isNull(input)) {
            return false;
        }
        return Iterable.class.isAssignableFrom(input)
                ||
                Iterator.class.isAssignableFrom(input)
                ||
                Map.class.isAssignableFrom(input)
                ||
                input.isArray();
    }

    /**
     * see {@link ArrayUtil#contains(Object[], Object)}
     *
     * @param input           the checked element
     * @param searchedElement the searched elements
     * @param <T>             the element type
     * @return return true if the checked element contains the searched element
     */
    public static <T> boolean contains(T[] input, T searchedElement) {
        return ArrayUtil.contains(input, searchedElement);
    }

    /**
     * see {@link CollUtil#contains(Collection, Object)}
     *
     * @param input           the checked element
     * @param searchedElement the searched elements
     * @param <T>             the element type
     * @return return true if the checked element contains the searched element
     */
    public static <T> boolean contains(Collection<T> input, T searchedElement) {
        return CollUtil.contains(input, searchedElement);
    }

    /**
     * see {@link CollUtil#safeContains(Collection, Object)}
     *
     * @param input           the checked element
     * @param searchedElement the searched elements
     * @return return true if the checked element contains the searched element and return false if not contains or occur throwable
     */
    public static boolean containsIgnoreThrowable(Collection<?> input, Object searchedElement) {
        return CollUtil.safeContains(input, searchedElement);
    }

    /**
     * return true if the input map is not null and the map contains the searched key
     *
     * @param input       the checked element
     * @param searchedKey the searched key
     * @return return true if the input map is not null and the map contains the searched key
     */
    public static <K, V> boolean containsKey(Map<K, V> input, K searchedKey) {
        return Nil.isNotNull(input) && input.containsKey(searchedKey);
    }

    /**
     * reverse {@link #contains(Collection, Object)}
     *
     * @param input           the checked element
     * @param searchedElement the searched elements
     * @param <T>             the element type
     * @return return true if the checked element not contains the searched element
     */
    public static <T> boolean notContains(Collection<T> input, T searchedElement) {
        return !contains(input, searchedElement);
    }

    /**
     * return true if the input map is null or the map not contains the searched key
     *
     * @param input       the checked element
     * @param searchedKey the searched key
     * @return return true if the checked element contains the searched element and return false if not contains or occur throwable
     */
    public static <K, V> boolean notContainsKey(Map<K, V> input, K searchedKey) {
        return !containsKey(input, searchedKey);
    }

    /**
     * return true if the checked element length is 1
     *
     * @param input the checked element
     * @return return true if the checked element length is 1
     */
    public static <T> boolean hasOnlyOneElement(T[] input) {
        return getSize(input) == CollectionConstant.LENGTH_ONE;
    }

    /**
     * return true if the checked element length is 1
     *
     * @param input the checked element
     * @return return true if the checked element length is 1
     */
    public static boolean hasOnlyOneElement(Collection<?> input) {
        return getSize(input) == CollectionConstant.LENGTH_ONE;
    }

    /**
     * return true if the checked element length is 1
     *
     * @param input the checked element
     * @return return true if the checked element length is 1
     */
    public static boolean hasOnlyOneElement(Iterable<?> input) {
        return hasOnlyOneElement(getIterator(input));
    }

    /**
     * return true if the checked element length is 1
     *
     * @param input the checked element
     * @return return true if the checked element length is 1
     */
    public static boolean hasOnlyOneElement(Iterator<?> input) {
        return getSize(input) == CollectionConstant.LENGTH_ONE;
    }

    /**
     * return true if the checked element length > 1
     *
     * @param input the checked element
     * @return return true if the checked element length > 1
     */
    public static <T> boolean hasMoreThanOneElement(T[] input) {
        return getSize(input) > CollectionConstant.LENGTH_ONE;
    }

    /**
     * return true if the checked element length > 1
     *
     * @param input the checked element
     * @return return true if the checked element length > 1
     */
    public static boolean hasMoreThanOneElement(Collection<?> input) {
        return getSize(input) > CollectionConstant.LENGTH_ONE;
    }

    /**
     * return true if the checked element length > 1
     *
     * @param input the checked element
     * @return return true if the checked element length > 1
     */
    public static boolean hasMoreThanOneElement(Iterable<?> input) {
        return hasMoreThanOneElement(getIterator(input));
    }

    /**
     * return true if the checked element length > 1
     *
     * @param input the checked element
     * @return return true if the checked element length > 1
     */
    public static boolean hasMoreThanOneElement(Iterator<?> input) {
        return getSize(input) > CollectionConstant.LENGTH_ONE;
    }

    /**
     * see {@link ArrayUtil#append(Object[], Object[])}
     *
     * @param inputs         the input element
     * @param appendElements the elements to append
     * @param <T>            the element type
     * @return after add
     */
    @SafeVarargs
    public static <T> T[] add(T[] inputs, T... appendElements) {
        return ArrayUtil.append(inputs, appendElements);
    }

    /**
     * see {@link ArrayUtil#append(Object[], Object[])}
     *
     * @param inputs         the input element
     * @param appendElements the elements to append
     * @param <T>            the element type
     * @return after add
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public static <T> T[] add(T[] inputs, Collection<T> appendElements) {
        return Action.<T[]>infer(Nil.isEmpty(appendElements))
                .then(() -> inputs)
                .otherwise(() -> (T[]) ArrayUtil.append(inputs, appendElements.toArray()))
                .get();
    }

    /**
     * see {@link CollUtil#addAll(Collection, Object[])}
     *
     * @param inputs         the input element
     * @param appendElements the elements to append
     * @param <T>            the collection type
     * @param <E>            the element type
     * @return after add
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public static <T extends Collection<E>, E> T add(T inputs, E[] appendElements) {
        return (T) CollUtil.addAll(inputs, appendElements);
    }

    /**
     * see {@link CollUtil#addAll(Collection, Iterator)}
     *
     * @param inputs         the input element
     * @param appendElements the elements to append
     * @param <T>            the collection type
     * @param <E>            the element type
     * @return after add
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public static <T extends Collection<E>, E> T add(T inputs, Iterator<E> appendElements) {
        return (T) CollUtil.addAll(inputs, appendElements);
    }

    /**
     * see {@link CollUtil#addAll(Collection, Iterable)}
     *
     * @param inputs         the input element
     * @param appendElements the elements to append
     * @param <T>            the collection type
     * @param <E>            the element type
     * @return after add
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public static <T extends Collection<E>, E> T add(T inputs, Iterable<E> appendElements) {
        return (T) CollUtil.addAll(inputs, appendElements);
    }

    /**
     * see {@link CollUtil#addAll(Collection, Enumeration)}
     *
     * @param inputs         the input element
     * @param appendElements the elements to append
     * @param <T>            the collection type
     * @param <E>            the element type
     * @return after add
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public static <T extends Collection<E>, E> T add(T inputs, Enumeration<E> appendElements) {
        return (T) CollUtil.addAll(inputs, appendElements);
    }

    /**
     * see {@link CollUtil#addAll(Collection, Object)}
     *
     * @param inputs         the input element
     * @param appendElements the elements to append
     * @param <T>            the collection type
     * @param <E>            the element type
     * @return after add
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public static <T extends Collection<E>, E> T add(T inputs, Object appendElements) {
        return (T) CollUtil.addAll(inputs, appendElements);
    }

    /**
     * return the specified map after add node
     *
     * @param input the input map
     * @param <K>   the key type of map
     * @param <V>   the value type of map
     * @return the specified map after add node
     */
    public static <K, V> Map<K, V> put(Map<K, V> input, K key, V value) {
        input = emptyIfNull(input);
        input.put(key, value);
        return input;
    }

    /**
     * see {@link CollUtil#removeAny(Collection, Object[])}
     *
     * @param inputs         the input element
     * @param removeElements the elements to remove
     * @param <T>            the collection type
     * @param <E>            the element type
     * @return after remove
     */
    @SafeVarargs
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public static <T extends Collection<E>, E> T remove(T inputs, E... removeElements) {
        return (T) Action.<Collection<E>>ifEmpty(inputs)
                .then(Collections::newArrayList)
                .otherwise(() -> CollUtil.removeAny(inputs, removeElements))
                .get();
    }

    /**
     * see {@link CollUtil#removeAny(Collection, Object[])}
     *
     * @param inputs         the input element
     * @param removeElements the elements to remove
     * @param <T1>           the collection type
     * @param <T2>           the collection type
     * @param <E>            the element type
     * @return after remove
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public static <T1 extends Collection<E>, T2 extends Collection<E>, E> T1 remove(T1 inputs, T2 removeElements) {
        if (Nil.isEmpty(inputs)) {
            return (T1) Collections.newArrayList();
        }
        removeElements.forEach(removeElement -> CollUtil.removeAny(inputs, removeElement));
        return inputs;
    }

    /**
     * remove the first element in specified array
     *
     * @param arrayType the array type
     * @param inputs    the input element
     * @param <T>       the element type
     * @return after remove the first element
     */
    public static <T> T[] removeFirst(Class<T> arrayType, T[] inputs) {
        if (Nil.isEmpty(inputs)) {
            return inputs;
        }
        T[] outputs = newArray(arrayType, inputs.length - 1);
        System.arraycopy(inputs, 1, outputs, 0, outputs.length);
        return outputs;
    }

    /**
     * remove the last element in specified array
     *
     * @param inputs the input element
     * @param <T>    the element type
     * @return the remove element
     */
    public static <T> T removeLast(List<T> inputs) {
        return inputs.removeLast();
    }

    /**
     * reverse the key value map to value key map
     *
     * @param input the input key value map
     * @param <K>   the key type of map
     * @param <V>   the value type of map
     * @return the value key map
     */
    public static <K, V> Map<V, K> reverse(Map<K, V> input) {
        return input.entrySet()
                .stream()
                .map(entry -> Collections.ofPair(entry.getValue(), entry.getKey()))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    /**
     * return the input element size
     *
     * @param input the input element
     * @return the size of input element
     */
    public static <T> int getSize(T[] input) {
        return Nil.isNull(input) ? CollectionConstant.LENGTH_ZERO : input.length;
    }

    /**
     * return the input element size
     *
     * @param input the input element
     * @return the size of input element
     */
    public static int getSize(Collection<?> input) {
        return Nil.isNull(input) ? CollectionConstant.LENGTH_ZERO : input.size();
    }

    /**
     * return the input element size
     *
     * @param input the input element
     * @return the size of input element
     */
    public static int getSize(Iterable<?> input) {
        return IterUtil.size(input);
    }

    /**
     * return the input element size
     *
     * @param input the input element
     * @return the size of input element
     */
    public static int getSize(Iterator<?> input) {
        return IterUtil.size(input);
    }

    /**
     * see {@link CollUtil#size(Object)}
     *
     * @param input the input element
     * @return the size of input element
     */
    public static int getSize(Object input) {
        return CollUtil.size(input);
    }

    /**
     * get the first element
     *
     * @param inputs the input elements
     * @param <T>    the element type
     * @return the first element
     */
    public static <T> Optional<T> getFirst(T[] inputs) {
        return Arrays.stream(inputs).findFirst();
    }

    /**
     * get the first element
     *
     * @param inputs the input elements
     * @param <T>    the element type
     * @return the first element
     */
    public static <T> Optional<T> getFirst(Iterable<T> inputs) {
        return getByIndex(inputs, CollectionConstant.INDEX_FIRST);
    }

    /**
     * get the second element
     *
     * @param inputs the input elements
     * @param <T>    the element type
     * @return the second element
     */
    public static <T> Optional<T> getSecond(Iterable<T> inputs) {
        return getByIndex(inputs, CollectionConstant.INDEX_SECOND);
    }

    /**
     * get the third element
     *
     * @param inputs the input elements
     * @param <T>    the element type
     * @return the third element
     */
    public static <T> Optional<T> getThird(Iterable<T> inputs) {
        return getByIndex(inputs, CollectionConstant.INDEX_THIRD);
    }

    /**
     * get the forth element
     *
     * @param inputs the input elements
     * @param <T>    the element type
     * @return the third element
     */
    public static <T> Optional<T> getForth(Iterable<T> inputs) {
        return getByIndex(inputs, CollectionConstant.INDEX_FORTH);
    }

    /**
     * get the last element
     *
     * @param inputs the input elements
     * @param <T>    the element type
     * @return the last element
     */
    public static <T> Optional<T> getLast(T[] inputs) {
        int inputLength = inputs.length;
        if (Nil.isZeroValue(inputLength)) {
            return Optional.empty();
        }
        return Optional.ofNullable(inputs[inputLength - 1]);
    }

    /**
     * get the last element
     *
     * @param inputs the input elements
     * @param <T>    the element type
     * @return the last element
     */
    public static <T> Optional<T> getLast(Collection<T> inputs) {
        return Optional.ofNullable(CollUtil.getLast(inputs));
    }

    /**
     * <pre>
     * get the next element.
     *
     * example code:
     *     {@code
     *        public static void main(String[] args) {
     *            List<Integer> inputs = List.of(3, 1, 2, 4, 2, 6);
     *            // the output is 4.
     *            Integer nextElement = Collections.getNext(inputs, input -> input.equals(2)).get();
     *        }
     *     }
     * </pre>
     *
     * @param inputs               the input elements
     * @param currentElementAction the current element
     * @param <T>                  the element type
     * @return the next element
     */
    public static <T> Optional<T> getNext(Iterable<T> inputs, Predicate<T> currentElementAction) {
        return Action.<Optional<T>>infer(Nil.isEmpty(inputs))
                .then(Optional::empty)
                .otherwise(() -> getNext(inputs.iterator(), currentElementAction))
                .get();
    }

    /**
     * get the next element, see {@link #getNext(Iterable, Predicate)}
     *
     * @param inputs               the input elements
     * @param currentElementAction the current element action
     * @param <T>                  the element type
     * @return the next element
     */
    public static <T> Optional<T> getNext(Iterator<T> inputs, Predicate<T> currentElementAction) {
        return Action.<Optional<T>>infer(Nil.isEmpty(inputs))
                .then(Optional::empty)
                .otherwise(() -> ofUnknownSizeStream(inputs)
                        .dropWhile(element -> !currentElementAction.test(element))
                        .skip(1)
                        .findFirst()
                )
                .get();
    }

    /**
     * get the specified index element
     *
     * @param inputs the input elements
     * @param index  the specified index
     * @param <T>    the element type
     * @return the specified index element
     */
    public static <T> Optional<T> getByIndex(Iterable<T> inputs, int index) {
        return getByIndex(getIterator(inputs), index);
    }

    /**
     * get the specified index element
     *
     * @param inputs the input elements
     * @param index  the specified index
     * @param <T>    the element type
     * @return the specified index element
     */
    public static <T> Optional<T> getByIndex(Iterator<T> inputs, int index) {
        return Optional.ofNullable(IterUtil.get(inputs, index));
    }

    /**
     * <pre>
     * get the minimum element of the given extracts {@link Comparable} collection.
     *
     * example code:
     *     {@code
     *        public static void main(String[] args) {
     *            List<Integer> inputs = List.of(3, 1, 6, 4, 5, 3);
     *            // the output is 1.
     *            Integer minimum = Collections.getMin(inputs).get();
     *        }
     *     }
     * </pre>
     *
     * @param inputs the input elements
     * @param <T>    the element type
     * @return the minimum element of the given extracts {@link Comparable} collection.
     */
    public static <T extends Comparable<? super T>> Optional<T> getMin(Iterable<T> inputs) {
        return getFirst(asc(inputs));
    }

    /**
     * <pre>
     * get the minimum value of a field in an element in the given extracts {@link Comparable} collection.
     *
     * example code:
     *     {@code
     *        @Data
     *        @SuperBuilder(toBuilder = true)
     *        public class Person {
     *            private String name;
     *            private Integer age;
     *
     *            public static void main(String[] args) {
     *                List<Person> inputs = List.of(
     *                        Person.builder().name("name1").age(10).build(),
     *                        Person.builder().name("name2").age(43).build(),
     *                        Person.builder().name("name3").age(1).build(),
     *                        Person.builder().name("name4").age(11).build()
     *                );
     *                // the output is Person(name="name3", age=1)
     *                Person minimum = Collections.getMin(inputs, Person::getAge).get();
     *            }
     *        }
     *     }
     * </pre>
     *
     * @param inputs        the input elements
     * @param mappingAction the field in an element in the given extracts {@link Comparable} collection to compare
     * @param <T>           the element type
     * @param <U>           the field in an element type
     * @return the minimum value of a field in an element in the given extracts {@link Comparable} collection.
     */
    public static <T, U extends Comparable<? super U>> Optional<T> getMin(Iterable<T> inputs, Function<T, U> mappingAction) {
        return getFirst(asc(inputs, mappingAction));
    }

    /**
     * <pre>
     * get the maximum element of the given extracts {@link Comparable} collection.
     *
     *  example:
     *
     *     {@code
     *        public static void main(String[] args) {
     *            List<Integer> inputs = List.of(3, 1, 6, 4, 5, 3);
     *            // the output is 6.
     *            Integer maximum = Collections.getMax(inputs).get();
     *        }
     *     }
     * </pre>
     *
     * @param inputs the input elements
     * @param <T>    the element type
     * @return the maximum element of the given extracts {@link Comparable} collection.
     */
    public static <T extends Comparable<? super T>> Optional<T> getMax(Iterable<T> inputs) {
        return getFirst(desc(inputs));
    }

    /**
     * <pre>
     * get the maximum value of a field in an element in the given extracts {@link Comparable} collection.
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
     *                        Person.builder().name("name2").age(43).build(),
     *                        Person.builder().name("name3").age(1).build(),
     *                        Person.builder().name("name4").age(11).build()
     *                );
     *                // the output is Person(name="name2", age=43)
     *                Person maximum = Collections.getMax(inputs, Person::getAge).get();
     *            }
     *
     *        }
     *     }
     * </pre>
     *
     * @param inputs        the input elements
     * @param mappingAction the specified field in collection element
     * @param <T>           the element type
     * @param <U>           the field in an element type
     * @return the maximum value of a field in an element in the given extracts {@link Comparable} collection.
     */
    public static <T, U extends Comparable<? super U>> Optional<T> getMax(Iterable<T> inputs, Function<T, U> mappingAction) {
        return getFirst(desc(inputs, mappingAction));
    }

    /**
     * get the map first node key
     *
     * @param input the input elements
     * @param <K>   the key type of map
     * @param <V>   the value type of map
     * @return the map first node key
     */
    public static <K, V> Optional<K> getMapFirstKey(Map<K, V> input) {
        return Nil.isNotEmpty(input) ? getFirst(input.keySet()) : Optional.empty();
    }

    /**
     * get the map first node value
     *
     * @param input the input elements
     * @param <K>   the key type of map
     * @param <V>   the value type of map
     * @return the map first node value
     */
    public static <K, V> Optional<V> getMapFirstValue(Map<K, V> input) {
        return Nil.isNotEmpty(input) ? getMapFirstKey(input).map(input::get).or(Optional::empty) : Optional.empty();
    }

    /**
     * get all map keys
     *
     * @param input the input elements
     * @param <K>   the key type of map
     * @param <V>   the value type of map
     * @return all map keys
     */
    public static <K, V> List<K> getMapKeys(Map<K, V> input) {
        return Nil.isNotEmpty(input) ? ofArrayList(input.keySet()) : newArrayList();
    }

    /**
     * get all map values
     *
     * @param input the input elements
     * @param <K>   the key type of map
     * @param <V>   the value type of map
     * @return all map values
     */
    public static <K, V> List<V> getMapValues(Map<K, V> input) {
        return Nil.isNotEmpty(input) ? ofArrayList(input.values()) : newArrayList();
    }

    /**
     * get map values
     *
     * @param input        the input elements
     * @param key          the key
     * @param valuesIfNull the result if null
     * @param <K>          the key type of map
     * @param <V>          the value type of map
     * @return values
     */
    public static <K, V> List<V> getMapValues(Map<K, List<V>> input, K key, List<V> valuesIfNull) {
        if (Nil.isEmpty(input)) {
            return newArrayList();
        }
        List<V> values = input.get(key);
        return Nil.isNull(values) ? valuesIfNull : values;
    }

    /**
     * get map values, return empty list if null.
     *
     * @param input the input elements
     * @param key   the key
     * @param <K>   the key type of map
     * @param <V>   the value type of map
     * @return values
     */
    public static <K, V> List<V> getMapValuesNotNull(Map<K, List<V>> input, K key) {
        return getMapValues(input, key, newArrayList());
    }

    /**
     * get the {@link Iterable#iterator() iterator} of {@link Iterable}
     *
     * @param inputs the input elements
     * @param <T>    the element type
     * @return the {@link Iterable#iterator() iterator} of {@link Iterable}
     */
    public static <T> Iterator<T> getIterator(Iterable<T> inputs) {
        return IterUtil.getIter(inputs);
    }

    /**
     * get the duplicate values
     *
     * @param inputs the input elements
     * @param <T>    the element type
     * @return the duplicate values
     */
    public static <T> List<T> getDuplicate(List<T> inputs) {
        return getDuplicate(inputs, duplicateFiled -> duplicateFiled);
    }

    /**
     * get the duplicate values
     *
     * @param inputs               the input elements
     * @param duplicateFiledGetter the duplicate filed getter
     * @param <T>                  the element type
     * @param <R>                  the duplicate filed type
     * @return the duplicate values
     */
    public static <T, R> List<R> getDuplicate(List<T> inputs, Function<T, R> duplicateFiledGetter) {
        return Action.<List<R>>ifEmpty(inputs)
                .then(Collections::newArrayList)
                .otherwise(() -> inputs.stream()
                        .collect(Collectors.groupingBy(duplicateFiledGetter, Collectors.counting()))
                        .entrySet()
                        .stream()
                        .filter(inputName -> inputName.getValue() > 1)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList())
                )
                .get();
    }

    /**
     * <pre>
     * asc the given {@link Comparable} collection.
     *
     *  example:
     *
     *     {@code
     *        public static void main(String[] args) {
     *            List<Integer> inputs = List.of(3, 1, 6, 4, 5, 3);
     *            // the output is [1, 3, 3, 4, 5, 6].
     *            List<Integer> outputs = Collections.asc(inputs);
     *        }
     *     }
     * </pre>
     *
     * @param inputs the input elements
     * @param <T>    the element type
     * @return after asc
     */
    public static <T extends Comparable<? super T>> List<T> asc(Iterable<T> inputs) {
        return asc(inputs, input -> input);
    }

    /**
     * <pre>
     * asc the given {@link Comparable} collection by the specified field in collection element.
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
     *                        Person.builder().name("name2").age(43).build(),
     *                        Person.builder().name("name3").age(1).build(),
     *                        Person.builder().name("name4").age(11).build()
     *                );
     *                // the output is [Person(name="name3", age=1), Person(name="name1", age=10), Person(name="name4", age=11), Person(name="name2", age=43)]
     *                List<Person> outputs = Collections.asc(inputs, Person::getAge);
     *            }
     *
     *        }
     *     }
     * </pre>
     *
     * @param inputs        the input elements
     * @param mappingAction the specified field in collection element
     * @param <T>           the element type
     * @param <U>           the field in collection element type
     * @return after asc
     */
    public static <T, U extends Comparable<? super U>> List<T> asc(Iterable<T> inputs, Function<T, U> mappingAction) {
        return Action.<List<T>>ifEmpty(inputs)
                .then(Collections::newArrayList)
                .otherwise(() -> ofUnknownSizeStream(inputs)
                        .sorted(Comparator.comparing(mappingAction))
                        .collect(Collectors.toList())
                )
                .get();
    }

    /**
     * <pre>
     * desc the given {@link Comparable} collection.
     *
     *  example:
     *
     *     {@code
     *        public static void main(String[] args) {
     *            List<Integer> inputs = List.of(3, 1, 6, 4, 5, 3);
     *            // the output is [6, 5, 4, 3, 3, 1].
     *            List<Integer> outputs = Collections.desc(inputs);
     *        }
     *     }
     * </pre>
     *
     * @param inputs the input elements
     * @param <T>    the element type
     * @return after desc
     */
    public static <T extends Comparable<? super T>> List<T> desc(Iterable<T> inputs) {
        return desc(inputs, input -> input);
    }

    /**
     * <pre>
     * desc the given {@link Comparable} collection by the specified field in collection element.
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
     *                        Person.builder().name("name2").age(43).build(),
     *                        Person.builder().name("name3").age(1).build(),
     *                        Person.builder().name("name4").age(11).build()
     *                );
     *                // the output is [Person(name="name2", age=43), Person(name="name4", age=11), Person(name="name1", age=10), Person(name="name3", age=1)]
     *                List<Person> outputs = Collections.desc(inputs, Person::getAge);
     *            }
     *
     *        }
     *     }
     * </pre>
     *
     * @param inputs        the input elements
     * @param mappingAction the specified field in collection element
     * @param <T>           the element type
     * @param <U>           the field in collection element type
     * @return after desc
     */
    public static <T, U extends Comparable<? super U>> List<T> desc(Iterable<T> inputs, Function<T, U> mappingAction) {
        return Action.<List<T>>ifEmpty(inputs)
                .then(Collections::newArrayList)
                .otherwise(() -> ofUnknownSizeStream(inputs)
                        .sorted(Comparator.comparing(mappingAction).reversed())
                        .collect(Collectors.toList())
                )
                .get();
    }

    /**
     * the same as {@link Converts#toMultiMap(Iterable, Function)}
     *
     * @param inputs       the input elements
     * @param getKeyAction the specified field to be map key in collection element
     * @param <K>          the key type of map
     * @param <V>          the value type of map
     * @return after group by
     */
    public static <K, V> Map<K, List<V>> groupBy(Iterable<V> inputs, Function<V, K> getKeyAction) {
        return Converts.toMultiMap(inputs, getKeyAction);
    }

    /**
     * <pre>
     * distinct the given collection by the specified field in collection element.
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
     *                        Person.builder().name("name2").age(10).build(),
     *                        Person.builder().name("name3").age(11).build(),
     *                        Person.builder().name("name4").age(11).build()
     *                );
     *                // the output is [Person(name="name1", age=10), Person(name="name3", age=11)]
     *                List<Person> outputs = Collections.distinct(inputs, Person::getAge);
     *            }
     *
     *        }
     *     }
     * </pre>
     *
     * @param inputs                   the input elements
     * @param getFieldToDistinctAction the specified field in collection element
     * @param <T>                      the element type
     * @param <U>                      the field in collection element type
     * @return after distinct
     */
    public static <T, U> List<T> distinct(Iterable<T> inputs, Function<? super T, U> getFieldToDistinctAction) {
        return Action.<List<T>>infer(Nil.isEmpty(inputs))
                .then(Collections::newArrayList)
                .otherwise(() -> ofUnknownSizeStream(inputs)
                        .filter(distinctSupporter(getFieldToDistinctAction))
                        .collect(Collectors.toList())
                )
                .get();
    }

    /**
     * internal function to support distinct the given collection by the specified field in collection element.
     *
     * @param getFieldToDistinctAction the specified field in collection element
     * @param <T>                      the element type
     * @param <U>                      the field in collection element type
     * @return the distinct support function
     * @see #distinct(Iterable, Function)
     */
    private static <T, U> Predicate<T> distinctSupporter(@NonNull Function<? super T, U> getFieldToDistinctAction) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return input -> {
            U seenResult = getFieldToDistinctAction.apply(input);
            if (Nil.isNull(seenResult)) {
                return false;
            }
            return Nil.isNull(seen.putIfAbsent(seenResult, true));
        };
    }

    /**
     * see {@link ArrayUtil#wrap(byte...)}
     *
     * @param inputs the input elements
     * @return after wrap
     */
    public static Byte[] wrap(byte... inputs) {
        return ArrayUtil.wrap(inputs);
    }

    /**
     * see {@link ArrayUtil#unWrap(Byte...)}
     *
     * @param inputs the input elements
     * @return after wrap
     */
    public static byte[] unWrap(Byte... inputs) {
        return ArrayUtil.unWrap(inputs);
    }

    /**
     * <pre>
     * flatten nested collection of one layer
     *
     *  example:
     *
     *     {@code
     *        public static void main(String[] args) {
     *            List<List<Integer>> inputs = List.of(
     *                    List.of(1, 2, 3, 4, 5),
     *                    List.of(6, 7, 8, 9, 10)
     *            );
     *            // the output is [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
     *            List<Integer> outputs = Collections.flattenNest1(inputs);
     *        }
     *     }
     * </pre>
     *
     * @param inputs the input elements
     * @param <T>    the element type
     * @param <N1>   the first layer collection type
     * @return after flatten
     */
    public static <T, N1 extends Iterable<T>> List<T> flattenNest1(Iterable<N1> inputs) {
        return Action.<List<T>>ifEmpty(inputs)
                .then(Collections::newArrayList)
                .otherwise(() -> ofUnknownSizeStream(inputs)
                        .flatMap(Collections::ofUnknownSizeStream)
                        .collect(Collectors.toList())
                )
                .get();
    }

    /**
     * <pre>
     * flatten nested collection of two layer
     *
     *  example:
     *
     *     {@code
     *        public static void main(String[] args) {
     *            List<List<List<Integer>>> inputs = List.of(
     *                    List.of(List.of(1, 2, 3, 4, 5), List.of(6, 7, 8, 9, 10)),
     *                    List.of(List.of(11, 12, 13, 14, 15), List.of(16, 17, 18, 19, 20))
     *            );
     *            // the output is [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]
     *            List<Integer> outputs = Collections.flattenNest2(inputs);
     *        }
     *     }
     * </pre>
     *
     * @param inputs the input elements
     * @param <T>    the element type
     * @param <N1>   the first layer collection type
     * @param <N2>   the second layer collection type
     * @return after flatten
     */
    public static <T, N1 extends Iterable<T>, N2 extends Iterable<N1>> List<T> flattenNest2(Iterable<N2> inputs) {
        return Action.<List<T>>ifEmpty(inputs)
                .then(Collections::newArrayList)
                .otherwise(() -> ofUnknownSizeStream(inputs)
                        .flatMap(Collections::ofUnknownSizeStream)
                        .flatMap(Collections::ofUnknownSizeStream)
                        .collect(Collectors.toList())
                )
                .get();
    }

    /**
     * <pre>
     * flatten nested collection of three layer
     *
     *  example:
     *
     *     {@code
     *        public static void main(String[] args) {
     *            List<List<List<List<Integer>>>> inputs = List.of(
     *                    List.of(List.of(List.of(1, 2, 3, 4, 5), List.of(6, 7, 8, 9, 10)), List.of(List.of(11, 12, 13, 14, 15), List.of(16, 17, 18, 19, 20))),
     *                    List.of(List.of(List.of(21, 22, 23, 24, 25), List.of(26, 27, 28, 29, 30)), List.of(List.of(31, 32, 33, 34, 35), List.of(36, 37, 38, 39, 40)))
     *            );
     *            // the output is [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40]
     *            List<Integer> outputs = Collections.flattenNest3(inputs);
     *        }
     *     }
     * </pre>
     *
     * @param inputs the input elements
     * @param <T>    the element type
     * @param <N1>   the first layer collection type
     * @param <N2>   the second layer collection type
     * @param <N3>   the third layer collection type
     * @return after flatten
     */
    public static <T, N1 extends Iterable<T>, N2 extends Iterable<N1>, N3 extends Iterable<N2>> List<T> flattenNest3(Iterable<N3> inputs) {
        return Action.<List<T>>ifEmpty(inputs)
                .then(Collections::newArrayList)
                .otherwise(() -> StreamSupport.stream(inputs.spliterator(), CollectionConstant.DEFAULT_PARALLEL_STREAM_ENABLE)
                        .flatMap(Collections::ofUnknownSizeStream)
                        .flatMap(Collections::ofUnknownSizeStream)
                        .flatMap(Collections::ofUnknownSizeStream)
                        .collect(Collectors.toList())
                )
                .get();
    }

    /**
     * <pre>
     * get the difference from the given collections.
     *
     *  example:
     *
     *  []        and [1,2,3]      => [1,2,3]
     *  [1,2,3]   and null         => [1,2,3]
     *  [1,2,3]   and []           => [1,2,3]
     *  [1]       and [1,2,3,4]    => [2,3,4]
     *  [1,2,3,4] and [1]          => [2,3,4]
     *  [1,2,3,4] and [1,5]        => [2,3,4,5]
     *  [1,5]     and [1,2,3,4]    => [2,3,4,5]
     *
     *  null      and null         => []
     *  null      and [1,2,3]      => [1,2,3]
     * </pre>
     *
     * @param input            the input elements
     * @param comparedElements the elements to compare
     * @param <T>              the element type
     * @return the difference from the given collections
     */
    public static <T> List<T> difference(List<T> input, List<T> comparedElements) {
        if (Nil.isAllNull(input, comparedElements)) {
            return newArrayList();
        }
        if (Nil.isEmpty(input)) {
            return comparedElements;
        }
        if (Nil.isEmpty(comparedElements)) {
            return input;
        }
        return Stream.concat(input.stream(), comparedElements.stream())
                .distinct()
                .filter(value -> notContains(input, value) || notContains(comparedElements, value))
                .collect(Collectors.toList());
    }

    /**
     * <pre>
     * get the different set from the given collections.
     * different set refers to the collection of all elements in one set that are not in another set.
     *
     * example:
     *
     *  [2,3,4] and [3,4,5]      => [2]
     *  [2,3,4] and [4,5,6]      => [2,3]
     *  [2,3,4] and [5,6,7]      => [2,3,4]
     *  [2,3,4] and [2,3,4]      => []
     *
     *  null    and null         => []
     *  [null]  and null         => []
     *  [null]  and [1,2,3]      => []
     *  [1,2,3] and [null]       => [1,2,3]
     * </pre>
     *
     * @param input1 the one collection
     * @param input2 the another collection
     * @param <T>    the collection element type
     * @return the different set from the given collections
     * @see CollUtil#subtract(Collection, Collection)
     */
    public static <T> List<T> differenceSet(Collection<T> input1, Collection<T> input2) {
        return CollUtil.subtractToList(input1, input2);
    }

    /**
     * get the intersection from the given collections.
     *
     * @param inputs the given collection
     * @param <T>    the collection type
     * @param <E>    the collection element type
     * @return the intersection from the given collections
     * @see CollUtil#intersection(Collection[])
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public static <T extends Collection<E>, E> T intersection(T... inputs) {
        return (T) CollUtil.intersection(inputs);
    }

}