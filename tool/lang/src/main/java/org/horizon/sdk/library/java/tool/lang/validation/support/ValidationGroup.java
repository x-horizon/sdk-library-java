package org.horizon.sdk.library.java.tool.lang.validation.support;

/**
 * defines validation groups for organizing constraint rules based on business scenarios.
 *
 * <p>usage examples:
 * <pre>{@code
 * // apply different validation rules for create/update operations
 * Validators.<UserVO>builder()
 *             .constraintOnGroup(ValidationGroup.CREATE, builder -> builder.constraint(UserVO::getId, Constraint::mustNull).build())
 *             .constraintOnGroup(ValidationGroup.UPDATE, builder -> builder.constraint(UserVO::getId, Constraint::mustNotNull).build())
 * }</pre>
 *
 * @author wjm
 * @since 2025-04-17 16:50
 */
public enum ValidationGroup {
    CREATE,
    UPDATE,

    ;
}