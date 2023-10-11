// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.number;

import cn.srd.library.java.tool.lang.object.Classes;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * float number handler
 *
 * @author wjm
 * @since 2023-09-21 21:37
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FloatHandler implements NumberHandler {

    /**
     * the singleton instance
     */
    static final FloatHandler INSTANCE = new FloatHandler();

    @Override
    public boolean isAssignable(Class<?> input) {
        return Classes.isAssignable(Float.class, input);
    }

    @Override
    public <T extends Number> Number getValue(T input) {
        return input.floatValue();
    }

}
