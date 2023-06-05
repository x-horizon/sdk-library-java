package cn.srd.itcp.sugar.component.convert.jackson.core;

import cn.srd.itcp.sugar.tool.core.Objects;
import cn.srd.itcp.sugar.tool.core.asserts.Assert;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.fasterxml.jackson.databind.type.TypeFactory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.SneakyThrows;

import java.io.File;
import java.io.Serial;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Jackson 转换器
 * <pre>
 * 关于 Validator：
 * &#064;Null:                      只能为 null；
 * &#064;NotNull:                   必须不为 null；
 * &#064;NotEmpty:                  必须不为 null 且不为长度不为 0，只能标记在 CharSequence、Collection、Map、Array 上；
 * &#064;NotBlank:                  必须不为 null 且去除空格后长度不为 0，只能标记在 CharSequence 上；
 * &#064;AssertFalse:               必须为 false，只能标记在 boolean 及其包装类上；
 * &#064;AssertTrue:                必须为 true，只能标记在 boolean 及其包装类上；
 * &#064;Positive:                  必须为正数，只能标记在 BigInteger、BigDecimal、byte、short、int、long、float、double 及其包装类上；
 * &#064;Negative:                  必须为负数，只能标记在 BigInteger、BigDecimal、byte、short、int、long、float、double 及其包装类上；
 * &#064;PositiveOrZero:            必须为 0 或正数，只能标记在 BigInteger、BigDecimal、byte、short、int、long、float、double 及其包装类上；
 * &#064;NegativeOrZero:            必须为 0 或负数，只能标记在 BigInteger、BigDecimal、byte、short、int、long、float、double 及其包装类上；
 * &#064;Max(value):                必须为一个不大于指定值的数字，只能标记在 BigInteger、BigDecimal、byte、short、int、long 及其包装类上；
 * &#064;Min(value):                必须为一个不小于指定值的数字，只能标记在 BigInteger、BigDecimal、byte、short、int、long 及其包装类上；
 * &#064;DecimalMax(value):         必须为一个不大于指定值的数字；
 * &#064;DecimalMin(value):         必须为一个不小于指定值的数字；
 * &#064;Size(min, max):            长度必须在指定范围内，只能标记在 CharSequence、Collection、Map、Array 上；
 * &#064;Range(min, max):           必须在指定范围内；
 * &#064;Digits(integer, fraction): 必须为一个小数，且整数部分的位数不能超过 integer，小数部分的位数不能超过 fraction，只能标记在 BigInteger、BigDecimal、CharSequence、byte、short、int、long 及其包装类上；
 * &#064;Past:                      与当前时间比较必须为一个过去的时间，只能标记在 Date、JSR310 规范的 Instant、LocalDateTime、LocalDate、LocalTime、ZonedDateTime 上；
 * &#064;Future:                    与当前时间比较必须为一个未来的时间，只能标记在 Date、JSR310 规范的 Instant、LocalDateTime、LocalDate、LocalTime、ZonedDateTime 上；
 * &#064;PastOrPresent:             与当前时间比较必须为当前时间或一个过去的时间，只能标记在 Date、JSR310 规范的 Instant、LocalDateTime、LocalDate、LocalTime、ZonedDateTime 上；
 * &#064;FutureOrPresent:           与当前时间比较必须为当前时间或一个未来的时间，只能标记在 Date、JSR310 规范的 Instant、LocalDateTime、LocalDate、LocalTime、ZonedDateTime 上；
 * &#064;Pattern(regexp):           必须满足指定的正则规则，只能标记在 CharSequence 上；
 * &#064;Email:                     必须符合邮箱命名格式，只能标记在 CharSequence 上；
 * &#064;Email(regexp):             必须符合指定正则规则的邮箱命名格式，只能标记在 CharSequence 上；
 * &#064;Valid:                     递归验证，用于数组和集合，可以对其元素进行递归验证；
 * &#064;URL(xxx):                  必须为合法 URL，只能标记在 CharSequence 上；
 * </pre>
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
public class JacksonConverts {

    /**
     * private block constructor
     */
    private JacksonConverts() {
    }

    /**
     * singleton pattern
     */
    private static final JacksonConverts INSTANCE = new JacksonConverts();

    /**
     * get singleton instance
     *
     * @return 实例
     */
    public static JacksonConverts getInstance() {
        return INSTANCE;
    }

    /**
     * 是否开启 Jackson 相关注解校验默认值
     */
    private static final boolean DEFAULT_VALIDATE_ENABLE = false;

    /**
     * 校验器设置
     */
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 默认 Jackson 转换器
     */
    private JacksonMapper defaultJacksonMapper;

    /**
     * 替换 Jackson 全局转换器
     *
     * @param jacksonMapper Jackson 转换器
     * @return Jackson 转换工具
     */
    private JacksonConverts replaceGlobalJacksonMapper(JacksonMapper jacksonMapper) {
        this.defaultJacksonMapper = jacksonMapper;
        return this;
    }

    /**
     * 构建 Jackson 转换器
     *
     * @return Jackson 转换器
     */
    public JacksonMapper.Builder builder() {
        return new JacksonMapper.Builder(new JacksonMapper());
    }

    static {
        /**
         * <pre>
         * 设置静态块（最先加载）来初始化 {@link #defaultJacksonMapper}；
         * 若直接对 {@link #defaultJacksonMapper} 赋值，如：{@link #defaultJacksonMapper} = getInstance().builder().build()，
         * 此时单例正在加载的过程中又要获取单例，就会造成空指针问题；
         * </pre>
         */
        getInstance().replaceGlobalJacksonMapper(getInstance().builder().build());
    }

    /**
     * implement by {@link ObjectMapper}
     */
    public static final class JacksonMapper extends ObjectMapper {

        @Serial
        private static final long serialVersionUID = -8474265145182256290L;

        /**
         * 获取 Jackson 转换器
         *
         * @return Jackson 转换器
         */
        public JacksonConverts getConverts() {
            return new JacksonConverts().replaceGlobalJacksonMapper(this);
        }

        /**
         * implement by {@link MapperBuilder}
         */
        public static final class Builder extends MapperBuilder<JacksonMapper, Builder> {

            /**
             * 是否允许替换全局 Jackson 转换器
             */
            private static volatile boolean allowToReplaceGlobalObjectMapper = true;

            /**
             * private block constructor
             *
             * @param jacksonMapper see {@link JacksonMapper}
             */
            private Builder(JacksonMapper jacksonMapper) {
                super(jacksonMapper);
            }

            /**
             * 全局 Jackson 转换器构造器
             */
            public void buildGlobal() {
                buildGlobal(super.build());
            }

            /**
             * 构造全局 Jackson 转换器
             *
             * @param jacksonMapper see {@link JacksonMapper}
             */
            public void buildGlobal(JacksonMapper jacksonMapper) {
                Assert.TRUE_NEED.throwsIfFalse(allowToReplaceGlobalObjectMapper);
                synchronized (Builder.class) {
                    Assert.TRUE_NEED.throwsIfFalse(allowToReplaceGlobalObjectMapper);
                    getInstance().replaceGlobalJacksonMapper(jacksonMapper);
                    allowToReplaceGlobalObjectMapper = false;
                }
            }

        }

    }

    /**
     * 将对象写入文件
     *
     * @param file   待写入文件
     * @param object 待写入对象
     */
    @SneakyThrows
    public void writeToFile(File file, Object object) {
        defaultJacksonMapper.writeValue(file, object);
    }

    /**
     * 转换为 byte[]
     *
     * @param object 待转换对象
     * @return 转换后对象
     */
    @SneakyThrows
    public byte[] toBytes(Object object) {
        return defaultJacksonMapper.writeValueAsBytes(object);
    }

    /**
     * 转换为 {@link String}
     *
     * @param object 待转换对象
     * @return 转换后对象
     */
    @SneakyThrows
    public String toString(Object object) {
        return defaultJacksonMapper.writeValueAsString(object);
    }

    /**
     * 转换为 {@link JsonNode}
     *
     * @param json 待转换对象
     * @return 转换后对象
     */
    @SneakyThrows
    public JsonNode toJsonNode(String json) {
        return defaultJacksonMapper.readTree(json);
    }

    /**
     * 转换为实体对象
     *
     * @param json        待转换对象
     * @param targetClass 目标转换对象类
     * @param <T>         待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> T toBean(byte[] json, Class<T> targetClass) {
        return toBean(json, targetClass, DEFAULT_VALIDATE_ENABLE);
    }

    /**
     * 转换为实体对象
     *
     * @param json           待转换对象
     * @param targetClass    目标转换对象类
     * @param enableValidate 是否开启 Jackson 相关注解校验
     * @param <T>            待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> T toBean(byte[] json, Class<T> targetClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(json, targetClass),
                this::validate,
                enableValidate
        );
    }

    /**
     * 转换为实体对象
     *
     * @param json        待转换对象
     * @param targetClass 目标转换对象类
     * @param <T>         待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> T toBean(String json, Class<T> targetClass) {
        return toBean(json, targetClass, DEFAULT_VALIDATE_ENABLE);
    }

    /**
     * 转换为实体对象
     *
     * @param json           待转换对象
     * @param targetClass    目标转换对象类
     * @param enableValidate 是否开启 Jackson 相关注解校验
     * @param <T>            待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> T toBean(String json, Class<T> targetClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(json, targetClass),
                this::validate,
                enableValidate
        );
    }

    /**
     * 转换为实体对象
     *
     * @param input          待转换对象
     * @param targetClass    目标转换对象类
     * @param enableValidate 是否开启 Jackson 相关注解校验
     * @param <T>            待转换对象类型
     * @param <K>            Map Key
     * @param <V>            Map Value
     * @return 转换后对象
     */
    public <T, K, V> T toBean(Map<K, V> input, Class<T> targetClass, boolean enableValidate) {
        return toBean(toString(input), targetClass, enableValidate);
    }

    /**
     * 转换为实体对象
     *
     * @param file        待转换对象
     * @param targetClass 目标转换对象类
     * @param <T>         待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> T toBean(File file, Class<T> targetClass) {
        return toBean(file, targetClass, DEFAULT_VALIDATE_ENABLE);
    }

    /**
     * 转换为实体对象
     *
     * @param file           待转换对象
     * @param targetClass    目标转换对象类
     * @param enableValidate 是否开启 Jackson 相关注解校验
     * @param <T>            待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> T toBean(File file, Class<T> targetClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(file, targetClass),
                this::validate,
                enableValidate
        );
    }

    /**
     * 转换为实体对象
     *
     * @param url         待转换对象
     * @param targetClass 目标转换对象类
     * @param <T>         待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> T toBean(URL url, Class<T> targetClass) {
        return toBean(url, targetClass, DEFAULT_VALIDATE_ENABLE);
    }

    /**
     * 转换为实体对象
     *
     * @param url            待转换对象
     * @param targetClass    目标转换对象类
     * @param enableValidate 是否开启 Jackson 相关注解校验
     * @param <T>            待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> T toBean(URL url, Class<T> targetClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(url, targetClass),
                this::validate,
                enableValidate
        );
    }

    /**
     * 转换为实体对象集合
     *
     * @param jsonArray   待转换对象
     * @param targetClass 目标转换对象类
     * @param <T>         待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> List<T> toBeans(byte[] jsonArray, Class<T> targetClass) {
        return toBeans(jsonArray, targetClass, DEFAULT_VALIDATE_ENABLE);
    }

    /**
     * 转换为实体对象集合
     *
     * @param jsonArray      待转换对象
     * @param targetClass    目标转换对象类
     * @param enableValidate 是否开启 Jackson 相关注解校验
     * @param <T>            待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> List<T> toBeans(byte[] jsonArray, Class<T> targetClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(jsonArray, TypeFactory.defaultInstance().constructCollectionType(List.class, targetClass)),
                targets -> targets.forEach(this::validate),
                enableValidate
        );
    }

    /**
     * 转换为实体对象集合
     *
     * @param jsonArray   待转换对象
     * @param targetClass 目标转换对象类
     * @param <T>         待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> List<T> toBeans(String jsonArray, Class<T> targetClass) {
        return toBeans(jsonArray, targetClass, DEFAULT_VALIDATE_ENABLE);
    }

    /**
     * 转换为实体对象集合
     *
     * @param jsonArray      待转换对象
     * @param targetClass    目标转换对象类
     * @param enableValidate 是否开启 Jackson 相关注解校验
     * @param <T>            待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> List<T> toBeans(String jsonArray, Class<T> targetClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(jsonArray, TypeFactory.defaultInstance().constructCollectionType(List.class, targetClass)),
                targets -> targets.forEach(this::validate),
                enableValidate
        );
    }

    /**
     * 转换为实体对象集合
     *
     * @param file        待转换对象
     * @param targetClass 目标转换对象类
     * @param <T>         待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> List<T> toBeans(File file, Class<T> targetClass) {
        return toBeans(file, targetClass, DEFAULT_VALIDATE_ENABLE);
    }

    /**
     * 转换为实体对象集合
     *
     * @param file           待转换对象
     * @param targetClass    目标转换对象类
     * @param enableValidate 是否开启 Jackson 相关注解校验
     * @param <T>            待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> List<T> toBeans(File file, Class<T> targetClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(file, TypeFactory.defaultInstance().constructCollectionType(List.class, targetClass)),
                targets -> targets.forEach(this::validate),
                enableValidate
        );
    }

    /**
     * 转换为实体对象集合
     *
     * @param url         待转换对象
     * @param targetClass 目标转换对象类
     * @param <T>         待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> List<T> toBeans(URL url, Class<T> targetClass) {
        return toBeans(url, targetClass, DEFAULT_VALIDATE_ENABLE);
    }

    /**
     * 转换为实体对象集合
     *
     * @param url            待转换对象
     * @param targetClass    目标转换对象类
     * @param enableValidate 是否开启 Jackson 相关注解校验
     * @param <T>            待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> List<T> toBeans(URL url, Class<T> targetClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(url, TypeFactory.defaultInstance().constructCollectionType(List.class, targetClass)),
                targets -> targets.forEach(this::validate),
                enableValidate
        );
    }

    /**
     * 转换为 Map
     *
     * @param json       待转换对象
     * @param valueClass 目标转换对象类
     * @param <V>        待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <V> Map<String, V> toMap(String json, Class<V> valueClass) {
        return toMap(json, valueClass, DEFAULT_VALIDATE_ENABLE);
    }

    /**
     * 转换为 Map
     *
     * @param json           待转换对象
     * @param valueClass     目标转换对象类
     * @param enableValidate 是否开启 Jackson 相关注解校验
     * @param <V>            待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <V> Map<String, V> toMap(String json, Class<V> valueClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(json, TypeFactory.defaultInstance().constructMapType(Map.class, String.class, valueClass)),
                targetMap -> targetMap.values().forEach(this::validate),
                enableValidate
        );
    }

    /**
     * 转换为 Map
     *
     * @param file       待转换对象
     * @param valueClass 目标转换对象类
     * @param <V>        待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <V> Map<String, V> toMap(File file, Class<V> valueClass) {
        return toMap(file, valueClass, DEFAULT_VALIDATE_ENABLE);
    }

    /**
     * 转换为 Map
     *
     * @param file           待转换对象
     * @param valueClass     目标转换对象类
     * @param enableValidate 是否开启 Jackson 相关注解校验
     * @param <V>            待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <V> Map<String, V> toMap(File file, Class<V> valueClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(file, TypeFactory.defaultInstance().constructMapType(Map.class, String.class, valueClass)),
                targetMap -> targetMap.values().forEach(this::validate),
                enableValidate
        );
    }

    /**
     * 转换为 Map
     *
     * @param url        待转换对象
     * @param valueClass 目标转换对象类
     * @param <V>        待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <V> Map<String, V> toMap(URL url, Class<V> valueClass) {
        return toMap(url, valueClass, DEFAULT_VALIDATE_ENABLE);
    }

    /**
     * 转换为 Map
     *
     * @param url            待转换对象
     * @param valueClass     目标转换对象类
     * @param enableValidate 是否开启 Jackson 相关注解校验
     * @param <V>            待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <V> Map<String, V> toMap(URL url, Class<V> valueClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(url, TypeFactory.defaultInstance().constructMapType(Map.class, String.class, valueClass)),
                targetMap -> targetMap.values().forEach(this::validate),
                enableValidate
        );
    }

    /**
     * 转换为 Map
     *
     * @param json       待转换对象
     * @param valueClass 目标转换对象类
     * @param <V>        待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <V> Map<String, List<V>> toMaps(String json, Class<V> valueClass) {
        return toMaps(json, valueClass, DEFAULT_VALIDATE_ENABLE);
    }

    /**
     * 转换为 Map
     *
     * @param json           待转换对象
     * @param valueClass     目标转换对象类
     * @param enableValidate 是否开启 Jackson 相关注解校验
     * @param <V>            待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <V> Map<String, List<V>> toMaps(String json, Class<V> valueClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(json, TypeFactory.defaultInstance().constructMapType(
                        Map.class,
                        TypeFactory.defaultInstance().constructType(String.class),
                        TypeFactory.defaultInstance().constructCollectionType(List.class, valueClass)
                )),
                targetMap -> targetMap.values().forEach(targets -> targets.forEach(this::validate)),
                enableValidate
        );
    }

    /**
     * 转换为 Map
     *
     * @param file       待转换对象
     * @param valueClass 目标转换对象类
     * @param <V>        待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <V> Map<String, List<V>> toMaps(File file, Class<V> valueClass) {
        return toMaps(file, valueClass, DEFAULT_VALIDATE_ENABLE);
    }

    /**
     * 转换为 Map
     *
     * @param file           待转换对象
     * @param valueClass     目标转换对象类
     * @param enableValidate 是否开启 Jackson 相关注解校验
     * @param <V>            待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <V> Map<String, List<V>> toMaps(File file, Class<V> valueClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(file, TypeFactory.defaultInstance().constructMapType(
                        Map.class,
                        TypeFactory.defaultInstance().constructType(String.class),
                        TypeFactory.defaultInstance().constructCollectionType(List.class, valueClass)
                )),
                targetMap -> targetMap.values().forEach(targets -> targets.forEach(this::validate)),
                enableValidate
        );
    }

    /**
     * 转换为 Map
     *
     * @param url        待转换对象
     * @param valueClass 目标转换对象类
     * @param <V>        待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <V> Map<String, List<V>> toMaps(URL url, Class<V> valueClass) {
        return toMaps(url, valueClass, DEFAULT_VALIDATE_ENABLE);
    }

    /**
     * 转换为 Map
     *
     * @param url            待转换对象
     * @param valueClass     目标转换对象类
     * @param enableValidate 是否开启 Jackson 相关注解校验
     * @param <V>            待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <V> Map<String, List<V>> toMaps(URL url, Class<V> valueClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(url, TypeFactory.defaultInstance().constructMapType(
                        Map.class,
                        TypeFactory.defaultInstance().constructType(String.class),
                        TypeFactory.defaultInstance().constructCollectionType(List.class, valueClass)
                )),
                targetMap -> targetMap.values().forEach(targets -> targets.forEach(this::validate)),
                enableValidate
        );
    }

    /**
     * 根据 目标转换对象类型引用 转换为 目标对象
     *
     * @param json                待转换对象
     * @param targetTypeReference 目标转换对象类型引用
     * @param <T>                 待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> T toAnything(String json, TypeReference<T> targetTypeReference) {
        return defaultJacksonMapper.readValue(json, targetTypeReference);
    }

    /**
     * 根据 目标转换对象类型引用 转换为 目标对象
     *
     * @param file                待转换对象
     * @param targetTypeReference 目标转换对象类型引用
     * @param <T>                 待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> T toAnything(File file, TypeReference<T> targetTypeReference) {
        return defaultJacksonMapper.readValue(file, targetTypeReference);
    }

    /**
     * 根据 目标转换对象类型引用 转换为 目标对象
     *
     * @param url                 待转换对象
     * @param targetTypeReference 目标转换对象类型引用
     * @param <T>                 待转换对象类型
     * @return 转换后对象
     */
    @SneakyThrows
    public <T> T toAnything(URL url, TypeReference<T> targetTypeReference) {
        return defaultJacksonMapper.readValue(url, targetTypeReference);
    }

    /**
     * Jackson 相关注解校验
     *
     * @param object 目标校验对象
     * @param <T>    目标校验对象类型
     */
    private <T> void validate(T object) {
        Set<ConstraintViolation<T>> constraintViolations = VALIDATOR.validate(object);
        if (Objects.isNotEmpty(constraintViolations)) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    /**
     * Jackson 相关注解校验
     *
     * @param target         目标校验对象
     * @param consumer       是否开启校验逻辑
     * @param enableValidate 是否开启开启校验
     * @param <T>            目标校验对象类型
     * @return 目标校验对象
     */
    private <T> T validateIfNeed(T target, Consumer<T> consumer, boolean enableValidate) {
        if (enableValidate) {
            consumer.accept(target);
        }
        return target;
    }

}

