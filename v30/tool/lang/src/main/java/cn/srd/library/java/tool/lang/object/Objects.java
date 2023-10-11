// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.object;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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

}
