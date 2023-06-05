package cn.srd.itcp.sugar.tool.core;

import java.util.function.Consumer;
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
     * 若对受检对象检查通过，执行指定逻辑
     *
     * @param input 受检对象
     * @param check 检查逻辑
     * @param apply 执行逻辑
     * @param <T>   受检对象类型
     */
    public static <T> void applyIfNeed(T input, Predicate<T> check, Consumer<T> apply) {
        if (check.test(input)) {
            apply.accept(input);
        }
    }

}
