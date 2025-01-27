package cn.library.java.message.engine.server.mqtt.handler;

import io.netty.handler.codec.mqtt.MqttTopicSubscription;

/**
 * @author wjm
 * @since 2025-01-07 20:33
 */
public interface ClientSubscribeHandler {

    Void process(MqttTopicSubscription mqttTopicSubscription);

}