package cn.srd.sugar.tool.lang.core.asserts;

import cn.srd.itcp.sugar.tool.web.HttpStatus;
import cn.srd.sugar.contract.throwable.core.ExceptionMessageTemplate;
import cn.srd.sugar.contract.throwable.core.ExceptionTemplate;
import cn.srd.sugar.contract.throwable.core.RunningException;
import cn.srd.sugar.tool.lang.core.object.Objects;
import cn.srd.sugar.tool.lang.core.validation.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * assert support
 *
 * @author wjm
 * @since 2020/6/13 20:05
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
    // TODO wjm 实现延迟加载这三个形参
    AssertSupport set(@Nullable Integer code, @Nullable String message, @Nullable Exception exception);

    //=====================断言功能

    /**
     * 断言，see {@link Objects#isNull(Object)}
     *
     * @param object 待断言对象
     */
    default void throwsIfNull(Object object) {
        if (Objects.isNull(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNull(Object...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfNull(Object... objects) {
        if (Objects.isNull(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isAllNull(Object...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfAllNull(Object... objects) {
        if (Objects.isAllNull(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotNull(Object)}
     *
     * @param object 待断言对象
     */
    default void throwsIfNotNull(Object object) {
        if (Objects.isNotNull(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotNull(Object...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfNotNull(Object... objects) {
        if (Objects.isNotNull(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(CharSequence)}
     *
     * @param object 待断言对象
     */
    default void throwsIfEmpty(CharSequence object) {
        if (Objects.isEmpty(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(CharSequence...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfEmpty(CharSequence... objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isAllEmpty(CharSequence...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfAllEmpty(CharSequence... objects) {
        if (Objects.isAllEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isBlank(CharSequence)}
     *
     * @param object 待断言对象
     */
    default void throwsIfBlank(CharSequence object) {
        if (Objects.isBlank(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isBlank(CharSequence...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfBlank(CharSequence... objects) {
        if (Objects.isBlank(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isAllBlank(CharSequence...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfAllBlank(CharSequence... objects) {
        if (Objects.isAllBlank(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(Map)}
     *
     * @param object 待断言对象
     */
    default void throwsIfEmpty(Map<?, ?> object) {
        if (Objects.isEmpty(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(Map...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfEmpty(Map<?, ?>... objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isAllEmpty(Map...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfAllEmpty(Map<?, ?>... objects) {
        if (Objects.isAllEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(Iterable)}
     *
     * @param object 待断言对象
     */
    default void throwsIfEmpty(Iterable<?> object) {
        if (Objects.isEmpty(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(Iterable...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfEmpty(Iterable<?>... objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isAllEmpty(Iterable...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfAllEmpty(Iterable<?>... objects) {
        if (Objects.isAllEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(Iterator)}
     *
     * @param object 待断言对象
     */
    default void throwsIfEmpty(Iterator<?> object) {
        if (Objects.isEmpty(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(Iterator...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfEmpty(Iterator<?>... objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isAllEmpty(Iterator...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfAllEmpty(Iterator<?>... objects) {
        if (Objects.isAllEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(byte[])}
     *
     * @param objects 待断言对象
     */
    default void throwsIfEmpty(byte[] objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(short[])}
     *
     * @param objects 待断言对象
     */
    default void throwsIfEmpty(short[] objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(int[])}
     *
     * @param objects 待断言对象
     */
    default void throwsIfEmpty(int[] objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(long[])}
     *
     * @param objects 待断言对象
     */
    default void throwsIfEmpty(long[] objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(float[])}
     *
     * @param objects 待断言对象
     */
    default void throwsIfEmpty(float[] objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(double[])}
     *
     * @param objects 待断言对象
     */
    default void throwsIfEmpty(double[] objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(char[])}
     *
     * @param objects 待断言对象
     */
    default void throwsIfEmpty(char[] objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(boolean[])}
     *
     * @param objects 待断言对象
     */
    default void throwsIfEmpty(boolean[] objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(String[])}
     *
     * @param objects 待断言对象
     */
    default void throwsIfEmpty(String[] objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(Object)}
     *
     * @param object 待断言对象
     */
    default void throwsIfEmpty(Object object) {
        if (Objects.isEmpty(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isEmpty(Object...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfEmpty(Object... objects) {
        if (Objects.isEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isAllEmpty(Object...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfAllEmpty(Object... objects) {
        if (Objects.isAllEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(CharSequence)}
     *
     * @param object 待断言对象
     */
    default void throwsIfNotEmpty(CharSequence object) {
        if (Objects.isNotEmpty(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(CharSequence...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfNotEmpty(CharSequence... objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotBlank(CharSequence)}
     *
     * @param object 待断言对象
     */
    default void throwsIfNotBlank(CharSequence object) {
        if (Objects.isNotBlank(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotBlank(CharSequence...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfNotBlank(CharSequence... objects) {
        if (Objects.isNotBlank(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(Map)}
     *
     * @param object 待断言对象
     */
    default void throwsIfNotEmpty(Map<?, ?> object) {
        if (Objects.isNotEmpty(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(Map...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfNotEmpty(Map<?, ?>... objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(Iterable)}
     *
     * @param object 待断言对象
     */
    default void throwsIfNotEmpty(Iterable<?> object) {
        if (Objects.isNotEmpty(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(Iterable...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfNotEmpty(Iterable<?>... objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(Iterator)}
     *
     * @param object 待断言对象
     */
    default void throwsIfNotEmpty(Iterator<?> object) {
        if (Objects.isNotEmpty(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(Iterator...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfNotEmpty(Iterator<?>... objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(byte[])}
     *
     * @param objects 待断言对象
     */
    default void throwsIfNotEmpty(byte[] objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(short[])}
     *
     * @param objects 待断言对象
     */
    default void throwsIfNotEmpty(short[] objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(int[])}
     *
     * @param objects 待断言对象
     */
    default void throwsIfNotEmpty(int[] objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(long[])}
     *
     * @param objects 待断言对象
     */
    default void throwsIfNotEmpty(long[] objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(float[])}
     *
     * @param objects 待断言对象
     */
    default void throwsIfNotEmpty(float[] objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(double[])}
     *
     * @param objects 待断言对象
     */
    default void throwsIfNotEmpty(double[] objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(char[])}
     *
     * @param objects 待断言对象
     */
    default void throwsIfNotEmpty(char[] objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(boolean[])}
     *
     * @param objects 待断言对象
     */
    default void throwsIfNotEmpty(boolean[] objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(String[])}
     *
     * @param objects 待断言对象
     */
    default void throwsIfNotEmpty(String[] objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(Object)}
     *
     * @param object 待断言对象
     */
    default void throwsIfNotEmpty(Object object) {
        if (Objects.isNotEmpty(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotEmpty(Object...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfNotEmpty(Object... objects) {
        if (Objects.isNotEmpty(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isTrue(Boolean)}
     *
     * @param object 待断言对象
     */
    default void throwsIfTrue(Boolean object) {
        if (Objects.isTrue(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isTrue(BooleanSupplier)}
     *
     * @param object 待断言对象
     */
    default void throwsIfTrue(BooleanSupplier object) {
        if (Objects.isTrue(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isTrue(Boolean)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfTrue(Boolean... objects) {
        if (Objects.isTrue(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isAllTrue(Boolean...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfAllTrue(Boolean... objects) {
        if (Objects.isAllTrue(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isFalse(Boolean)}
     *
     * @param object 待断言对象
     */
    default void throwsIfFalse(Boolean object) {
        if (Objects.isFalse(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isFalse(BooleanSupplier)}
     *
     * @param object 待断言对象
     */
    default void throwsIfFalse(BooleanSupplier object) {
        if (Objects.isFalse(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isFalse(Object, Predicate)}
     *
     * @param item      待断言对象
     * @param predicate 断言逻辑
     * @param <T>       对象类型
     */
    default <T> void throwsIfFalse(T item, Predicate<T> predicate) {
        if (Objects.isFalse(item, predicate)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isFalse(Boolean...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfFalse(Boolean... objects) {
        if (Objects.isFalse(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isAllFalse(Boolean...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfAllFalse(Boolean... objects) {
        if (Objects.isAllFalse(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isPositive(Number)}
     *
     * @param object 待断言对象
     */
    default void throwsIfPositive(Number object) {
        if (Objects.isPositive(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isAllPositive(Number...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfAllPositive(Number... objects) {
        if (Objects.isAllPositive(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotPositive(Number)}
     *
     * @param object 待断言对象
     */
    default void throwsIfNotPositive(Number object) {
        if (Objects.isNotPositive(object)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#isNotPositive(Number...)}
     *
     * @param objects 待断言对象
     */
    default void throwsIfNotPositive(Number... objects) {
        if (Objects.isNotPositive(objects)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#equals(Object, Object)}
     *
     * @param obj1 待比较对象
     * @param obj2 待比较对象
     */
    default void throwsIfEquals(@Nullable Object obj1, @Nullable Object obj2) {
        if (Objects.equals(obj1, obj2)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#equals(Object, Object...)}
     *
     * @param obj1 待比较对象
     * @param objs 待比较对象
     */
    default void throwsIfEquals(@Nullable Object obj1, @Nullable Object... objs) {
        if (Objects.equals(obj1, objs)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#notEquals(Object, Object)}
     *
     * @param obj1 待比较对象
     * @param obj2 待比较对象
     */
    default void throwsIfNotEquals(@Nullable Object obj1, @Nullable Object obj2) {
        if (Objects.notEquals(obj1, obj2)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#notEquals(Object, Object...)}
     *
     * @param obj1 待比较对象
     * @param objs 待比较对象
     */
    default void throwsIfNotEquals(@Nullable Object obj1, @Nullable Object... objs) {
        if (Objects.notEquals(obj1, objs)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#collEquals(Object, Collection)}
     *
     * @param obj1 待比较对象
     * @param objs 待比较对象
     * @param <T>  待比较对象类型
     */
    default <T> void throwsIfCollEquals(@Nullable T obj1, @Nullable Collection<T> objs) {
        if (Objects.collEquals(obj1, objs)) {
            throwsNow();
        }
    }

    /**
     * 断言，see {@link Objects#collNotEquals(Object, Collection)}
     *
     * @param obj1 待比较对象
     * @param objs 待比较对象
     * @param <T>  待比较对象类型
     */
    default <T> void throwsIfCollNotEquals(@Nullable T obj1, @Nullable Collection<T> objs) {
        if (Objects.collNotEquals(obj1, objs)) {
            throwsNow();
        }
    }

    /**
     * 抛出异常
     */
    default void throwsNow() {
        throw new RunningException(this);
    }

}
