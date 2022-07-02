package cn.sugar.convert.jackson.core;

import cn.sugar.tools.core.Objects;
import cn.sugar.tools.core.asserts.Assert;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.SneakyThrows;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Jackson 转换器
 *
 * @author wjm
 * @date 2021/5/1 14:13
 */
public class JacksonConverts {

    private JacksonConverts() {
    }

    private static final JacksonConverts INSTANCE = new JacksonConverts();

    public static JacksonConverts getInstance() {
        return INSTANCE;
    }

    private static final boolean DEFAULT_VALIDATE_ENABLE = Boolean.FALSE;

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    private JacksonMapper defaultJacksonMapper;

    private JacksonConverts replaceGlobalJacksonMapper(JacksonMapper jacksonMapper) {
        this.defaultJacksonMapper = jacksonMapper;
        return this;
    }

    public JacksonMapper.Builder builder() {
        return new JacksonMapper.Builder(new JacksonMapper());
    }

    /**
     * <pre>
     * 设置静态块（最先加载）来初始化 {@link #defaultJacksonMapper}；
     * 若直接对 {@link #defaultJacksonMapper} 赋值，如：{@link #defaultJacksonMapper} = getInstance().builder().build()，
     * 此时单例正在加载的过程中又要获取单例，就会造成空指针问题；
     * </pre>
     */
    static {
        getInstance().replaceGlobalJacksonMapper(getInstance().builder().build());
    }

    public static class JacksonMapper extends ObjectMapper {

        private static final long serialVersionUID = -8474265145182256290L;

        public JacksonConverts getConverts() {
            return new JacksonConverts().replaceGlobalJacksonMapper(this);
        }

        public static class Builder extends MapperBuilder<JacksonMapper, Builder> {

            private static volatile Boolean allowToReplaceGlobalObjectMapper = Boolean.TRUE;

            private Builder(JacksonMapper mapper) {
                super(mapper);
            }

            public void buildGlobal() {
                buildGlobal(super.build());
            }

            public void buildGlobal(JacksonMapper jacksonMapper) {
                Assert.TRUE_NEED.throwsIfFalse(allowToReplaceGlobalObjectMapper);
                synchronized (Builder.class) {
                    Assert.TRUE_NEED.throwsIfFalse(allowToReplaceGlobalObjectMapper);
                    getInstance().replaceGlobalJacksonMapper(jacksonMapper);
                    allowToReplaceGlobalObjectMapper = Boolean.FALSE;
                }
            }

        }

    }

    @SneakyThrows
    public String toString(Object object) {
        return defaultJacksonMapper.writeValueAsString(object);
    }

    @SneakyThrows
    public JsonNode toJsonNode(String json) {
        return defaultJacksonMapper.readTree(json);
    }

    @SneakyThrows
    public void writeToFile(File file, Object object) {
        defaultJacksonMapper.writeValue(file, object);
    }

    @SneakyThrows
    public <T> T toBean(String json, Class<T> targetClass) {
        return toBean(json, targetClass, DEFAULT_VALIDATE_ENABLE);
    }

    @SneakyThrows
    public <T> T toBean(String json, Class<T> targetClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(json, targetClass),
                this::validate,
                enableValidate
        );
    }

    @SneakyThrows
    public <T> T toBean(File file, Class<T> targetClass) {
        return toBean(file, targetClass, DEFAULT_VALIDATE_ENABLE);
    }

    @SneakyThrows
    public <T> T toBean(File file, Class<T> targetClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(file, targetClass),
                this::validate,
                enableValidate
        );
    }

    @SneakyThrows
    public <T> T toBean(URL url, Class<T> targetClass) {
        return toBean(url, targetClass, DEFAULT_VALIDATE_ENABLE);
    }

    @SneakyThrows
    public <T> T toBean(URL url, Class<T> targetClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(url, targetClass),
                this::validate,
                enableValidate
        );
    }

    @SneakyThrows
    public <T> List<T> toBeans(String jsonArray, Class<T> targetClass) {
        return toBeans(jsonArray, targetClass, DEFAULT_VALIDATE_ENABLE);
    }

    @SneakyThrows
    public <T> List<T> toBeans(String jsonArray, Class<T> targetClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(jsonArray, TypeFactory.defaultInstance().constructCollectionType(List.class, targetClass)),
                targets -> targets.forEach(this::validate),
                enableValidate
        );
    }

    @SneakyThrows
    public <T> List<T> toBeans(File file, Class<T> targetClass) {
        return toBeans(file, targetClass, DEFAULT_VALIDATE_ENABLE);
    }

    @SneakyThrows
    public <T> List<T> toBeans(File file, Class<T> targetClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(file, TypeFactory.defaultInstance().constructCollectionType(List.class, targetClass)),
                targets -> targets.forEach(this::validate),
                enableValidate
        );
    }

    @SneakyThrows
    public <T> List<T> toBeans(URL url, Class<T> targetClass) {
        return toBeans(url, targetClass, DEFAULT_VALIDATE_ENABLE);
    }

    @SneakyThrows
    public <T> List<T> toBeans(URL url, Class<T> targetClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(url, TypeFactory.defaultInstance().constructCollectionType(List.class, targetClass)),
                targets -> targets.forEach(this::validate),
                enableValidate
        );
    }

    @SneakyThrows
    public <V> Map<String, V> toMap(String json, Class<V> valueClass) {
        return toMap(json, valueClass, DEFAULT_VALIDATE_ENABLE);
    }

    @SneakyThrows
    public <V> Map<String, V> toMap(String json, Class<V> valueClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(json, TypeFactory.defaultInstance().constructMapType(Map.class, String.class, valueClass)),
                targetMap -> targetMap.values().forEach(this::validate),
                enableValidate
        );
    }

    @SneakyThrows
    public <V> Map<String, V> toMap(File file, Class<V> valueClass) {
        return toMap(file, valueClass, DEFAULT_VALIDATE_ENABLE);
    }

    @SneakyThrows
    public <V> Map<String, V> toMap(File file, Class<V> valueClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(file, TypeFactory.defaultInstance().constructMapType(Map.class, String.class, valueClass)),
                targetMap -> targetMap.values().forEach(this::validate),
                enableValidate
        );
    }

    @SneakyThrows
    public <V> Map<String, V> toMap(URL url, Class<V> valueClass) {
        return toMap(url, valueClass, DEFAULT_VALIDATE_ENABLE);
    }

    @SneakyThrows
    public <V> Map<String, V> toMap(URL url, Class<V> valueClass, boolean enableValidate) {
        return validateIfNeed(
                defaultJacksonMapper.readValue(url, TypeFactory.defaultInstance().constructMapType(Map.class, String.class, valueClass)),
                targetMap -> targetMap.values().forEach(this::validate),
                enableValidate
        );
    }

    @SneakyThrows
    public <V> Map<String, List<V>> toMaps(String json, Class<V> valueClass) {
        return toMaps(json, valueClass, DEFAULT_VALIDATE_ENABLE);
    }

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

    @SneakyThrows
    public <V> Map<String, List<V>> toMaps(File file, Class<V> valueClass) {
        return toMaps(file, valueClass, DEFAULT_VALIDATE_ENABLE);
    }

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

    @SneakyThrows
    public <V> Map<String, List<V>> toMaps(URL url, Class<V> valueClass) {
        return toMaps(url, valueClass, DEFAULT_VALIDATE_ENABLE);
    }

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

    @SneakyThrows
    public <T> T toAnything(String json, TypeReference<T> targetTypeReference) {
        return defaultJacksonMapper.readValue(json, targetTypeReference);
    }

    @SneakyThrows
    public <T> T toAnything(File file, TypeReference<T> targetTypeReference) {
        return defaultJacksonMapper.readValue(file, targetTypeReference);
    }

    @SneakyThrows
    public <T> T toAnything(URL url, TypeReference<T> targetTypeReference) {
        return defaultJacksonMapper.readValue(url, targetTypeReference);
    }

    private <T> void validate(T object) {
        Set<ConstraintViolation<T>> constraintViolations = VALIDATOR.validate(object);
        if (Objects.isNotEmpty(constraintViolations)) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    private <T> T validateIfNeed(T target, Consumer<T> consumer, boolean enableValidate) {
        if (enableValidate) {
            consumer.accept(target);
        }
        return target;
    }

}

