package org.horizon.sdk.library.java.tool.lang.validation.support;

import lombok.Getter;
import org.horizon.sdk.library.java.tool.lang.validation.violation.ViolationMessageType;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;

/**
 * represents a single validation rule containing validation logic and error messaging configuration.
 *
 * @param <V> type of the value being validated
 * @author wjm
 * @since 2025-04-19 01:04
 */
@Getter
public class ValidationRule<V> implements Serializable {

    @Serial private static final long serialVersionUID = -4299445295003717158L;

    /**
     * flag indicating whether to skip validation.
     */
    private SkipCheckType skipCheckType;

    /**
     * parameters for error message formatting.
     * <p>usage in violation messages:
     * <pre>{@code
     * "field {0} must be greater than {1}"
     * }</pre>
     * where:
     * <ol>
     *   <li>index 0: field name</li>
     *   <li>index 1: first argument from this list</li>
     * </ol>
     */
    private List<Object> arguments;

    /**
     * template type for validation failure messages.
     * <p>determines both the message pattern and the error category.
     *
     * @see ViolationMessageType
     */
    private ViolationMessageType messageType;

    /**
     * validation logic encapsulated as a predicate.
     * <p>validation succeeds when:
     * <pre>{@code predicate.test(fieldValue) == true}</pre>
     */
    private final Predicate<V> predicate;

    /**
     * constructs a null-skipping validation rule.
     *
     * @param skipCheckType the flag indicating whether to skip validation
     */
    public ValidationRule(SkipCheckType skipCheckType) {
        this.skipCheckType = skipCheckType;
        this.predicate = ignored -> true;
    }

    /**
     * constructs a full-featured validation rule.
     *
     * @param arguments   parameters for error message formatting
     * @param messageType template for violation messages
     * @param predicate   validation logic to execute
     */
    public ValidationRule(List<Object> arguments, ViolationMessageType messageType, Predicate<V> predicate) {
        this.arguments = arguments;
        this.messageType = messageType;
        this.predicate = predicate;
    }

}