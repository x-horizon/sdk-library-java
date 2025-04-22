package org.horizon.sdk.library.java.tool.lang.functional;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * @author wjm
 * @since 2025-04-19 21:34
 */
@FunctionalInterface
public interface SerializableConsumer<T> extends Consumer<T>, Serializable {

}