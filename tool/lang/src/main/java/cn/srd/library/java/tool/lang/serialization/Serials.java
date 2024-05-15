// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.serialization;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.ObjectStreamClass;

/**
 * toolkit for serial
 *
 * @author wjm
 * @since 2023-11-04 01:36
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Serials {

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