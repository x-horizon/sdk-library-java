package org.horizon.sdk.library.java.tool.lang.functional;

import java.io.Serializable;
import java.util.function.BiPredicate;

/**
 * @author wjm
 * @since 2025-05-02 18:28
 */
@FunctionalInterface
public interface SerializableBiPredicate<T, U> extends BiPredicate<T, U>, Serializable {

}