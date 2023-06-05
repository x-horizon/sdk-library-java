package cn.srd.itcp.sugar.tool.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * lambda 工具类
 *
 * @author wjm
 * @since 2023-06-05 15:57:19
 */
public class LambdasUtil {

    /**
     * private block constructor
     */
    private LambdasUtil() {
    }

    /**
     * 若对受检对象检查通过，消费指定逻辑
     *
     * @param input 受检对象
     * @param check 检查逻辑
     * @param logic 消费逻辑
     * @param <T>   受检对象类型
     */
    public static <T> void acceptIfNeed(T input, Predicate<T> check, Consumer<T> logic) {
        if (check.test(input)) {
            logic.accept(input);
        }
    }

    /**
     * 若对受检对象检查通过，应用指定逻辑
     *
     * @param input 受检对象
     * @param check 检查逻辑
     * @param logic 应用逻辑
     * @param <T>   受检对象类型
     * @param <R>   返回结果类型
     * @return
     */
    @CanIgnoreReturnValue
    public static <T, R> R applyIfNeed(T input, Predicate<T> check, Function<T, R> logic) {
        if (check.test(input)) {
            return logic.apply(input);
        }
        return null;
    }

}
