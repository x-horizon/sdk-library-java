package org.horizon.sdk.library.java.tool.lang.validation.support;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * represents a complete validation configuration for a specific field in a model.
 *
 * @param <M> type of the model containing the field
 * @param <V> type of the field value being validated
 * @author wjm
 * @since 2025-04-19 01:06
 */
@Getter
@AllArgsConstructor
public class ValidationSchema<M, V> {

    /**
     * display name for the field in validation messages.
     * <p>characteristics:
     * <ul>
     *   <li>used in error messages to identify the field</li>
     *   <li>should match the business context naming</li>
     *   <li>example: "emailAddress" instead of "email_field"</li>
     * </ul>
     */
    private String fieldName;

    /**
     * functional accessor to retrieve the field value from the model.
     * <p>implementation notes:
     * <ul>
     *   <li>typically method references like {@code User::getName}</li>
     *   <li>handles null values according to validation rules</li>
     *   <li>supports nested properties (e.g. {@code Order::getCustomer::getName})</li>
     * </ul>
     */
    private Function<M, V> fieldValueGetter;

    /**
     * collection of validation rules for the field.
     * <p>behavior details:
     * <ul>
     *   <li>rules are evaluated in declaration order</li>
     *   <li>all failed rules generate separate violations</li>
     *   <li>empty list means no validation applied</li>
     * </ul>
     */
    private List<ValidationRule<V>> validationRules;

    /**
     * flag indicating whether to skip validation.
     */
    private Set<SkipCheckType> skipCheckTypes;

}