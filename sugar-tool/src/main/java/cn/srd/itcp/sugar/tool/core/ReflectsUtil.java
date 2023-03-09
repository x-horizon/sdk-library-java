package cn.srd.itcp.sugar.tool.core;

import cn.hutool.core.util.ReflectUtil;
import io.vavr.control.Try;

import java.lang.reflect.Field;

/**
 * 反射工具
 *
 * @author wjm
 * @since 2021/6/3 11:54
 */
public class ReflectsUtil extends ReflectUtil {

    /**
     * private block constructor
     */
    private ReflectsUtil() {
    }

    /**
     * <pre>
     * see {@link ReflectUtil#getFieldValue(Object, Field)}
     * 用于容忍所有异常，主要为 JDK16 后模块化影响导致无法反射 private final 作用域的字段，请确认有必要，才调用该函数
     * </pre>
     *
     * @param obj   对象，static字段则此字段为null
     * @param field 字段
     * @return 字段值
     */
    public static Object getFieldValueIgnoreThrowable(Object obj, Field field) {
        return Try.of(() -> ReflectUtil.getFieldValue(obj, field)).getOrNull();
    }

}
