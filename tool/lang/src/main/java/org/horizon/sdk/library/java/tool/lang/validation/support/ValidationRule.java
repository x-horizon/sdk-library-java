package org.horizon.sdk.library.java.tool.lang.validation.support;

import lombok.Getter;
import org.horizon.sdk.library.java.tool.lang.validation.violation.ViolationMessageType;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author wjm
 * @since 2025-04-19 01:04
 */
@Getter
public class ValidationRule<V> {

    private boolean needToSkipNull = false;

    private List<Object> arguments;

    private ViolationMessageType messageType;

    private final Predicate<V> predicate;

    public ValidationRule(boolean needToSkipNull) {
        this.needToSkipNull = needToSkipNull;
        this.predicate = ignored -> true;
    }

    public ValidationRule(List<Object> arguments, ViolationMessageType messageType, Predicate<V> predicate) {
        this.arguments = arguments;
        this.messageType = messageType;
        this.predicate = predicate;
    }

}