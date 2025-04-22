package org.horizon.sdk.library.java.tool.lang.validation.violation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.contract.model.throwable.InvalidArgumentException;
import org.horizon.sdk.library.java.tool.lang.functional.Assert;

import java.util.List;
import java.util.stream.Collectors;

/**
 * container class for validation failures and exception handling mechanism.
 *
 * <p>this class serves two main purposes:
 * <ol>
 *   <li>aggregates multiple validation violations</li>
 *   <li>provides exception throwing capability when violations exist</li>
 * </ol>
 *
 * <p>typical usage:
 * <pre>{@code
 * // throws exception with all messages
 * validator.validate(userVO).throwsIfViolated();
 * }</pre>
 *
 * @author wjm
 * @since 2025-04-19 23:41
 */
@Getter
@AllArgsConstructor
public class Violator {

    /**
     * collection of detected validation failures.
     * <ul>
     *   <li>empty list indicates successful validation</li>
     *   <li>each entry represents a single failed validation rule</li>
     *   <li>order matches validation execution sequence</li>
     * </ul>
     */
    private List<Violation> violations;

    /**
     * throws formatted exception if any validation violations exist.
     *
     * <p>execution flow:
     * <ol>
     *   <li>collects all violation messages</li>
     *   <li>joins messages with linefeed separator</li>
     *   <li>throws {@link InvalidArgumentException} if messages exist</li>
     * </ol>
     */
    public void throwsIfViolated() {
        String message = this.violations.stream()
                .map(Violation::getMessage)
                .collect(Collectors.joining(SymbolConstant.LF));
        Assert.of().setThrowable(InvalidArgumentException.class).setMessage(message).throwsIfNotBlank(message);
    }

}