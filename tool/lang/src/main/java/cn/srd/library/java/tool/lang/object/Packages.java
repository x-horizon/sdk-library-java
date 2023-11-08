// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.object;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * toolkit for package
 *
 * @author wjm
 * @since 2023-11-08 23:31
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Packages {

    /**
     * return the package path where the specified class is located
     *
     * @param input the specified class
     * @return the package path where the specified class is located
     */
    public static String getPackagePath(Class<?> input) {
        return input.getPackage().getName();
    }

}
