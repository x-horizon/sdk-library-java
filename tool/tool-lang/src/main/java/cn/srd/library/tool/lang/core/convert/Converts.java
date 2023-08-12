package cn.srd.library.tool.lang.core.convert;

import cn.hutool.core.convert.Convert;
import cn.srd.library.tool.lang.core.object.Objects;
import io.vavr.Function3;
import io.vavr.Function4;
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

    /**
     * {@link Function} =&gt; {@link Function3}
     *
     * @param param 参数
     * @param from  转换源
     * @param <T1>  转换源形参类型1
     * @param <T2>  转换源形参类型2
     * @param <T3>  转换源形参类型3
     * @param <R>   转换源出参类型
     * @return 结果集
     */
    public static <T1, T2, T3, R> Function3<T1, T2, T3, R> toFunction3(T1 param, Function<T1, R> from) {
        return (t1, t2, t3) -> from.apply(param);
    }

    /**
     * {@link Function} =&gt; {@link Function4}
     *
     * @param param 参数
     * @param from  转换源
     * @param <T1>  转换源形参类型1
     * @param <T2>  转换源形参类型2
     * @param <T3>  转换源形参类型3
     * @param <T4>  转换源形参类型4
     * @param <R>   转换源出参类型
     * @return 结果集
     */
    public static <T1, T2, T3, T4, R> Function4<T1, T2, T3, T4, R> toFunction4(T1 param, Function<T1, R> from) {
        return (t1, t2, t3, t4) -> from.apply(param);
    }

    /**
     * {@link Function3} =&gt; {@link Function4}
     *
     * @param param1 参数1
     * @param param2 参数2
     * @param param3 参数3
     * @param from   转换源
     * @param <T1>   转换源形参类型1
     * @param <T2>   转换源形参类型2
     * @param <T3>   转换源形参类型3
     * @param <T4>   转换源形参类型4
     * @param <R>    转换源出参类型
     * @return 结果集
     */
    public static <T1, T2, T3, T4, R> Function4<T1, T2, T3, T4, R> toFunction4(T1 param1, T2 param2, T3 param3, Function3<T1, T2, T3, R> from) {
        return (t1, t2, t3, t4) -> from.apply(param1, param2, param3);
    }

}

