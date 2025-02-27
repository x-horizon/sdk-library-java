package org.horizon.sdk.library.java.message.engine.client.contract.strategy;

import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-05-27 11:53
 */
public interface MessageClientFlowStrategy {

    String getFlowId(Method producerMethod);

}