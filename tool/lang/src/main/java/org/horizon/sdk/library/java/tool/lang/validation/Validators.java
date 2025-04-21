package org.horizon.sdk.library.java.tool.lang.validation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.tool.lang.validation.constraint.ConstraintBuilder;

/**
 * @author wjm
 * @since 2025-04-19 00:53
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validators {

    public static <M> ConstraintBuilder<M> builder() {
        return new ConstraintBuilder<>();
    }

}