package org.horizon.library.java.message.engine.server.mqtt.handler;

/**
 * @author wjm
 * @since 2025-01-07 21:39
 */
public interface ClientUnsubscribeHandler {

    short process(String unsubscribeTopic);

}