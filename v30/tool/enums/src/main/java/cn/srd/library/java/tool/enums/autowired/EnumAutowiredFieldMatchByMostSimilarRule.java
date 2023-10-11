// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.enums.autowired;

import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.tool.lang.text.Strings;

import java.util.List;

/**
 * the rule of {@link EnumAutowiredFieldMatchRule} implemented by {@link Strings#getMostSimilar(String, List)}
 *
 * @author wjm
 * @since 2021-09-08 16:07
 */
public class EnumAutowiredFieldMatchByMostSimilarRule implements EnumAutowiredFieldMatchRule {

    @Override
    public <E extends Enum<E>> String getMostSuitableAutowiredClassName(Enum<E> enumField, List<String> enumAutowiredSubclassNames) {
        return Strings.getMostSimilar(Strings.removeAll(enumField.name(), SymbolConstant.UNDERLINE), enumAutowiredSubclassNames);
    }

}
