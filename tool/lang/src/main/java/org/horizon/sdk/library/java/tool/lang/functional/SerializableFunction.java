package org.horizon.sdk.library.java.tool.lang.functional;

import java.io.Serializable;
import java.util.function.Function;

/**
 * @author wjm
 * @since 2025-04-19 18:36
 */
@FunctionalInterface
public interface SerializableFunction<T, R> extends Function<T, R>, Serializable {

}