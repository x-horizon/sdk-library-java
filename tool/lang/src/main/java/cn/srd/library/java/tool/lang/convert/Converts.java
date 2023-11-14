// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.convert;

import cn.hutool.core.convert.Convert;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.tool.lang.functional.Action;
import cn.srd.library.java.tool.lang.functional.Functional;
import cn.srd.library.java.tool.lang.number.NumberType;
import cn.srd.library.java.tool.lang.text.Strings;
import io.vavr.Function3;
import io.vavr.Function4;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.function.*;

/**
 * toolkit for convert
 *
 * @author wjm
 * @since 2021-05-01 14:13
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Converts {

    public static final boolean DEFAULT_CONVERT_QUIETLY = false;

    /**
     * convert byte array to string
     *
     * @param input the input element
     * @return after convert
     */
    public static String toString(byte[] input) {
        return Action.<String>ifNull(input)
                .then(Functional.nullSupplier())
                .otherwise(() -> new String(input))
                .get();
    }

    /**
     * convert object to string
     *
     * @param input the input element
     * @return after convert
     * @see Convert#toStr(Object)
     */
    public static String toString(Object input) {
        return Convert.toStr(input);
    }

    /**
     * convert object to boolean
     *
     * @param input the input element
     * @return after convert
     * @see Convert#toBool(Object)
     */
    public static Boolean toBoolean(Object input) {
        return Convert.toBool(input);
    }

    /**
     * convert an object to integer
     *
     * @param input the input element
     * @return after convert
     */
    public static Number toInteger(Object input) {
        return Convert.toInt(input);
    }

    /**
     * convert an object to number
     *
     * @param input the input element
     * @return after convert
     */
    public static Number toNumber(Object input) {
        return Convert.toNumber(input);
    }

    /**
     * convert an object to number  // TODO wjm unsupported all extend Number
     *
     * @param input       the input element
     * @param outputClass the output number class, only support {@link NumberType}
     * @param <T>         the output number type
     * @return after convert
     */
    public static <T extends Number> T toNumber(Object input, Class<T> outputClass) {
        return outputClass.cast(Arrays.stream(NumberType.values())
                .filter(numberType -> numberType.getHandler().isAssignable(outputClass))
                .findFirst()
                .map(numberType -> numberType.getHandler().getValue(toNumber(input)))
                .orElseThrow(() -> new LibraryJavaInternalException(Strings.format("could not convert [{}] to data type [{}.class]", input, outputClass.getSimpleName())))
        );
    }

    public static <T> Function<Void, T> toFunction(Supplier<T> action) {
        return ignore -> action.get();
    }

    public static <T> Function<T, Void> toFunction(Consumer<T> action) {
        return input -> {
            action.accept(input);
            return null;
        };
    }

    public static <T> Function<T, Boolean> toFunction(Predicate<T> action) {
        return action::test;
    }

    public static <T, R> BiFunction<T, Void, R> toBiFunction(Function<T, R> action) {
        return (input, ignore) -> action.apply(input);
    }

    public static <T1, T2, T3, R> Function3<T1, T2, T3, R> toFunction3(Function<T1, R> action) {
        return (input, ignore1, ignore2) -> action.apply(input);
    }

    public static <T1, T2, T3, T4, R> Function4<T1, T2, T3, T4, R> toFunction4(Function<T1, R> action) {
        return (input, ignore1, ignore2, ignore3) -> action.apply(input);
    }

    public static <T1, T2, T3, T4, R> Function4<T1, T2, T3, T4, R> toFunction4(Function3<T1, T2, T3, R> action) {
        return (input1, input2, input3, ignore) -> action.apply(input1, input2, input3);
    }

}
