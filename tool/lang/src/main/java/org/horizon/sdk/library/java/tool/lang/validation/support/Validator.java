package org.horizon.sdk.library.java.tool.lang.validation.support;

import lombok.AllArgsConstructor;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.text.Strings;
import org.horizon.sdk.library.java.tool.lang.validation.constraint.ConstraintConditionAdaptor;
import org.horizon.sdk.library.java.tool.lang.validation.violation.Violation;
import org.horizon.sdk.library.java.tool.lang.validation.violation.Violator;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wjm
 * @since 2025-04-18 15:36
 */
@AllArgsConstructor
public class Validator<M> {

    private Map<ConstraintConditionAdaptor<M>, List<ValidationSchema<M, ?>>> conditionValidationSchemaMap;

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public Violator validate(M model, ValidationGroup... validationGroups) {
        return new Violator(this.conditionValidationSchemaMap.entrySet().stream()
                .filter(entry -> entry.getKey().test(model, validationGroups))
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .flatMap(validationSchema -> {
                    Object fieldValue = validationSchema.getFieldValueGetter().apply(model);
                    if (Nil.isNull(fieldValue) && validationSchema.getRules().stream().anyMatch(ValidationRule::isNeedToSkipNull)) {
                        return Stream.empty();
                    }
                    String failedMessage = validationSchema.getRules().stream()
                            .filter(validationRule -> !((Predicate<Object>) validationRule.getPredicate()).test(fieldValue))
                            .map(failedValidationRule -> Strings.format(failedValidationRule.getMessageType().getMessage(), Collections.addFirst(failedValidationRule.getArguments(), validationSchema.getFieldName()).toArray()))
                            .collect(Collectors.joining(SymbolConstant.LF));
                    return Nil.isEmpty(failedMessage) ? Stream.empty() : Stream.of(new Violation(validationSchema.getFieldName(), failedMessage));
                })
                .toList()
        );
    }

}