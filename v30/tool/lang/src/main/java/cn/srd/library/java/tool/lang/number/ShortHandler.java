// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.number;

import cn.srd.library.java.tool.lang.object.Classes;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * short number handler
 *
 * @author wjm
 * @since 2023-09-21 21:37
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShortHandler implements NumberHandler {

    /**
     * the singleton instance
     */
    static final ShortHandler INSTANCE = new ShortHandler();

    @Override
    public boolean isAssignable(Class<?> input) {
        return Classes.isAssignable(Short.class, input);
    }

    @Override
    public <T extends Number> Number getValue(T input) {
        return input.shortValue();
    }

}
