// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.binary;

import cn.hutool.core.util.HexUtil;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.functional.Action;
import cn.srd.library.java.tool.lang.object.Nil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * toolkit for binary
 *
 * @author wjm
 * @since 2022-07-06 21:53
 */
@SuppressWarnings(SuppressWarningConstant.UNUSED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Binaries {

    /**
     * see {@link #toHexString(byte[], boolean)}
     *
     * @param inputs          the input elements
     * @param needToLowerCase need lower case or not
     * @return after convert
     */
    public static String toHexString(Byte[] inputs, boolean needToLowerCase) {
        return Action.<String>infer(Nil.isNull(inputs))
                .then(() -> SymbolConstant.EMPTY)
                .otherwise(() -> toHexString(Collections.unWrap(inputs), needToLowerCase))
                .get();
    }

    /**
     * see {@link HexUtil#encodeHexStr(byte[], boolean)}
     *
     * @param inputs          the input elements
     * @param needToLowerCase need lower case or not
     * @return after convert
     */
    public static String toHexString(byte[] inputs, boolean needToLowerCase) {
        return HexUtil.encodeHexStr(inputs, needToLowerCase);
    }

}
