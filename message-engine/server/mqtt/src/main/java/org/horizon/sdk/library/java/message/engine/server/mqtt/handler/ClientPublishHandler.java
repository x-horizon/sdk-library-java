package org.horizon.sdk.library.java.message.engine.server.mqtt.handler;

import io.netty.handler.codec.mqtt.MqttPublishMessage;

/**
 * @author wjm
 * @since 2025-01-07 19:57
 */
public interface ClientPublishHandler {

    Void process(MqttPublishMessage mqttPublishMessage);

}