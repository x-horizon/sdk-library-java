package cn.srd.itcp.sugar.tool.core.convert;

import cn.hutool.core.convert.Convert;
import cn.srd.itcp.sugar.tool.core.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.*;

/**
 * All in one 转换器
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Converts extends Convert {

    /**
     * 是否静默转换，即报错不抛出异常，只打印日志，默认不静默转换
     */
    public static final boolean DEFAULT_CONVERT_QUIETLY = false;

    /**
     * byte[] =&gt; {@link String}
     *
     * @param from 转换源
     * @return 结果集
     */
    public static String toString(byte[] from) {
        if (Objects.isNull(from)) {
            return null;
        }
        return new String(from);
    }

    /**
     * {@link Supplier} =&gt; {@link Function}
     *
     * @param from 转换源
     * @param <T>  转换源出参类型
     * @return 结果集
     */
    public static <T> Function<Void, T> toFunction(Supplier<T> from) {
        return t -> from.get();
    }

    /**
     * {@link Consumer} =&gt; {@link Function}
     *
     * @param param 参数
     * @param from  转换源
     * @param <T>   转换源形参类型
     * @return 结果集
     */
    public static <T> Function<T, Void> toFunction(T param, Consumer<T> from) {
        return t -> {
            from.accept(param);
            return null;
        };
    }

    /**
     * {@link Predicate} =&gt; {@link Function}
     *
     * @param param 参数
     * @param from  转换源
     * @param <T>   转换源形参类型
     * @return 结果集
     */
    public static <T> Function<T, Boolean> toFunction(T param, Predicate<T> from) {
        return t -> from.test(param);
    }

    /**
     * {@link Function} =&gt; {@link BiFunction}
     *
     * @param param 参数
     * @param from  转换源
     * @param <T>   转换源形参类型
     * @param <R>   转换源出参类型
     * @return 结果集
     */
    public static <T, R> BiFunction<T, Void, R> toBiFunction(T param, Function<T, R> from) {
        return (t, r) -> from.apply(param);
    }

}

