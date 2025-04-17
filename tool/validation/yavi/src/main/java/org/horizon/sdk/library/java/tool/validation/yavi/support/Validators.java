package org.horizon.sdk.library.java.tool.validation.yavi.support;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.ConstraintViolation;
import am.ik.yavi.core.Validator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.contract.model.throwable.InvalidArgumentException;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.text.Strings;
import org.horizon.sdk.library.java.tool.validation.yavi.message.ValidationMessageFormatter;

/**
 * @author wjm
 * @since 2025-04-17 16:29
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validators {

    public static <T> ValidatorBuilder<T> of() {
        return ValidatorBuilder.<T>of().messageFormatter(ValidationMessageFormatter.INSTANCE);
    }

    public static <T> void throwsIfFailed(Validator<T> validator, T model) {
        validator.validate(model).throwIfInvalid(constraintViolations -> new InvalidArgumentException(Strings.joinWithChineseComma(Converts.toList(constraintViolations, ConstraintViolation::message))));
    }

    public static <T> void throwsIfFailed(Validator<T> validator, ValidationGroup validationGroup, T model) {
        validator.validate(model, validationGroup).throwIfInvalid(constraintViolations -> new InvalidArgumentException(Strings.joinWithChineseComma(Converts.toList(constraintViolations, ConstraintViolation::message))));
    }

}