package cn.srd.itcp.sugar.tools.core.convert;

import cn.hutool.core.convert.Convert;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * All in one 转换器
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
public class Converts extends Convert {

    /**
     * protected block constructor
     */
    protected Converts() {
    }

    /**
     * 是否静默转换，即报错不抛出异常，只打印日志，默认不静默转换
     */
    public static final boolean DEFAULT_CONVERT_QUIETLY = false;

    /**
     * Supplier =&gt; Function
     *
     * @param supplier 转换源
     * @param <T>      转换源参数类型
     * @return 结果集
     */
    public static <T> Function<Void, T> toFunction(Supplier<T> supplier) {
        return t -> supplier.get();
    }

    /**
     * Consumer =&gt; Function
     *
     * @param param    消费参数
     * @param consumer 转换源
     * @param <T>      转换源参数类型
     * @return 结果集
     */
    public static <T> Function<T, Void> toFunction(T param, Consumer<T> consumer) {
        return t -> {
            consumer.accept(param);
            return null;
        };
    }

    /**
     * Predicate =&gt; Function
     *
     * @param param     断言参数
     * @param predicate 转换源
     * @param <T>       转换源参数类型
     * @return 结果集
     */
    public static <T> Function<T, Boolean> toFunction(T param, Predicate<T> predicate) {
        return t -> predicate.test(param);
    }

}

