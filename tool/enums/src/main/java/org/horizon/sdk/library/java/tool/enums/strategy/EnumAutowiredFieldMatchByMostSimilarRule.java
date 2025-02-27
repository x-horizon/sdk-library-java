package org.horizon.sdk.library.java.tool.enums.strategy;

import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import java.util.Collection;
import java.util.List;

/**
 * the rule of {@link EnumAutowiredFieldMatchRule} implemented by {@link Strings#getMostSimilar(String, Collection)}
 *
 * @author wjm
 * @since 2021-09-08 16:07
 */
public class EnumAutowiredFieldMatchByMostSimilarRule implements EnumAutowiredFieldMatchRule {

    @Override
    public <E extends Enum<E>> String getMostSuitableAutowiredClassSimpleName(Enum<E> enumField, List<String> enumAutowiredSubclassSimpleNames) {
        return Strings.getMostSimilar(Strings.removeAll(enumField.name(), SymbolConstant.UNDERLINE), enumAutowiredSubclassSimpleNames);
    }

}