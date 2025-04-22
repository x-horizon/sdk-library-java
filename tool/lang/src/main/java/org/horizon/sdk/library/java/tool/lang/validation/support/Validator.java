package org.horizon.sdk.library.java.tool.lang.validation.support;

import lombok.AllArgsConstructor;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.text.Strings;
import org.horizon.sdk.library.java.tool.lang.validation.violation.Violation;
import org.horizon.sdk.library.java.tool.lang.validation.violation.Violator;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * core validation executor that checks model objects against configured constraints.
 *
 * <p>typical workflow:
 * <ol>
 *   <li>receive model object and validation groups</li>
 *   <li>find matching validation schemas based on conditions</li>
 *   <li>execute field validation rules sequentially</li>
 *   <li>aggregate all violations</li>
 * </ol>
 *
 * @param <M> type of the model being validated
 * @author wjm
 * @since 2025-04-18 15:36
 */
@AllArgsConstructor
public class Validator<M> {

    private ValidatorBuilder<M> validatorBuilder;

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <T> ValidatorBuilder<T> toBuilder() {
        return (ValidatorBuilder<T>) this.validatorBuilder;
    }

    /**
     * performs validation on the target model and returns violation results.
     *
     * <p>execution steps:
     * <ol>
     *   <li>filter conditions matching current model state</li>
     *   <li>retrieve all associated validation schemas</li>
     *   <li>for each schema:
     *     <ul>
     *       <li>skip if field value is null and rules permit skipping</li>
     *       <li>execute all validation rules</li>
     *       <li>collect failed validations</li>
     *     </ul>
     *   </li>
     *   <li>format violation messages using {@link Strings#format}</li>
     * </ol>
     *
     * @param model            target object to validate
     * @param validationGroups active validation groups
     * @return violator containing all violations
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public Violator validate(M model, ValidationGroup... validationGroups) {
        return new Violator(this.validatorBuilder.conditionValidationSchemaMap.entrySet().stream()
                .filter(entry -> entry.getKey().test(model, validationGroups))
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .flatMap(validationSchema -> {
                    Object fieldValue = validationSchema.getFieldValueGetter().apply(model);
                    if (validationSchema.getSkipCheckTypes().stream().anyMatch(skipCheckType -> skipCheckType.getPredicate().test(fieldValue))) {
                        return Stream.empty();
                    }
                    // if (Nil.isNull(fieldValue) && validationSchema.getValidationRules().stream().anyMatch(ValidationRule::isNeedToSkipNull)) {
                    //     return Stream.empty();
                    // }
                    String failedMessage = validationSchema.getValidationRules().stream()
                            .filter(validationRule -> !((Predicate<Object>) validationRule.getPredicate()).test(fieldValue))
                            .map(failedValidationRule -> Strings.format(failedValidationRule.getMessageType().getMessage(), Collections.addFirst(failedValidationRule.getArguments(), validationSchema.getFieldName()).toArray()))
                            .collect(Collectors.joining(SymbolConstant.LF));
                    return Nil.isEmpty(failedMessage) ? Stream.empty() : Stream.of(new Violation(validationSchema.getFieldName(), failedMessage));
                })
                .toList()
        );
    }

}