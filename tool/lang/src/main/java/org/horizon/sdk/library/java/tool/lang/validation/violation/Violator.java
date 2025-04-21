package org.horizon.sdk.library.java.tool.lang.validation.violation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.contract.model.throwable.InvalidArgumentException;
import org.horizon.sdk.library.java.tool.lang.functional.Assert;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wjm
 * @since 2025-04-19 23:41
 */
@Getter
@AllArgsConstructor
public class Violator {

    private List<Violation> violations;

    public void throwsIfViolated() {
        String message = this.violations.stream()
                .map(Violation::getMessage)
                .collect(Collectors.joining(SymbolConstant.LF));
        Assert.of().setThrowable(InvalidArgumentException.class).setMessage(message).throwsIfNotBlank(message);
    }

}