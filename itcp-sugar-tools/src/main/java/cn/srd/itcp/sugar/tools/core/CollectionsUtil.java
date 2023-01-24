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
 * @since 2020/12/15 12:40
 */
public class CollectionsUtil extends CollUtil {

    /**
     * private block constructor
     */
    private CollectionsUtil() {
    }

    // ==================================== 获取集合元素 ====================================

    /**
     * 获取 List 第 2 个元素
     *
     * @param from 输入参数
     * @param <T>  元素类型
     * @return 输入参数的第 2 个元素
     */
    public static <T> T getSecond(@Nullable List<T> from) {
        return Objects.isNotEmpty(from) && from.size() >= 2 ? from.get(1) : null;
    }

    /**
     * 获取 List 第 3 个元素
     *
     * @param from 输入参数
     * @param <T>  元素类型
     * @return 输入参数的第 3 个元素
     */
    public static <T> T getThird(@Nullable List<T> from) {
        return Objects.isNotEmpty(from) && from.size() >= 3 ? from.get(2) : null;
    }

    /**
     * 获取 Map 第一个 Entry 的 Key（仅当 map 只有一个元素时有意义，多个元素每次获取第一个可能会不相同）
     *
     * @param from 输入参数
     * @param <K>  Key 类型
     * @param <V>  Value 类型
     * @return 第一个桶的 Key 值
     */
    public static <K, V> K getFirstKey(@Nullable Map<K, V> from) {
        return Objects.isNotEmpty(from) ? getFirst(from.keySet()) : null;
    }

    /**
     * 获取 Map 第一个 Entry 的 Value（仅当 map 只有一个元素时有意义，多个元素每次获取第一个可能会不相同）
     *
     * @param from 输入参数
     * @param <K>  Key 类型
     * @param <V>  Value 类型
     * @return 第一个桶的 Value 值
     */
    public static <K, V> V getFirstValue(@Nullable Map<K, V> from) {
        return Objects.isNotEmpty(from) ? from.get(getFirstKey(from)) : null;
    }

    /**
     * 根据集合元素中某个字段值升序排序后，取第一个元素作为返回结果，若集合 size 为 0，返回 null，参考 {@link #asc(Collection, Function)}
     *
     * @param from     输入参数
     * @param function 获取逻辑
     * @param <T>      元素类型
     * @param <U>      获取结果的类型
     * @return 根据获取逻辑获取到的第一个元素
     */
    public static <T, U extends Comparable<? super U>> T getAscFirst(@NonNull Collection<T> from, @NonNull Function<T, U> function) {
        return getFirst(asc(from, function));
    }

    /**
     * 根据集合元素中某个字段值降序排序后，取第一个元素作为返回结果，若集合 size 为 0，返回 null，参考 {@link #desc(Collection, Function)}
     *
     * @param from     输入参数
     * @param function 获取逻辑
     * @param <T>      元素类型
     * @param <U>      获取结果的类型
     * @return 根据获取逻辑获取到的第一个元素
     */
    public static <T, U extends Comparable<? super U>> T getDescFirst(@NonNull Collection<T> from, @NonNull Function<T, U> function) {
        return getFirst(desc(from, function));
    }

    /**
     * 获取最大长度字符串
     *
     * @param from 输入参数
     * @return 最大长度字符串
     */
    public static String getMax(@NonNull Collection<String> from) {
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
     *         List&lt;Person> people = toList(person1, person2, person3, person4);
     *         Person person = getMax(people, Comparator.comparing(Person::getAge));
     *    }
     *    上述代码结果为: person: {Person@701} "Person(name="Mike", age=21)
     * </pre>
     *
     * @param from       输入参数
     * @param comparator 比较逻辑
     * @param <T>        元素类型
     * @return 获取到的最大比较规则的元素
     */
    public static <T> T getMax(@NonNull Collection<T> from, @NonNull Comparator<? super T> comparator) {
        return from.stream().filter(Objects::isNotNull).max(comparator).orElse(null);
    }

    /**
     * 获取最小长度字符串
     *
     * @param from 输入参数
     * @return 最小长度的字符串
     */
    public static String getMin(@NonNull Collection<String> from) {
        return from.stream().filter(Objects::isNotNull).min(Comparator.comparing(String::length)).orElse(null);
    }

    /**
     * 获取最小比较规则元素，参考 {@link #getMax(Collection, Comparator)}
     *
     * @param from       输入参数
     * @param comparator 比较规则
     * @param <T>        元素类型
     * @return 根据比较规则获取到的最小比较规则元素
     */
    public static <T> T getMin(@NonNull Collection<T> from, @NonNull Comparator<? super T> comparator) {
        return from.stream().filter(Objects::isNotNull).min(comparator).orElse(null);
    }

    // ==================================== unmodifiable / immutable ====================================

    /**
     * 转换为不可变集合
     *
     * @param from 输入参数
     * @param <K>  Key 类型
     * @param <V>  Value 类型
     * @return 不可变集合
     */
    public static <K, V> Map<K, V> toImmutableMap(Map<? extends K, ? extends V> from) {
        return java.util.Collections.unmodifiableMap(from);
    }

    // ==================================== anything => List ====================================

    /**
     * String => List，根据 {@link StringPool#COMMA} 进行截取
     *
     * @param from 如："["1","2","3"]"
     * @return 输出集合
     */
    @NonNull
    public static List<String> toList(@Nullable String from) {
        return toList(from, StringPool.COMMA);
    }

    /**
     * String => List
     *
     * @param from        如："["1","2","3"]"
     * @param splitSymbol 截取符号，如：","
     * @return 转换结果
     */
    @NonNull
    public static List<String> toList(@Nullable String from, @NonNull String splitSymbol) {
        if (Objects.isBlank(from)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(StringUtils.strip(from, "[]").replaceAll(StringPool.SPACE, "").split(splitSymbol)));
    }

    /**
     * Map value => List
     *
     * @param from 输入参数
     * @param <K>  Key 类型
     * @param <V>  Value 类型
     * @return 提取 value 形成的集合
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
     * @param from 输入参数
     * @param <T>  元素类型
     * @return 嵌套集合转换后的单层集合
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
     * @param from     输入参数
     * @param function 提取逻辑
     * @param <T>      元素类型
     * @param <U>      需要提取的属性数据类型
     * @return 提取结果
     */
    public static <T, U> List<U> toList(@NonNull Collection<T> from, @NonNull Function<T, U> function) {
        return from.stream().map(function).collect(Collectors.toList());
    }

    /**
     * 提取集合中元素的某个字段，忽略为 null 的元素，并将该字段构造为新的 List
     *
     * @param from     输入参数
     * @param function 提取逻辑
     * @param <T>      元素类型
     * @param <U>      需要提取的属性数据类型
     * @return 提取结果
     */
    public static <T, U> List<U> toListIgnoreNull(@NonNull Collection<T> from, @NonNull Function<T, U> function) {
        return from.stream().map(function).filter(Objects::isNotNull).collect(Collectors.toList());
    }

    // ==================================== anything => Set ====================================

    /**
     * 嵌套 List =>  Set
     *
     * @param from 输入参数
     * @param <T>  元素类型
     * @return 嵌套集合转换后的单层集合
     */
    @NonNull
    public static <T> Set<T> toSet(@Nullable List<List<T>> from) {
        if (Objects.isEmpty(from)) {
            return new HashSet<>();
        }
        return from.stream().flatMap(Collection::stream).collect(Collectors.toSet());
    }

    /**
     * 提取集合中元素的某个字段，并将该字段构造为新的 Set
     *
     * @param from     输入参数
     * @param function 提取逻辑
     * @param <T>      元素类型
     * @param <U>      需要提取的属性数据类型
     * @return 提取结果
     */
    public static <T, U> Set<U> toSet(@NonNull Collection<T> from, @NonNull Function<T, U> function) {
        return from.stream().map(function).collect(Collectors.toSet());
    }

    /**
     * 提取集合中元素的某个字段，忽略为 null 的元素，并将该字段构造为新的 Set
     *
     * @param from     输入参数
     * @param function 提取逻辑
     * @param <T>      元素类型
     * @param <U>      需要提取的属性数据类型
     * @return 提取结果
     */
    public static <T, U> Set<U> toSetIgnoreNull(@NonNull Collection<T> from, @NonNull Function<T, U> function) {
        return from.stream().map(function).filter(Objects::isNotNull).collect(Collectors.toSet());
    }

    // ==================================== anything => Map ====================================

    /**
     * Bean => Map
     *
     * @param bean 输入参数
     * @param <T>  bean 类型
     * @return 转换结果
     */
    public static <T> Map<String, Object> toMap(T bean) {
        return BeanUtil.beanToMap(bean);
    }

    /**
     * 提取集合中元素某个字段的值作为 key，该字段作为 value 构造为新的 Map
     * <pre>
     *    {@code
     *      List&lt;Person> people = toList(new Person().setName("John").setAge(18));
     *      Map&lt;String, Person> map = toMap(people, Person::getName);
     *    }
     *    1、上述代码结果为: "John" -> {Person@700} "Person(name="John", age=18)"
     *    2、key 不允许重复，否则抛出异常 java.lang.IllegalStateException: Duplicate key
     * </pre>
     *
     * @param from        输入参数
     * @param keyFunction 提取逻辑
     * @param <K>         元素类型
     * @param <V>         提取结果的 Value 类型
     * @return 提取结果
     */
    public static <K, V> Map<K, V> toMap(@NonNull Collection<V> from, @NonNull Function<V, K> keyFunction) {
        return from.stream().collect(Collectors.toMap(keyFunction, item -> item));
    }

    /**
     * 提取集合中元素某个字段的值作为 key，另一个字段的值作为 value 构造为新的 Map
     * <pre>
     *    {@code
     *      List&lt;Person> people = toList(new Person().setName("John").setAge(18));
     *      Map&lt;String, Integer> map = toMap(people, Person::getName, Person::getAge);
     *    }
     *    1、上述代码结果为: "John" -> 18
     *    2、key 不允许重复，否则抛出异常 java.lang.IllegalStateException: Duplicate key
     * </pre>
     *
     * @param from          输入参数
     * @param keyFunction   提取 key 逻辑
     * @param valueFunction 提取 value 逻辑
     * @param <T>           元素类型
     * @param <K>           提取结果的 Key 类型
     * @param <V>           提取结果的 Value 类型
     * @return 提取结果
     */
    public static <T, K, V> Map<K, V> toMap(@NonNull Collection<T> from, @NonNull Function<T, K> keyFunction, @NonNull Function<T, V> valueFunction) {
        return from.stream().collect(Collectors.toMap(keyFunction, valueFunction));
    }

    /**
     * 提取集合中元素某个字段的值作为 key，该字段作为 value 构造为新的 Map
     * <pre>
     *    {@code
     *      Person person1 = new Person().setName("John").setAge(18);
     *      Person person2 = new Person().setName("John").setAge(19);
     *      List&lt;Person> people = toList(person1, person2);
     *      Map&lt;String, List&lt;Person>> map = toMultiMap(people, Person::getName);
     *    }
     *    1、上述代码结果为: "John" -> {ArrayList@777}  size = 2  [Person(name="John", age=18), Person(name="John", age=19)]
     *    2、重复的 key 对应的 value 合并为 List
     * </pre>
     *
     * @param from        输入参数
     * @param keyFunction 提取 key 的逻辑
     * @param <K>         元素类型
     * @param <V>         提取的 Value 类型
     * @return 提取结果
     */
    public static <K, V> Map<K, List<V>> toMultiMap(@NonNull Collection<V> from, @NonNull Function<V, K> keyFunction) {
        return from.stream().collect(Collectors.groupingBy(keyFunction, Collectors.mapping(item -> item, Collectors.toList())));
    }

    /**
     * 提取集合中元素某个字段的值作为 key，另一个字段的值作为 value 构造为新的 Map
     * <pre>
     *    {@code
     *      Person person1 = new Person().setName("John").setAge(18);
     *      Person person2 = new Person().setName("John").setAge(19);
     *      List&lt;Person> people = toList(person1, person2);
     *      Map&lt;String, List&lt;Person>> map = toMultiMap(people, Person::getName, Person::getAge);
     *    }
     *    1、上述代码结果为: "John" -> {ArrayList@777}  size = 2  [18, 19]
     *    2、重复的 key 对应的 value 合并为 List
     * </pre>
     *
     * @param from          输入参数
     * @param keyFunction   提取 key 的逻辑
     * @param valueFunction 提取 value 的逻辑
     * @param <T>           元素类型
     * @param <K>           提取的 Key 类型
     * @param <V>           提取的 Value 类型
     * @return 提取结果
     */
    public static <T, K, V> Map<K, List<V>> toMultiMap(@NonNull Collection<T> from, @NonNull Function<T, K> keyFunction, @NonNull Function<T, V> valueFunction) {
        return from.stream().collect(Collectors.groupingBy(keyFunction, Collectors.mapping(valueFunction, Collectors.toList())));
    }

    // ==================================== 集合过滤 ====================================

    /**
     * 过滤出集合中条件为 true 的元素并构造为新的 List
     * <pre>
     *    {@code
     *         List&lt;String> list = new ArrayList<>();
     *         list.add("123");
     *         list.add("123");
     *         list.add("113");
     *         List&lt;String> list2 = filters(list, item -> item.startsWith("12"));
     *    }
     *    上述代码结果为: list2: [123, 123]
     * </pre>
     *
     * @param from      输入参数
     * @param predicate 过滤条件
     * @param <T>       元素类型
     * @param <E>       过滤参数的类型
     * @return 过滤结果
     */
    public static <T extends E, E> List<T> filtersToList(@NonNull Collection<T> from, @NonNull Predicate<E> predicate) {
        return from.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * 过滤出集合中条件为 true 的元素并构造为新的 List，并取出第一个元素
     *
     * @param from      输入参数
     * @param predicate 过滤条件
     * @param <T>       元素类型
     * @param <E>       过滤的参数类型
     * @return 过滤结果
     */
    public static <T extends E, E> T filtersToOne(@NonNull Collection<T> from, @NonNull Predicate<E> predicate) {
        return CollectionsUtil.getFirst(filtersToList(from, predicate));
    }

    /**
     * 过滤出数组中条件为 true 的元素并构造为新的 List，参考 {@link #filtersToList(Collection, Predicate)}
     *
     * @param from      输入参数
     * @param predicate 过滤条件
     * @param <T>       元素类型
     * @param <E>       过滤后的元素类型
     * @return 过滤结果
     */
    public static <T extends E, E> List<T> filtersToList(@NonNull T[] from, @NonNull Predicate<E> predicate) {
        return Arrays.stream(from).filter(predicate).collect(Collectors.toList());
    }

    /**
     * 过滤出 Map 中条件为 true 的元素并构造为新的 Map
     * <pre>
     *    {@code
     *         Map&lt;String, String> map1 = new HashMap<>();
     *         map1.put("123", "test");
     *         map1.put("121", "test");
     *         map1.put("113", "test");
     *         map1.put("111", "test");
     *         Map&lt;String, String> map2 = filters(map1, entry -> entry.getKey().startsWith("12"));
     *    }
     *    上述代码结果为: map2: {123="test", 121="test"}
     * </pre>
     *
     * @param from      输入参数
     * @param predicate 过滤条件
     * @param <K>       过滤后的 Key 类型
     * @param <V>       过滤后的 Value 类型
     * @return 过滤结果
     */
    public static <K, V> Map<K, V> filtersToList(@NonNull Map<K, V> from, @NonNull Predicate<? super Map.Entry<K, V>> predicate) {
        return from.entrySet().stream().filter(predicate).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * 过滤出 Map value 中条件为 true 的元素并构造为新的 List
     * <pre>
     *    {@code
     *         Map&lt;String, String> map = new HashMap<>();
     *         map.put("test1", "123");
     *         map.put("test2", "121");
     *         map.put("test3", "113");
     *         map.put("test4", "111");
     *         List&lt;String> list = capableFilters(map, str -> str.startsWith("12"));
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
    public static <K, V> List<V> capableFiltersToList(@NonNull Map<K, V> from, @NonNull Predicate<? super V> predicate) {
        return filtersToList(from.values(), predicate);
    }

    /**
     * 过滤出 Map 中条件为 true 的元素，并按要求提取元素中的 value 构造为新的 Map
     * <pre>
     *    {@code
     *         Person person1 = new Person().setName("John").setAge(18);
     *         Person person2 = new Person().setName("John").setAge(19);
     *         List&lt;Person> people = toList(person1, person2);
     *
     *         Map&lt;String, List&lt;Person>> map1 = new HashMap<>();
     *         map1.put("test1", people);
     *         map1.put("test2", people);
     *         map1.put("test3", people);
     *
     *         Map&lt;String, Person> map2 = capableFilters(map1, entry -> "test1".equals(entry.getKey()), entry -> entry.getValue().get(0));
     *    }
     *    上述代码结果为: map2: {"test1"={Person@700} "Person(name="John", age=18)"}
     * </pre>
     *
     * @param from          输入参数
     * @param predicate     过滤条件
     * @param valueFunction 过滤 value 的逻辑
     * @param <K>           key 元素类型
     * @param <V>           value 元素类型
     * @return 过滤结果
     */
    public static <K, V> Map<K, V> capableFiltersToList(@NonNull Map<K, List<V>> from, @NonNull Predicate<? super Map.Entry<K, List<V>>> predicate, @NonNull Function<? super Map.Entry<K, List<V>>, V> valueFunction) {
        return from.entrySet().stream().filter(predicate).collect(Collectors.toMap(Map.Entry::getKey, valueFunction));
    }

    /**
     * 过滤出集合中条件为 true 的元素后计算集合大小，参考 {@link #filtersToList(Collection, Predicate)}
     *
     * @param from      输入参数
     * @param predicate 过滤条件
     * @param <T>       元素类型
     * @return 过滤结果
     */
    public static <T> long countAfterFilters(@NonNull Collection<T> from, @NonNull Predicate<? super T> predicate) {
        return from.stream().filter(predicate).count();
    }

    /**
     * 过滤出集合中条件为 true 的元素并构造为新的 Set
     * <pre>
     *    {@code
     *         List&lt;String> list = new ArrayList<>();
     *         list.add("123");
     *         list.add("123");
     *         list.add("113");
     *         Set&lt;String> set = filters(list, item -> item.startsWith("12"));
     *    }
     *    上述代码结果为: set: [123]
     * </pre>
     *
     * @param from      输入参数
     * @param predicate 过滤条件
     * @param <T>       元素类型
     * @param <E>       过滤的参数类型
     * @return 过滤结果
     */
    public static <T extends E, E> Set<T> filtersToSet(@NonNull Collection<T> from, @NonNull Predicate<E> predicate) {
        return from.stream().filter(predicate).collect(Collectors.toSet());
    }

    // ==================================== 集合去重 ====================================

    /**
     * 根据元素中某个字段去重并构造为新的 List
     * <pre>
     *    {@code
     *         Person person1 = new Person().setName("John").setAge(18);
     *         Person person2 = new Person().setName("John").setAge(19);
     *         List&lt;Person> people = toList(person1, person2);
     *         List&lt;Person> people2 = distinct(people, Person::getName);
     *    }
     *    上述代码结果为: people2: [{Person@701} "Person(name="John", age=18)]
     * </pre>
     *
     * @param from     输入参数
     * @param function 去重逻辑
     * @param <T>      元素类型
     * @param <U>      去重逻辑类型
     * @return 去重结果
     */
    public static <T, U> List<T> distinct(@NonNull Collection<T> from, @NonNull Function<? super T, U> function) {
        return from.stream().filter(distinctSupporter(function)).collect(Collectors.toList());
    }

    /**
     * 根据元素中某个字段去重并构造为新的 List
     * <pre>
     *    {@code
     *         Person person1 = new Person().setName("John").setAge(18);
     *         Person person2 = new Person().setName("John").setAge(19);
     *         List&lt;Person> people = toList(person1, person2);
     *         List&lt;String> names = distinctDuplicatedKey(people, Person::getName);
     *    }
     *    上述代码结果为: names: ["John"]
     * </pre>
     *
     * @param from     输入参数
     * @param function 去重逻辑
     * @param <T>      元素类型
     * @param <U>      去重逻辑类型
     * @return 去重结果
     */
    public static <T, U> List<U> distinctDuplicatedKey(@NonNull Collection<T> from, @NonNull Function<? super T, U> function) {
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
     *         List&lt;Person> people = toList(person1, person2, person3, person4);
     *         List&lt;Person> people2 = capableDistinct(people, person -> person.getAge() < 21, Person::getName);
     *    }
     *    上述代码结果为: people2: [{Person@701} "Person(name="John", age=18), {Person@702} "Person(name="Mike", age=20)]
     * </pre>
     *
     * @param from      输入参数
     * @param predicate 过滤逻辑
     * @param function  去重逻辑
     * @param <T>       元素类型
     * @param <U>       去重逻辑类型
     * @return 去重结果
     */
    public static <T, U> List<T> capableDistinct(@NonNull Collection<T> from, @NonNull Predicate<? super T> predicate, @NonNull Function<? super T, U> function) {
        return from.stream().filter(predicate).filter(distinctSupporter(function)).collect(Collectors.toList());
    }

    /**
     * 根据元素中某个字段去重后，计算集合大小，参考 {@link #distinct(Collection, Function)}
     *
     * @param from     输入参数
     * @param function 去重逻辑
     * @param <T>      元素类型
     * @param <U>      去重逻辑类型
     * @return 去重结果
     */
    public static <T, U> long countDistinct(@NonNull Collection<T> from, @NonNull Function<? super T, U> function) {
        return from.stream().filter(distinctSupporter(function)).count();
    }

    /**
     * 过滤出集合中条件为 true 的元素，再根据元素中某个字段去重后，计算集合大小，参考 {@link #capableDistinct(Collection, Predicate, Function)}
     *
     * @param from      输入参数
     * @param predicate 过滤逻辑
     * @param function  去重逻辑
     * @param <T>       元素类型
     * @param <U>       去重逻辑类型
     * @return 去重结果
     */
    public static <T, U> long countDistinct(@NonNull Collection<T> from, @NonNull Predicate<? super T> predicate, @NonNull Function<? super T, U> function) {
        return from.stream().filter(predicate).filter(distinctSupporter(function)).count();
    }

    /**
     * 符合去重条件时返回 true，用于对实体类中的某个字段值进行去重
     *
     * @param function 输入参数
     * @param <T>      元素类型
     * @param <U>      去重逻辑类型
     * @return 去重逻辑
     */
    private static <T, U> Predicate<T> distinctSupporter(@NonNull Function<? super T, U> function) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> {
            U r = function.apply(t);
            if (Objects.isNull(r)) {
                return false;
            }
            return Objects.isNull(seen.putIfAbsent(r, true));
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
     *         List&lt;Person> people = toList(person1, person2, person3, person4);
     *         List&lt;Person> people2 = asc(people, Person::getAge);
     *    }
     *    上述代码结果为: people2: [
     *                              {Person@701} "Person(name="John", age=18),
     *                              {Person@702} "Person(name="John", age=19),
     *                              {Person@703} "Person(name="Mike", age=20),
     *                              {Person@704} "Person(name="Mike", age=21)
     *                           ]
     * </pre>
     *
     * @param from     输入参数
     * @param function 提取排序字段逻辑
     * @param <T>      元素类型
     * @param <U>      提取排序字段类型
     * @return 排序结果
     */
    public static <T, U extends Comparable<? super U>> List<T> asc(@NonNull Collection<T> from, @NonNull Function<T, U> function) {
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
     *         List&lt;Person> people = toList(person1, person2, person3, person4);
     *         List&lt;Person> people2 = desc(people, Person::getAge);
     *    }
     *    上述代码结果为: people2: [
     *                              {Person@701} "Person(name="Mike", age=21),
     *                              {Person@702} "Person(name="Mike", age=20),
     *                              {Person@703} "Person(name="John", age=19),
     *                              {Person@704} "Person(name="John", age=18)
     *                           ]
     * </pre>
     *
     * @param from     输入参数
     * @param function 提取排序字段逻辑
     * @param <T>      元素类型
     * @param <U>      提取排序字段类型
     * @return 排序结果
     */
    public static <T, U extends Comparable<? super U>> List<T> desc(@NonNull Collection<T> from, @NonNull Function<T, U> function) {
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
     *         List&lt;Person> people = toList(person1, person2, person3, person4);
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
        return from.stream().mapToInt(function).sum();
    }

    /**
     * 过滤出集合中条件为 true 的元素后进行求和，参考 {@link #filtersToList(Collection, Predicate)}、{@link #sum(Collection, ToIntFunction)}
     * <pre>
     *     Stream 中不允许出现 null 元素，如果可能有 null 元素，可先进行过滤，如：sum(people, Objects::isNotNull, Person::getAge);
     * </pre>
     *
     * @param from      输入参数
     * @param predicate 过滤条件
     * @param function  提取的元素逻辑
     * @param <T>       元素类型
     * @return 过滤结果
     */
    public static <T> int sum(@NonNull Collection<T> from, @NonNull Predicate<? super T> predicate, @NonNull ToIntFunction<? super T> function) {
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
     *         List&lt;Person> people = toList(person1, person2, person3, person4, person5, person6);
     *         Map&lt;String, List&lt;Person>> map = groupBy(people, Person::getName);
     *    }
     *    上述代码结果为: map: {
     *                          "John"=[{Person@700} "Person(name="John", age=18), {Person@701} "Person(name="John", age=19)]
     *                          "Mike"=[{Person@702} "Person(name="Mike", age=20), {Person@703} "Person(name="Mike", age=21)]
     *                          "Carl"=[{Person@704} "Person(name="Carl", age=22), {Person@705} "Person(name="Carl", age=23)]
     *                       }
     * </pre>
     *
     * @param from     输入参数
     * @param function 分组逻辑
     * @param <T>      元素类型
     * @param <U>      分组后的元素类型
     * @return 分组结果
     */
    public static <T, U> Map<U, List<T>> groupBy(@NonNull Collection<T> from, @NonNull Function<T, U> function) {
        return from.stream().collect(Collectors.groupingBy(function));
    }

    /**
     * 过滤出集合中条件为 true 的元素后，根据集合元素的某个字段值进行分组，并构造为新的 Map，参考 {@link #filtersToList(Collection, Predicate)}、{@link #groupBy(Collection, Function)}
     * <pre>
     *     Stream 中不允许出现 null 元素，如果可能有 null 元素，可先进行过滤，如：groupBy(people, Objects::isNotNull, Person::getName);
     * </pre>
     *
     * @param from      输入参数
     * @param predicate 过滤逻辑
     * @param function  提取排序字段逻辑
     * @param <T>       元素类型
     * @param <U>       提取排序字段类型
     * @return 排序结果
     */
    public static <T, U> Map<U, List<T>> groupBy(@NonNull Collection<T> from, @NonNull Predicate<? super T> predicate, @NonNull Function<T, U> function) {
        return from.stream().filter(predicate).collect(Collectors.groupingBy(function));
    }

    // ============================ 其他操作 ============================

    /**
     * 将元素加入集合，并返回加入元素后的集合，若入参集合为空，则创建一个新集合并返回
     *
     * @param from   输入参数
     * @param object 待加入集合的元素
     * @param <T>    元素类型
     * @return 加入后的集合
     */
    public static <T> List<T> add(@Nullable List<T> from, @Nullable T object) {
        if (Objects.isNull(from)) {
            return new ArrayList<>();
        }
        from.add(object);
        return from;
    }

}
