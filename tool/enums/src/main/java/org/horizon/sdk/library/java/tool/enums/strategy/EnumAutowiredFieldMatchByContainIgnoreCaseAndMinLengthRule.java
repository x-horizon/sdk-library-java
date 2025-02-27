package org.horizon.sdk.library.java.tool.enums.strategy;

import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import java.util.Comparator;
import java.util.List;

/**
 * @author wjm
 * @since 2025-01-06 18:38
 */
public class EnumAutowiredFieldMatchByContainIgnoreCaseAndMinLengthRule implements EnumAutowiredFieldMatchRule {

    @Override
    public <E extends Enum<E>> String getMostSuitableAutowiredClassSimpleName(Enum<E> enumField, List<String> enumAutowiredSubclassSimpleNames) {
        String formattedEnumFieldName = Strings.removeAll(enumField.name(), SymbolConstant.UNDERLINE);
        return enumAutowiredSubclassSimpleNames.stream()
                .filter(enumAutowiredSubclassSimpleName -> Strings.containsIgnoreCase(enumAutowiredSubclassSimpleName, formattedEnumFieldName))
                .min(Comparator.comparing(String::length))
                .orElse(null);
    }

}