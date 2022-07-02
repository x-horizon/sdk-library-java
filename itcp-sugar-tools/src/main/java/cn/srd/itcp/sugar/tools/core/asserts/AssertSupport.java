package cn.srd.itcp.sugar.tools.core.asserts;

import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.exceptions.ExceptionMessageTemplate;
import cn.srd.itcp.sugar.tools.exceptions.RunningException;
import cn.srd.itcp.sugar.tools.web.HttpStatus;
import cn.srd.itcp.sugar.tools.exceptions.ExceptionTemplate;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * assert support
 *
 * @author wjm
 * @date 2020/6/13 20:05
 */
public interface AssertSupport extends ExceptionTemplate {

    /**
     * 设置异常信息
     *
     * @param message 异常信息
     * @return this
     */
    default AssertSupport set(String message) {
        return set(null, message, null);
    }

    /**
     * 设置异常信息
     *
     * @param message 异常信息
     * @return this
     */
    default AssertSupport set(Supplier<String> message) {
        return set(message.get());
    }

    /**
     * 设置异常信息
     *
     * @param message 异常信息
     * @return this
     */
    default AssertSupport set(ExceptionMessageTemplate message) {
        return set(message.getExceptionMessage());
    }

    /**
     * 设置异常信息
     *
     * @param httpStatus 响应码 + 响应码对应的描述信息（作为异常信息）
     * @return this
     */
    default AssertSupport set(HttpStatus httpStatus) {
        return set(httpStatus.getCode(), httpStatus.getDescription(), null);
    }

    /**
     * 设置异常信息
     *
     * @param httpStatus 响应码
     * @param message    异常信息
     * @return this
     */
    default AssertSupport set(HttpStatus httpStatus, String message) {
        return set(httpStatus.getCode(), message, null);
    }

    /**
     * 设置异常信息
     *
     * @param httpStatus 响应码
     * @param message    异常信息
     * @return this
     */
    default AssertSupport set(HttpStatus httpStatus, Supplier<String> message) {
        return set(httpStatus.getCode(), message.get(), null);
    }

    /**
     * 设置异常信息
     *
     * @param code    响应码
     * @param message 异常信息
     * @return this
     */
    default AssertSupport set(Integer code, String message) {
        return set(code, message, null);
    }

    /**
     * 设置异常信息
     *
     * @param exception 异常
     * @return this
     */
    default AssertSupport set(Exception exception) {
        return set(null, null, exception);
    }

    /**
     * 设置异常信息
     *
     * @param httpStatus 响应码 + 响应码对应的描述信息（作为异常信息）
     * @param exception  异常
     * @return this
     */
    default AssertSupport set(HttpStatus httpStatus, Exception exception) {
        return set(httpStatus.getCode(), null, exception);
    }

    /**
     * 设置异常信息
     *
     * @param code      响应码
     * @param message   异常信息
     * @param exception 异常
     * @return this
     */
    // TODO 实现延迟加载这三个形参
    AssertSupport set(@Nullable Integer code, @Nullable String message, @Nullable Exception exception);

    //=====================断言功能

    default void throwsIfNull(Object object) {
        if (Objects.isNull(object)) {
            throwsNow();
        }
    }

    default void throwsIfNull(Object... object) {
        if (Objects.isNull(object)) {
            throwsNow();
        }
    }

    default void throwsIfAllNull(Object... object) {
        if (Objects.isAllNull(object)) {
            throwsNow();
        }
    }

    default void throwsIfNotNull(Object object) {
        if (Objects.isNotNull(object)) {
            throwsNow();
        }
    }

    default void throwsIfNotNull(Object... object) {
        if (Objects.isNotNull(object)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(CharSequence object) {
        if (Objects.isEmpty(object)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(CharSequence... objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfAllEmpty(CharSequence... objects) {
        if (Objects.isAllEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfBlank(CharSequence object) {
        if (Objects.isBlank(object)) {
            throwsNow();
        }
    }

    default void throwsIfBlank(CharSequence... objects) {
        if (Objects.isBlank(objects)) {
            throwsNow();
        }
    }

    default void throwsIfAllBlank(CharSequence... objects) {
        if (Objects.isAllBlank(objects)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(Map<?, ?> object) {
        if (Objects.isEmpty(object)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(Map<?, ?>... objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfAllEmpty(Map<?, ?>... objects) {
        if (Objects.isAllEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(Iterable<?> object) {
        if (Objects.isEmpty(object)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(Iterable<?>... objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfAllEmpty(Iterable<?>... objects) {
        if (Objects.isAllEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(Iterator<?> object) {
        if (Objects.isEmpty(object)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(Iterator<?>... objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfAllEmpty(Iterator<?>... objects) {
        if (Objects.isAllEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(byte[] objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(short[] objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(int[] objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(long[] objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(float[] objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(double[] objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(char[] objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(boolean[] objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(String[] objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(Object object) {
        if (Objects.isEmpty(object)) {
            throwsNow();
        }
    }

    default void throwsIfEmpty(Object... objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfAllEmpty(Object... objects) {
        if (Objects.isAllEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(CharSequence object) {
        if (Objects.isNotEmpty(object)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(CharSequence... objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfNotBlank(CharSequence object) {
        if (Objects.isNotBlank(object)) {
            throwsNow();
        }
    }

    default void throwsIfNotBlank(CharSequence... objects) {
        if (Objects.isNotBlank(objects)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(Map<?, ?> object) {
        if (Objects.isNotEmpty(object)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(Map<?, ?>... objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(Iterable<?> object) {
        if (Objects.isNotEmpty(object)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(Iterable<?>... objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(Iterator<?> object) {
        if (Objects.isNotEmpty(object)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(Iterator<?>... objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(byte[] objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(short[] objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(int[] objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(long[] objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(float[] objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(double[] objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(char[] objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(boolean[] objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(String[] objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(Object object) {
        if (Objects.isNotEmpty(object)) {
            throwsNow();
        }
    }

    default void throwsIfNotEmpty(Object... objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    default void throwsIfTrue(Boolean isTrue) {
        if (Objects.isTrue(isTrue)) {
            throwsNow();
        }
    }

    default void throwsIfTrue(Supplier<Boolean> supplier) {
        if (Objects.isTrue(supplier)) {
            throwsNow();
        }
    }

    default void throwsIfTrue(Boolean... areTrue) {
        if (Objects.isTrue(areTrue)) {
            throwsNow();
        }
    }

    default void throwsIfAllTrue(Boolean... areTrue) {
        if (Objects.isAllTrue(areTrue)) {
            throwsNow();
        }
    }

    default void throwsIfFalse(Boolean isFalse) {
        if (Objects.isFalse(isFalse)) {
            throwsNow();
        }
    }

    default void throwsIfFalse(Supplier<Boolean> supplier) {
        if (Objects.isFalse(supplier)) {
            throwsNow();
        }
    }

    default <T> void throwsIfFalse(T item, Predicate<T> predicate) {
        if (Objects.isFalse(item, predicate)) {
            throwsNow();
        }
    }

    default void throwsIfFalse(Boolean... areFalse) {
        if (Objects.isFalse(areFalse)) {
            throwsNow();
        }
    }

    default void throwsIfAllFalse(Boolean... areFalse) {
        if (Objects.isAllFalse(areFalse)) {
            throwsNow();
        }
    }

    default void throwsIfPositive(Number number) {
        if (Objects.isPositive(number)) {
            throwsNow();
        }
    }

    default void throwsIfAllPositive(Number... numbers) {
        if (Objects.isAllPositive(numbers)) {
            throwsNow();
        }
    }

    default void throwsIfNotPositive(Number number) {
        if (Objects.isNotPositive(number)) {
            throwsNow();
        }
    }

    default void throwsIfNotPositive(Number... numbers) {
        if (Objects.isNotPositive(numbers)) {
            throwsNow();
        }
    }

    default void throwsIfEquals(@Nullable Object obj1, @Nullable Object obj2) {
        if (Objects.equals(obj1, obj2)) {
            throwsNow();
        }
    }

    default void throwsIfEquals(@Nullable Object obj1, @Nullable Object... objs) {
        if (Objects.equals(obj1, objs)) {
            throwsNow();
        }
    }

    default void throwsIfNotEquals(@Nullable Object obj1, @Nullable Object obj2) {
        if (Objects.notEquals(obj1, obj2)) {
            throwsNow();
        }
    }

    default void throwsIfNotEquals(@Nullable Object obj1, @Nullable Object... objs) {
        if (Objects.notEquals(obj1, objs)) {
            throwsNow();
        }
    }

    default <T> void throwsIfCollEquals(@Nullable T obj1, @Nullable Collection<T> objs) {
        if (Objects.collEquals(obj1, objs)) {
            throwsNow();
        }
    }

    default <T> void throwsIfCollNotEquals(@Nullable T obj1, @Nullable Collection<T> objs) {
        if (Objects.collNotEquals(obj1, objs)) {
            throwsNow();
        }
    }

    default void throwsNow() {
        throw new RunningException(this);
    }

}
