package org.horizon.sdk.library.java.message.engine.client.contract.strategy;

/**
 * @author wjm
 * @since 2024-05-30 10:54
 */
public interface MessageClientUniqueIdGenerateStrategy<T> {

    T getId();

}