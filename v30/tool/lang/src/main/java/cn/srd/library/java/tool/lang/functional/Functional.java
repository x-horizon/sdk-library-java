// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.functional;

import cn.srd.library.java.contract.constant.text.SymbolConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;
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

}
