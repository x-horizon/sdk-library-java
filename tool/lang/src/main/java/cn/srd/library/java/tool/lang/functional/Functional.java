// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.functional;

import cn.srd.library.java.contract.constant.text.SymbolConstant;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * toolkit for functional
 *
 * @author wjm
 * @since 2023-09-23 11:33
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Functional {

    public static Supplier<Boolean> falseSupplier() {
        return () -> false;
    }

    public static Supplier<Boolean> trueSupplier() {
        return () -> true;
    }

    public static <T> Supplier<T> nullSupplier() {
        return () -> null;
    }

    public static Supplier<String> emptyStringSupplier() {
        return () -> SymbolConstant.EMPTY;
    }

    public static <T> Consumer<T> emptyConsumer() {
        return ignore -> {
        };
    }

    public static <T> void acceptIfNeed(T input, Predicate<T> check, Consumer<T> logic) {
        if (check.test(input)) {
            logic.accept(input);
        }
    }

    public static <T> void acceptIfNeed(T input, Predicate<T> check, Consumer<T> checkSuccessLogic, Consumer<T> checkFailedLogic) {
        if (check.test(input)) {
            checkSuccessLogic.accept(input);
        } else {
            checkFailedLogic.accept(input);
        }
    }

    @CanIgnoreReturnValue
    public static <T, R> R applyIfNeed(T input, Predicate<T> check, Function<T, R> logic) {
        if (check.test(input)) {
            return logic.apply(input);
        }
        return null;
    }

}
