// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.object;

import cn.hutool.core.util.ObjectUtil;
import cn.srd.library.java.tool.lang.number.Numbers;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.ObjectStreamClass;
import java.util.function.Supplier;

/**
 * toolkit for object
 *
 * @author wjm
 * @since 2020-07-08 16:11
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Objects {

    public static <T> T setIfNull(T checked, T valueIfNull) {
        return setIfNull(checked, () -> valueIfNull);
    }

    public static <T> T setIfNull(T checked, Supplier<T> valueActionIfNull) {
        if (Nil.isNull(checked)) {
            return valueActionIfNull.get();
        }
        return checked;
    }

    public static <T> T[] setIfEmpty(T[] checked, T[] valueIfEmpty) {
        if (Nil.isEmpty(checked)) {
            return valueIfEmpty;
        }
        return checked;
    }

    /**
     * see {@link ObjectUtil#clone(Object)}
     *
     * @param input the cloned object
     * @param <T>   the cloned object type
     * @return object after clone
     */
    public static <T> T clone(T input) {
        return ObjectUtil.clone(input);
    }

    /**
     * get actual value TODO wjm whether it is practical?
     *
     * @param input 输入值
     * @return 获取实际值
     */
    public static Object getActualValue(String input) {
        if (Nil.isNull(input)) {
            return null;
        }
        try {
            if (Numbers.isInteger(input)) {
                return Integer.valueOf(input);
            }
            if (Numbers.isLong(input)) {
                return Long.valueOf(input);
            }
        } catch (Exception ignore) {
        }
        return input;
    }

    /**
     * get serial version uid in a class implement {@link java.io.Serializable Serializable}
     *
     * @param input the class implement {@link java.io.Serializable Serializable}
     * @return serial version uid
     */
    public static long getSerialVersionUID(Class<?> input) {
        return ObjectStreamClass.lookup(input).getSerialVersionUID();
    }

}
