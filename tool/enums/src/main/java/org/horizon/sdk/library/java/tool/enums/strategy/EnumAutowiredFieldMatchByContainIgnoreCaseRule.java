package org.horizon.sdk.library.java.tool.enums.strategy;

import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import java.util.List;

/**
 * the rule of {@link EnumAutowiredFieldMatchRule} implemented by the enum autowired subclass simple name contain the enum field name ignore case.
 *
 * @author wjm
 * @since 2024-06-06 20:32
 */
public class EnumAutowiredFieldMatchByContainIgnoreCaseRule implements EnumAutowiredFieldMatchRule {

    @Override
    public <E extends Enum<E>> String getMostSuitableAutowiredClassSimpleName(Enum<E> enumField, List<String> enumAutowiredSubclassSimpleNames) {
        String formattedEnumFieldName = Strings.removeAll(enumField.name(), SymbolConstant.UNDERLINE);
        return enumAutowiredSubclassSimpleNames.stream()
                .filter(enumAutowiredSubclassSimpleName -> Strings.containsIgnoreCase(enumAutowiredSubclassSimpleName, formattedEnumFieldName))
                .findFirst()
                .orElse(null);
    }

}