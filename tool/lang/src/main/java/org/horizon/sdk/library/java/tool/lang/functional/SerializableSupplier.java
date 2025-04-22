package org.horizon.sdk.library.java.tool.lang.functional;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * @author wjm
 * @since 2025-04-21 21:38
 */
@FunctionalInterface
public interface SerializableSupplier<T> extends Supplier<T>, Serializable {

}