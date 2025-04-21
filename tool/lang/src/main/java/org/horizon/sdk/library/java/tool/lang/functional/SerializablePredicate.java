package org.horizon.sdk.library.java.tool.lang.functional;

import java.io.Serializable;
import java.util.function.Predicate;

/**
 * @author wjm
 * @since 2025-04-21 21:38
 */
@FunctionalInterface
public interface SerializablePredicate<T> extends Predicate<T>, Serializable {

}