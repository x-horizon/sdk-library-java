package cn.srd.itcp.sugar.tools.core;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.srd.itcp.sugar.tools.constant.StringPool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/**
 * 集合工具
 *
 * @author wjm
 * @date 2020/12/15 12:40
 */
public class CollectionsUtil extends CollUtil {

    private CollectionsUtil() {
    }

    // ==================================== 获取集合元素 ====================================

    /**
     * 获取 List 第 2 个元素
     *
     * @param from
     * @param <T>
     * @return
     */
    public static <T> T getSecond(@Nullable List<T> from) {
        return Objects.isNotEmpty(from) && from.size() >= 2 ? from.get(1) : null;
    }

    /**
     * 获取 Map 第一个 Entry 的 Key
     *
     * @param from
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> K getFirstKey(@Nullable Map<K, V> from) {
        return Objects.isNotEmpty(from) ? getFirst(from.keySet()) : null;
    }

    /**
     * 获取 Map 第一个 Entry 的 Value
     *
     * @param from
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> V getFirstValue(@Nullable Map<K, V> from) {
        return Objects.isNotEmpty(from) ? from.get(getFirstKey(from)) : null;
    }

    /**
     * 根据集合元素中某个字段值升序排序后，取第一个元素作为返回结果，若集合 size 为 0，返回 null，参考 {@link #asc(Collection, Function)}
     *
     * @param from
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U extends Comparable<? super U>> T getAscFirst(@NonNull Collection<T> from, @NonNull Function<T, U> function) {
        return getFirst(asc(from, function));
    }

    /**
     * 根据集合元素中某个字段值降序排序后，取第一个元素作为返回结果，若集合 size 为 0，返回 null，参考 {@link #desc(Collection, Function)}
     *
     * @param from
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U extends Comparable<? super U>> T getDescFirst(@NonNull Collection<T> from, @NonNull Function<T, U> function) {
        return getFirst(desc(from, function));
    }

    /**
     * 获取最大长度字符串
     *
     * @param from
     * @return
     */
    public static String getMax(@NonNull Collection<String> from) {
        Objects.requireNotNull(from);
        return from.stream().filter(Objects::isNotNull).max(Comparator.comparing(String::length)).orElse(null);
    }

    /**
     * 获取最大比较规则元素
     * <pre>
     *    {@code
     *         Person person1 = new Person().setName("John").setAge(18);
     *         Person person2 = new Person().setName("John").setAge(19);
     *         Person person3 = new Person().setName("Mike").setAge(20);
     *         Person person4 = new Person().setName("Mike").setAge(21);
     *         List<`Person> people = toList(person1, person2, person3, person4);
     *         Person person = getMax(people, Comparator.comparing(Person::getAge));
     *    }
     *    上述代码结果为: person: {Person@701} "Person(name="Mike", age=21)
     * </pre>
     *
     * @param from
     * @param comparator
     * @param <T>
     * @return
     */
    public static <T> T getMax(@NonNull Collection<T> from, @NonNull Comparator<? super T> comparator) {
        Objects.requireNonNull(from, comparator);
        return from.stream().filter(Objects::isNotNull).max(comparator).orElse(null);
    }

    /**
     * 获取最小长度字符串
     *
     * @param from
     * @return
     */
    public static String getMin(@NonNull Collection<String> from) {
        Objects.requireNotNull(from);
        return from.stream().filter(Objects::isNotNull).min(Comparator.comparing(String::length)).orElse(null);
    }

    /**
     * 获取最小比较规则元素，参考 {@link #getMax(Collection, Comparator)}
     *
     * @param from
     * @param comparator
     * @param <T>
     * @return
     */
    public static <T> T getMin(@NonNull Collection<T> from, @NonNull Comparator<? super T> comparator) {
        Objects.requireNonNull(from, comparator);
        return from.stream().filter(Objects::isNotNull).min(comparator).orElse(null);
    }

    // ==================================== unmodifiable / immutable ====================================

    /**
     * 转换为不可变集合
     *
     * @param from
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> toImmutableMap(Map<? extends K, ? extends V> from) {
        return java.util.Collections.unmodifiableMap(from);
    }

    // ==================================== anything => List ====================================

    /**
     * String => List
     *
     * @param from 如："["1","2","3"]"
     * @return
     */
    @NonNull
    public static List<String> toList(@Nullable String from) {
        if (Objects.isBlank(from)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(StringUtils.strip(from, "[]").replaceAll(StringPool.SPACE, "").split(",")));
    }

    /**
     * Map value => List
     *
     * @param from
     * @param <K>
     * @param <V>
     * @return
     */
    @NonNull
    public static <K, V> List<V> toList(@Nullable Map<K, V> from) {
        if (Objects.isEmpty(from)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(from.values());
    }

    /**
     * 嵌套 List =>  List
     *
     * @param from
     * @param <T>
     * @return
     */
    @NonNull
    public static <T> List<T> toList(@Nullable List<List<T>> from) {
        if (Objects.isEmpty(from)) {
            return new ArrayList<>();
        }
        return from.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * 提取集合中元素的某个字段，并将该字段构造为新的 List
     *
     * @param from
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> List<U> toList(@NonNull Collection<T> from, @NonNull Function<T, U> function) {
        Objects.requireNonNull(from, function);
        return from.stream().map(function).collect(Collectors.toList());
    }

    /**
     * 提取集合中元素的某个字段，忽略为 null 的元素，并将该字段构造为新的 List
     *
     * @param from
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> List<U> toListIgnoreNull(@NonNull Collection<T> from, @NonNull Function<T, U> function) {
        Objects.requireNonNull(from, function);
        return from.stream().map(function).filter(Objects::isNotNull).collect(Collectors.toList());
    }

    // ==================================== anything => Set ====================================

    /**
     * 提取集合中元素的某个字段，并将该字段构造为新的 Set
     *
     * @param from
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> Set<U> toSet(@NonNull Collection<T> from, @NonNull Function<T, U> function) {
        Objects.requireNonNull(from, function);
        return from.stream().map(function).collect(Collectors.toSet());
    }

    /**
     * 提取集合中元素的某个字段，忽略为 null 的元素，并将该字段构造为新的 Set
     *
     * @param from
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> Set<U> toSetIgnoreNull(@NonNull Collection<T> from, @NonNull Function<T, U> function) {
        Objects.requireNonNull(from, function);
        return from.stream().map(function).filter(Objects::isNotNull).collect(Collectors.toSet());
    }

    // ==================================== anything => Map ====================================

    /**
     * Bean => Map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> toMap(T bean) {
        return BeanUtil.beanToMap(bean);
    }

    /**
     * 提取集合中元素某个字段的值作为 key，该字段作为 value 构造为新的 Map
     * <pre>
     *    {@code
     *      List<`Person> people = toList(new Person().setName("John").setAge(18));
     *      Map<`String, Person> map = toMap(people, Person::getName);
     *    }
     *    1、上述代码结果为: "John" -> {Person@700} "Person(name="John", age=18)"
     *    2、key 不允许重复，否则抛出异常 java.lang.IllegalStateException: Duplicate key
     * </pre>
     *
     * @param from
     * @param keyFunction
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> toMap(@NonNull Collection<V> from, @NonNull Function<V, K> keyFunction) {
        Objects.requireNonNull(from, keyFunction);
        return from.stream().collect(Collectors.toMap(keyFunction, item -> item));
    }

    /**
     * 提取集合中元素某个字段的值作为 key，另一个字段的值作为 value 构造为新的 Map
     * <pre>
     *    {@code
     *      List<`Person> people = toList(new Person().setName("John").setAge(18));
     *      Map<`String, Integer> map = toMap(people, Person::getName, Person::getAge);
     *    }
     *    1、上述代码结果为: "John" -> 18
     *    2、key 不允许重复，否则抛出异常 java.lang.IllegalStateException: Duplicate key
     * </pre>
     *
     * @param from
     * @param keyFunction
     * @param valueFunction
     * @param <T>
     * @param <K>
     * @param <V>
     * @return
     */
    public static <T, K, V> Map<K, V> toMap(@NonNull Collection<T> from, @NonNull Function<T, K> keyFunction, @NonNull Function<T, V> valueFunction) {
        Objects.requireNonNull(from, keyFunction, valueFunction);
        return from.stream().collect(Collectors.toMap(keyFunction, valueFunction));
    }

    /**
     * 提取集合中元素某个字段的值作为 key，该字段作为 value 构造为新的 Map
     * <pre>
     *    {@code
     *      Person person1 = new Person().setName("John").setAge(18);
     *      Person person2 = new Person().setName("John").setAge(19);
     *      List<`Person> people = toList(person1, person2);
     *      Map<`String, List<`Person>> map = toMultiMap(people, Person::getName);
     *    }
     *    1、上述代码结果为: "John" -> {ArrayList@777}  size = 2  [Person(name="John", age=18), Person(name="John", age=19)]
     *    2、重复的 key 对应的 value 合并为 List
     * </pre>
     *
     * @param from
     * @param keyFunction
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, List<V>> toMultiMap(@NonNull Collection<V> from, @NonNull Function<V, K> keyFunction) {
        Objects.requireNonNull(from, keyFunction);
        return from.stream().collect(Collectors.groupingBy(keyFunction, Collectors.mapping(item -> item, Collectors.toList())));
    }

    /**
     * 提取集合中元素某个字段的值作为 key，另一个字段的值作为 value 构造为新的 Map
     * <pre>
     *    {@code
     *      Person person1 = new Person().setName("John").setAge(18);
     *      Person person2 = new Person().setName("John").setAge(19);
     *      List<`Person> people = toList(person1, person2);
     *      Map<`String, List<`Person>> map = toMultiMap(people, Person::getName, Person::getAge);
     *    }
     *    1、上述代码结果为: "John" -> {ArrayList@777}  size = 2  [18, 19]
     *    2、重复的 key 对应的 value 合并为 List
     * </pre>
     *
     * @param from
     * @param keyFunction
     * @param valueFunction
     * @param <T>
     * @param <K>
     * @param <V>
     * @return
     */
    public static <T, K, V> Map<K, List<V>> toMultiMap(@NonNull Collection<T> from, @NonNull Function<T, K> keyFunction, @NonNull Function<T, V> valueFunction) {
        Objects.requireNonNull(from, keyFunction, valueFunction);
        return from.stream().collect(Collectors.groupingBy(keyFunction, Collectors.mapping(valueFunction, Collectors.toList())));
    }

    // ==================================== 集合过滤 ====================================

    /**
     * 过滤出集合中条件为 true 的元素并构造为新的 List
     * <pre>
     *    {@code
     *         List<`String> list = new ArrayList<>();
     *         list.add("123");
     *         list.add("123");
     *         list.add("113");
     *         List<`String> list2 = filters(list, item -> item.startsWith("12"));
     *    }
     *    上述代码结果为: list2: [123, 123]
     * </pre>
     *
     * @param from
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T extends E, E> List<T> filters(@NonNull Collection<T> from, @NonNull Predicate<E> predicate) {
        Objects.requireNonNull(from, predicate);
        return from.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * 过滤出数组中条件为 true 的元素并构造为新的 List，参考 {@link #filters(Collection, Predicate)}
     *
     * @param from
     * @param predicate
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T extends E, E> List<T> filters(@NonNull T[] from, @NonNull Predicate<E> predicate) {
        Objects.requireNonNull(from, predicate);
        return Arrays.stream(from).filter(predicate).collect(Collectors.toList());
    }

    /**
     * 过滤出 Map 中条件为 true 的元素并构造为新的 Map
     * <pre>
     *    {@code
     *         Map<`String, String> map1 = new HashMap<>();
     *         map1.put("123", "test");
     *         map1.put("121", "test");
     *         map1.put("113", "test");
     *         map1.put("111", "test");
     *         Map<`String, String> map2 = filters(map1, entry -> entry.getKey().startsWith("12"));
     *    }
     *    上述代码结果为: map2: {123="test", 121="test"}
     * </pre>
     *
     * @param from
     * @param predicate
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> filters(@NonNull Map<K, V> from, @NonNull Predicate<? super Map.Entry<K, V>> predicate) {
        Objects.requireNonNull(from, predicate);
        return from.entrySet().stream().filter(predicate).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * 过滤出 Map value 中条件为 true 的元素并构造为新的 List
     * <pre>
     *    {@code
     *         Map<`String, String> map = new HashMap<>();
     *         map.put("test1", "123");
     *         map.put("test2", "121");
     *         map.put("test3", "113");
     *         map.put("test4", "111");
     *         List<`String> list = capableFilters(map, str -> str.startsWith("12"));
     *    }
     *    上述代码结果为: list: [123,121]
     * </pre>
     *
     * @param from      源集合
     * @param predicate 过滤条件
     * @param <K>       key 元素类型
     * @param <V>       value 元素类型
     * @return 把条件为 true 的集合过滤并返回
     */
    public static <K, V> List<V> capableFilters(@NonNull Map<K, V> from, @NonNull Predicate<? super V> predicate) {
        Objects.requireNonNull(from, predicate);
        return filters(from.values(), predicate);
    }

    /**
     * 过滤出 Map 中条件为 true 的元素，并按要求提取元素中的 value 构造为新的 Map
     * <pre>
     *    {@code
     *         Person person1 = new Person().setName("John").setAge(18);
     *         Person person2 = new Person().setName("John").setAge(19);
     *         List<`Person> people = toList(person1, person2);
     *
     *         Map<`String, List<`Person>> map1 = new HashMap<>();
     *         map1.put("test1", people);
     *         map1.put("test2", people);
     *         map1.put("test3", people);
     *
     *         Map<`String, Person> map2 = capableFilters(map1, entry -> "test1".equals(entry.getKey()), entry -> entry.getValue().get(0));
     *    }
     *    上述代码结果为: map2: {"test1"={Person@700} "Person(name="John", age=18)"}
     * </pre>
     *
     * @param from
     * @param predicate
     * @param valueFunction
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> capableFilters(@NonNull Map<K, List<V>> from, @NonNull Predicate<? super Map.Entry<K, List<V>>> predicate, @NonNull Function<? super Map.Entry<K, List<V>>, V> valueFunction) {
        Objects.requireNonNull(from, predicate, valueFunction);
        return from.entrySet().stream().filter(predicate).collect(Collectors.toMap(Map.Entry::getKey, valueFunction));
    }

    /**
     * 过滤出集合中条件为 true 的元素后计算集合大小，参考 {@link #filters(Collection, Predicate)}
     *
     * @param from
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> long countFilters(@NonNull Collection<T> from, @NonNull Predicate<? super T> predicate) {
        Objects.requireNonNull(from, predicate);
        return from.stream().filter(predicate).count();
    }

    // ==================================== 集合去重 ====================================

    /**
     * 根据元素中某个字段去重并构造为新的 List
     * <pre>
     *    {@code
     *         Person person1 = new Person().setName("John").setAge(18);
     *         Person person2 = new Person().setName("John").setAge(19);
     *         List<`Person> people = toList(person1, person2);
     *         List<`Person> people2 = distinct(people, Person::getName);
     *    }
     *    上述代码结果为: people2: [{Person@701} "Person(name="John", age=18)]
     * </pre>
     *
     * @param from
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> List<T> distinct(@NonNull Collection<T> from, @NonNull Function<? super T, U> function) {
        Objects.requireNonNull(from, function);
        return from.stream().filter(distinctSupporter(function)).collect(Collectors.toList());
    }

    /**
     * 根据元素中某个字段去重并构造为新的 List
     * <pre>
     *    {@code
     *         Person person1 = new Person().setName("John").setAge(18);
     *         Person person2 = new Person().setName("John").setAge(19);
     *         List<`Person> people = toList(person1, person2);
     *         List<`String> names = distinctDuplicatedKey(people, Person::getName);
     *    }
     *    上述代码结果为: names: ["John"]
     * </pre>
     *
     * @param from
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> List<U> distinctDuplicatedKey(@NonNull Collection<T> from, @NonNull Function<? super T, U> function) {
        Objects.requireNonNull(from, function);
        return from.stream().map(function).distinct().collect(Collectors.toList());
    }

    /**
     * 过滤出集合中条件为 true 的元素，再根据元素中某个字段去重后，构造为新的 List
     * <pre>
     *    {@code
     *         Person person1 = new Person().setName("John").setAge(18);
     *         Person person2 = new Person().setName("John").setAge(19);
     *         Person person3 = new Person().setName("Mike").setAge(20);
     *         Person person4 = new Person().setName("Mike").setAge(21);
     *         List<`Person> people = toList(person1, person2, person3, person4);
     *         List<`Person> people2 = capableDistinct(people, person -> person.getAge() < 21, Person::getName);
     *    }
     *    上述代码结果为: people2: [{Person@701} "Person(name="John", age=18), {Person@702} "Person(name="Mike", age=20)]
     * </pre>
     *
     * @param from
     * @param predicate
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> List<T> capableDistinct(@NonNull Collection<T> from, @NonNull Predicate<? super T> predicate, @NonNull Function<? super T, U> function) {
        Objects.requireNonNull(from, predicate, function);
        return from.stream().filter(predicate).filter(distinctSupporter(function)).collect(Collectors.toList());
    }

    /**
     * 根据元素中某个字段去重后，计算集合大小，参考 {@link #distinct(Collection, Function)}
     *
     * @param from
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> long countDistinct(@NonNull Collection<T> from, @NonNull Function<? super T, U> function) {
        Objects.requireNonNull(from, function);
        return from.stream().filter(distinctSupporter(function)).count();
    }

    /**
     * 过滤出集合中条件为 true 的元素，再根据元素中某个字段去重后，计算集合大小，参考 {@link #capableDistinct(Collection, Predicate, Function)}
     *
     * @param from
     * @param predicate
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> long countDistinct(@NonNull Collection<T> from, @NonNull Predicate<? super T> predicate, @NonNull Function<? super T, U> function) {
        Objects.requireNonNull(from, predicate, function);
        return from.stream().filter(predicate).filter(distinctSupporter(function)).count();
    }

    /**
     * 符合去重条件时返回 true，用于对实体类中的某个字段值进行去重
     *
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    private static <T, U> Predicate<T> distinctSupporter(@NonNull Function<? super T, U> function) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> {
            U r = function.apply(t);
            if (Objects.isNull(r)) {
                return false;
            }
            return Objects.isNull(seen.putIfAbsent(r, Boolean.TRUE));
        };
    }

    // ==================================== 集合排序 ====================================

    /**
     * 根据集合元素中某个字段值升序排除后构造为新的 List
     * <pre>
     *    {@code
     *         Person person1 = new Person().setName("John").setAge(18);
     *         Person person2 = new Person().setName("John").setAge(19);
     *         Person person3 = new Person().setName("Mike").setAge(20);
     *         Person person4 = new Person().setName("Mike").setAge(21);
     *         List<`Person> people = toList(person1, person2, person3, person4);
     *         List<`Person> people2 = asc(people, Person::getAge);
     *    }
     *    上述代码结果为: people2: [
     *                              {Person@701} "Person(name="John", age=18),
     *                              {Person@702} "Person(name="John", age=19),
     *                              {Person@703} "Person(name="Mike", age=20),
     *                              {Person@704} "Person(name="Mike", age=21)
     *                           ]
     * </pre>
     *
     * @param from
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U extends Comparable<? super U>> List<T> asc(@NonNull Collection<T> from, @NonNull Function<T, U> function) {
        Objects.requireNonNull(from, function);
        return from.stream().sorted(Comparator.comparing(function)).collect(Collectors.toList());
    }

    /**
     * 根据集合元素中某个字段值降序排序后构造为新的 List
     * <pre>
     *    {@code
     *         Person person1 = new Person().setName("John").setAge(18);
     *         Person person2 = new Person().setName("John").setAge(19);
     *         Person person3 = new Person().setName("Mike").setAge(20);
     *         Person person4 = new Person().setName("Mike").setAge(21);
     *         List<`Person> people = toList(person1, person2, person3, person4);
     *         List<`Person> people2 = desc(people, Person::getAge);
     *    }
     *    上述代码结果为: people2: [
     *                              {Person@701} "Person(name="Mike", age=21),
     *                              {Person@702} "Person(name="Mike", age=20),
     *                              {Person@703} "Person(name="John", age=19),
     *                              {Person@704} "Person(name="John", age=18)
     *                           ]
     * </pre>
     *
     * @param from
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U extends Comparable<? super U>> List<T> desc(@NonNull Collection<T> from, @NonNull Function<T, U> function) {
        Objects.requireNonNull(from, function);
        return from.stream().sorted(Comparator.comparing(function).reversed()).collect(Collectors.toList());
    }

    // ==================================== 集合计算 ====================================

    /**
     * 求和
     * <pre>
     *    {@code
     *         Person person1 = new Person().setName("John").setAge(18);
     *         Person person2 = new Person().setName("John").setAge(19);
     *         Person person3 = new Person().setName("Mike").setAge(20);
     *         Person person4 = new Person().setName("Mike").setAge(21);
     *         List<`Person> people = toList(person1, person2, person3, person4);
     *         int age = sum(people, Person::getAge);
     *    }
     *    上述代码结果为: age: 78
     * </pre>
     *
     * @param from     源集合
     * @param function 要计算的集合元素，不允许 null
     * @param <T>      源集合元素类型
     * @return 计算后的值
     */
    public static <T> int sum(@NonNull Collection<T> from, @NonNull ToIntFunction<? super T> function) {
        Objects.requireNonNull(from, function);
        return from.stream().mapToInt(function).sum();
    }

    /**
     * 过滤出集合中条件为 true 的元素后进行求和，参考 {@link #filters(Collection, Predicate)}、{@link #sum(Collection, ToIntFunction)}
     * <pre>
     *     Stream 中不允许出现 null 元素，如果可能有 null 元素，可先进行过滤，如：sum(people, Objects::isNotNull, Person::getAge);
     * </pre>
     *
     * @param from
     * @param predicate
     * @param function
     * @param <T>
     * @return
     */
    public static <T> int sum(@NonNull Collection<T> from, @NonNull Predicate<? super T> predicate, @NonNull ToIntFunction<? super T> function) {
        Objects.requireNonNull(from, predicate, function);
        return from.stream().filter(predicate).mapToInt(function).sum();
    }

    // ============================ 集合分组 ============================

    /**
     * 根据集合元素的某个字段值进行分组，并构造为新的 Map
     * <pre>
     *    {@code
     *         Person person1 = new Person().setName("John").setAge(18);
     *         Person person2 = new Person().setName("John").setAge(19);
     *         Person person3 = new Person().setName("Mike").setAge(20);
     *         Person person4 = new Person().setName("Mike").setAge(21);
     *         Person person5 = new Person().setName("Carl").setAge(22);
     *         Person person6 = new Person().setName("Carl").setAge(23);
     *         List<`Person> people = toList(person1, person2, person3, person4, person5, person6);
     *         Map<`String, List<`Person>> map = groupBy(people, Person::getName);
     *    }
     *    上述代码结果为: map: {
     *                          "John"=[{Person@700} "Person(name="John", age=18), {Person@701} "Person(name="John", age=19)]
     *                          "Mike"=[{Person@702} "Person(name="Mike", age=20), {Person@703} "Person(name="Mike", age=21)]
     *                          "Carl"=[{Person@704} "Person(name="Carl", age=22), {Person@705} "Person(name="Carl", age=23)]
     *                       }
     * </pre>
     *
     * @param from
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> Map<U, List<T>> groupBy(@NonNull Collection<T> from, @NonNull Function<T, U> function) {
        Objects.requireNonNull(from, function);
        return from.stream().collect(Collectors.groupingBy(function));
    }

    /**
     * 过滤出集合中条件为 true 的元素后，根据集合元素的某个字段值进行分组，并构造为新的 Map，参考 {@link #filters(Collection, Predicate)}、{@link #groupBy(Collection, Function)}
     * <pre>
     *     Stream 中不允许出现 null 元素，如果可能有 null 元素，可先进行过滤，如：groupBy(people, Objects::isNotNull, Person::getName);
     * </pre>
     *
     * @param from
     * @param predicate
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> Map<U, List<T>> groupBy(@NonNull Collection<T> from, @NonNull Predicate<? super T> predicate, @NonNull Function<T, U> function) {
        Objects.requireNonNull(from, predicate, function);
        return from.stream().filter(predicate).collect(Collectors.groupingBy(function));
    }

    // ============================ 其他操作 ============================

    /**
     * 将元素加入集合，并返回加入元素后的集合，若入参集合为空，则创建一个新集合并返回
     *
     * @param from
     * @param object
     * @param <T>
     * @return
     */
    public static <T> List<T> add(@Nullable List<T> from, @Nullable T object) {
        if (Objects.isNull(from)) {
            return new ArrayList<>();
        }
        from.add(object);
        return from;
    }

}
