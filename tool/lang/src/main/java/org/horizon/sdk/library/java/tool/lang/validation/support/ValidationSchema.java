package org.horizon.sdk.library.java.tool.lang.validation.support;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.function.Function;

/**
 * @author wjm
 * @since 2025-04-19 01:06
 */
@Getter
@AllArgsConstructor
public class ValidationSchema<M, V> {

    private String fieldName;

    private Function<M, V> fieldValueGetter;

    private List<ValidationRule<V>> rules;

}