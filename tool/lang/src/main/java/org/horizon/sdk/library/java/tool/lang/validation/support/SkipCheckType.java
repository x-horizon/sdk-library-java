package org.horizon.sdk.library.java.tool.lang.validation.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.horizon.sdk.library.java.tool.lang.functional.SerializablePredicate;
import org.horizon.sdk.library.java.tool.lang.object.Nil;

/**
 * @author wjm
 * @since 2025-04-22 22:28
 */
@Getter
@AllArgsConstructor
public enum SkipCheckType {
    NULL(Nil::isNull),
    EMPTY(Nil::isEmpty),
    BLANK(Nil::isBlank),

    ;

    private final SerializablePredicate<Object> predicate;
}