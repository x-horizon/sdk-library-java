package org.horizon.sdk.library.java.tool.lang.validation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.tool.lang.validation.constraint.ConstraintBuilder;

/**
 * tool kit for validation
 *
 * @author wjm
 * @since 2025-04-19 00:53
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validators {

    /**
     * creates a new constraint builder instance for model type {@code M}.
     *
     * <p>usage example:
     * <pre>{@code
     * // define VALIDATOR in UserVO.
     * public static final Validator<UserVO> VALIDATOR = Validators.<UserVO>builder()
     *             .constraintOnGroup(ValidationGroup.CREATE, builder -> builder.constraint(UserVO::getId, Constraint::mustNull).build())
     *             .constraintOnGroup(ValidationGroup.UPDATE, builder -> builder.constraint(UserVO::getId, Constraint::mustNotNull).build())
     *             .constraint(UserVO::getName, constraint -> constraint.mustNotBlank().mustLessThanOrEquals(64))
     *             .constraint(UserVO::getGenderType, Constraint::mustNotNull)
     *             .constraint(UserVO::getIdentityCard, constraint -> constraint.skipNull().mustValidIdentityCard())
     *             .constraint(UserVO::getEmail, constraint -> constraint.skipNull().mustValidEmail())
     *             .constraint(UserVO::getPhone, constraint -> constraint.skipNull().mustValidPhone())
     *             .build();
     *
     * // usage:
     * UserVO.VALIDATOR.validate(userVO, ValidationGroup.CREATE).throwsIfViolated();
     * UserVO.VALIDATOR.validate(userVO, ValidationGroup.UPDATE).throwsIfViolated();
     * }</pre>
     *
     * @param <M> type of the model to validate
     * @return new constraint builder instance
     */
    public static <M> ConstraintBuilder<M> builder() {
        return new ConstraintBuilder<>();
    }

}